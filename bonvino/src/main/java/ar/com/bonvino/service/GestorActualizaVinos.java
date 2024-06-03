package ar.com.bonvino.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import ar.com.bonvino.model.Bodega;
import ar.com.bonvino.model.TipoUva;
import ar.com.bonvino.model.Varietal;
import ar.com.bonvino.model.Vino;
import ar.com.bonvino.model.VinosActualizar;
import ar.com.bonvino.repository.BodegaRepository;
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
	private ApplicationEventPublisher applicationEventPublisher;
	
	private Date fechaHoraActual;
	
	private List<Bodega> bodegasConActualizacion;
	private Bodega bodegasSeleccionada;
	
	private InterfazAPIBodega conexion;
	
	public GestorActualizaVinos(InterfazAPIBodega conexion){
		this.conexion = conexion;
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
		
		//de todos los vinos que no se actualizaron, deben crearse
		for (VinosActualizar vinoCrear : listaCrearNuevos) {
			//busco la lista de tipos de uva
			Iterable<TipoUva> tipoUvas = buscarTipoDeUva(); 
			
			listaNuevos.add(crearVino(vinoCrear,(List<TipoUva>) tipoUvas));
			
		}
		
		return result;
	}

	private Vino crearVino(VinosActualizar vinoCrear, List<TipoUva> tipoUvas) {
		Vino nuevoVino =  new Vino(vinoCrear.getNombre(), 
				vinoCrear.getAñada(),
				vinoCrear.getFechaActualizacion(),
				vinoCrear.getNotaDeCataBodega(), 
				vinoCrear.getPrecioARS(), 
				vinoCrear.getImagenEtiqueta(),
				bodegasSeleccionada, 
				vinoCrear.getVarietalactualizar(), 
				tipoUvas);
		return nuevoVino;
	}

	private Iterable<TipoUva> buscarTipoDeUva() {
		return tipoUvaRepository.findAll();
	}

	private Iterable<Vino> ObtenerListaVinosBodega() {
		List<Vino> result = new ArrayList<Vino>();
		
		//recorro todos los vinos preguntando si es de la bodega seleccionada
		for (Vino vino : vinoRepository.findAll() ) {
			if(vino.esDeBodega(bodegasSeleccionada)) {
				result.add(vino);
			}
		}
		return result;
	}
	
}
