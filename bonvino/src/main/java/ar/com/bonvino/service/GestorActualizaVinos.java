package ar.com.bonvino.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import ar.com.bonvino.model.Bodega;
import ar.com.bonvino.model.Vino;
import ar.com.bonvino.repository.BodegaRepository;
import ar.com.bonvino.repository.VinoRepository;

@Service
public class GestorActualizaVinos {

	@Autowired
	private BodegaRepository bodegaRepository; 
	
	@Autowired
	private VinoRepository vinoRepository; 
	
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
	
	public List<Vino> tomarSeleccionBodega(Integer bodegasId) {
		seleccionarBodega(bodegasId);
		
		//realizo la conexion
		realizarConexionConSistemaBodega();
		
		List<Vino> listaObtenida = obtenerActualizacionesBodegaSeleccionada(bodegasSeleccionada);
		
		List<Vino> listaActualizada = actualizarVinosBodega(listaObtenida);
		
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
	
	
	public List<Vino> obtenerActualizacionesBodegaSeleccionada(Bodega bodega) {
		return conexion.obtenerActualizacionesBodegaSeleccionada(bodega);
	}
	
	public List<Vino> actualizarVinosBodega(List<Vino> listaObtenida){
		
		return null;
	}
	
}
