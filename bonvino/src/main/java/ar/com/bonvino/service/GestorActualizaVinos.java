package ar.com.bonvino.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.bonvino.model.Bodega;
import ar.com.bonvino.repository.BodegaRepository;

@Service
public class GestorActualizaVinos {

	@Autowired
	private BodegaRepository bodegaRepository; 
	
	private Date fechaHoraActual;
	
	private List<Bodega> bodegasConActualizacion;
	private Bodega bodegasSeleccionada;
	
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
		
		for (Bodega bodega : bodegasAll) {
			if (bodega.verificarPeriodicidadActualizacion(fechaActual)) {
				bodegasConActualizacion.add(bodega);
			}
		}
		
		return bodegasConActualizacion;
	}
	
	public void tomarSeleccionBodega(Integer bodegasId) {
		seleccionarBodega(bodegasId);
		
		//actualizo las fechas de actualizacion de las bodegas seleccionadas
		bodegasSeleccionada.setFechaActualizacion(fechaHoraActual);
		
		//realizo la conexion
		realizarConexionConSistemaBodega();
		
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
		
	}
	
	
	public void obtenerActualizacionesBodegaSeleccionada() {
		
	}
}
