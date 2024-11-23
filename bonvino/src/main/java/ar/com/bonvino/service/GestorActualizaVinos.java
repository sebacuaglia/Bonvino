package ar.com.bonvino.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import ar.com.bonvino.Constantes;
import ar.com.bonvino.model.Bodega;
import ar.com.bonvino.model.Enofilo;
import ar.com.bonvino.model.Siguiendo;
import ar.com.bonvino.model.TipoUva;
import ar.com.bonvino.model.Varietal;
import ar.com.bonvino.model.Vino;
import ar.com.bonvino.model.VinosActualizar;
import ar.com.bonvino.model.utils.EventBodega;
import ar.com.bonvino.repository.BodegaRepository;
import ar.com.bonvino.repository.EnofiloRepository;
import ar.com.bonvino.repository.TipoUvaRepository;
import ar.com.bonvino.repository.VarietalRepository;
import ar.com.bonvino.repository.VinoRepository;

@Service
public class GestorActualizaVinos {

	@Autowired
	private BodegaRepository bodegaRepository; 
	
	@Autowired
	private VinoRepository vinoRepository; 
	
	@Autowired
	private TipoUvaRepository tipoUvaRepository; 
	
	@Autowired
	private VarietalRepository varietalRepository;
	
	@Autowired
	private EnofiloRepository enofiloRepository;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	private Date fechaHoraActual;
	
	private List<Bodega> bodegasConActualizacion;
	private Bodega bodegasSeleccionada;
	
	private InterfaceAPIBodega conexion;
	
	private InterfaceNotificacionPush notificacionPush;
	
	public GestorActualizaVinos(InterfaceAPIBodega conexion, InterfaceNotificacionPush notificacionPush){
		this.conexion = conexion;
		this.notificacionPush = notificacionPush;
	}
	
	public List<Bodega> opcionImportarActualizacionVinoBodegas() {
		getFechaActual();
		
		return buscarBodegasConActualizacionesDisponibles(fechaHoraActual);
	}
	
	public void getFechaActual() {
		fechaHoraActual = new Date(System.currentTimeMillis());
	}
	
	public List<Bodega> buscarBodegasConActualizacionesDisponibles(Date fechaActual) {
		List<Bodega> bodegasAll = bodegaRepository.findAll();
		bodegasConActualizacion = new ArrayList<Bodega>();
		
		//recorro las bodegas verificando si deben o no actualizarse
		for (Bodega bodega : bodegasAll) {
			if (bodega.verificarPeriodicidadActualizacion(fechaActual)) {
				bodegasConActualizacion.add(bodega);
			}
		}
		
		//retorno las bodegas que pueden tener actualizaciones
		return bodegasConActualizacion;
	}
	
	public HashMap<String,List<Vino>> tomarSeleccionBodega(Integer bodegasId) {
		seleccionarBodega(bodegasId);
		
		//realizo la conexion
		realizarConexionConSistemaBodega();
		
		List<VinosActualizar> listaObtenida = obtenerActualizacionesBodegaSeleccionada(bodegasSeleccionada);
		
		HashMap<String,List<Vino>> listaActualizada = actualizarVinosBodega(listaObtenida);
		
		//actualizamos fechaActualizacion bodega
		bodegasSeleccionada.setFechaActualizacion(fechaHoraActual);
		bodegaRepository.save(bodegasSeleccionada);
				
		//esto hace que se ejecute un metodo asyncrono(paralela a la ejecucion actual) para enviar las notificaciones
		applicationEventPublisher.publishEvent(bodegasId);
		
		return listaActualizada;
	}
	
	
	//de la lista enviada, tomo solo los que se seleccionaron.
	public void seleccionarBodega(Integer bodegasId) {
		//si no existe la lista la vuelvo a crear
		if(bodegasConActualizacion == null) {
			opcionImportarActualizacionVinoBodegas();
		}
		
		bodegasSeleccionada = bodegasConActualizacion.stream()
					            .filter(bodega -> bodega.getId().equals(bodegasId))
					            .findFirst()
					            .orElse(null);
	}
	
	public void realizarConexionConSistemaBodega() {
		conexion.conectar();
	}
	
	
	public List<VinosActualizar> obtenerActualizacionesBodegaSeleccionada(Bodega bodega) {
		return conexion.obtenerActualizacionesBodegaSeleccionada(bodega);
	}
	
	public HashMap<String,List<Vino>> actualizarVinosBodega(List<VinosActualizar> listaObtenida){
		HashMap<String,List<Vino>> result = new HashMap<String, List<Vino>>();
		
		List<Vino> listaActualizada= new ArrayList<Vino>();
		List<Vino> listaNuevos= new ArrayList<Vino>();
		List<VinosActualizar> listaCrearNuevos= new ArrayList<VinosActualizar>();
		
		//Obtener Lista de Vinos de la bodega seleccionada
		Iterable<Vino> listaVinosActuales = ObtenerListaVinosBodega();
		
		//recorro los vinos a actualizar
		for (VinosActualizar vinoExterno : listaObtenida) {
			boolean seleccionActualizacion = false;
			for (Vino vino : listaVinosActuales) {
				
				//buscamos el vino obtenido externamente coincide con el que tenemos guardado en base de datos.
				if( vino.esVinoSeleccionado(vinoExterno) ) {
					seleccionActualizacion = true;
					
					//verificamos la fechas de actualizacion que poseen si son iguales, entonces no debe actualizarce
					if ( ! vino.esFechaActualizacionIguales(vinoExterno.getFechaActualizacion())) {
						vino.actualizarDatos(vinoExterno);
						listaActualizada.add(
							//actualizar base de datos
							vinoRepository.save(vino)
						);
						break;
					}
				}
			}
			
			//si esta bandera no es true quiere decir que el vino buscado no fue encontrado en nuestra base de datos y debe crearse
			if ( !seleccionActualizacion ) {
				listaCrearNuevos.add(vinoExterno);
			}
		}
		
		//de todos los vinos que no se actualizaron, deben crearse y los agregamos a la lista de creados
		for (VinosActualizar vinoCrear : listaCrearNuevos) {
						
			listaNuevos.add(
					crearVino(vinoCrear)
				);
			
			
		}
		
		//guardamos las actualizaciones y las devolvemos al front
		result.put(Constantes.CREADOS, listaNuevos);
		result.put(Constantes.ACTUALIZARDOS, listaActualizada);
		
		return result;
	}

	private Vino crearVino(VinosActualizar vinoCrear) {
		//busco la lista de tipos de uva
		List<TipoUva> tiposUvas = tipoUvaRepository.findAll();
		
		//filtro los tipos de uva que necesito
		List<TipoUva> tipouva = obtenerTipoDeUva(vinoCrear,tiposUvas);
		
		//creo el vino
		Vino nuevoVino =  new Vino(vinoCrear.getNombre(), 
				vinoCrear.getAñada(),
				vinoCrear.getFechaActualizacion(),
				vinoCrear.getNotaDeCataBodega(), 
				vinoCrear.getPrecioARS(), 
				vinoCrear.getImagenEtiqueta(),
				bodegasSeleccionada, 
				vinoCrear.getVarietalactualizar(), 
				tipouva);
		
		return vinoRepository.save(nuevoVino);
	}

	//obtengo el o los tipos de uva que necesito para crear el vino.
	private List<TipoUva> obtenerTipoDeUva(VinosActualizar vinoCrear, List<TipoUva> tiposUvas) {
		List<TipoUva> result = new ArrayList<TipoUva>();

		//recorro los tipos de uva y varietales
		for (TipoUva uva : tiposUvas) {
			for (Varietal varietal : vinoCrear.getVarietalactualizar()) {
				if(varietal.esDeTipoUva(uva.getNombre())) {
					result.add(varietal.getTipoUva());
				}
			}
		}
		return result;
	}

	private Iterable<Vino> ObtenerListaVinosBodega() {
		List<Vino> result = new ArrayList<Vino>();
		
		//recorro todos los vinos preguntando si es de la bodega seleccionada
		for (Vino vino : vinoRepository.findAll() ) {
			if(vino.perteneceABodegaSeleccionada(bodegasSeleccionada)) {
				result.add(vino);
			}
		}
		return result;
	}
	
	
	
	
	
	
	
	@EventListener
	public void eventoNotificacion(EventBodega event) {
		Iterable<Enofilo> listaSeguidores = obtenerListaEnofilos();
		for (Enofilo enofilo : listaSeguidores) {
			for (Siguiendo siguiendo : enofilo.getSeguido()) {
				if ( siguiendo.sosDeBodega() ) {
					if (siguiendo.esBodega(event.getBodega())) {
						notificacionPush.enviarNotificacionPush(enofilo);
					}
				}
			}
		}
		
	}

	private Iterable<Enofilo> obtenerListaEnofilos() {
		return enofiloRepository.findAll();
	}
	
	
	
	
	
	
}
