package ar.com.bonvino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.bonvino.model.Bodega;
import ar.com.bonvino.model.VinosActualizar;
import ar.com.bonvino.repository.VinosActualizarRepository;

@Service
public class ServiceAPIBodega implements InterfazAPIBodega{

	@Autowired
	private VinosActualizarRepository vinosActualizarRepository;
	
	@Override
	public void conectar() {
		System.out.println("Conectado al sistema de bodega");
	}
	
	@Override
	public List<VinosActualizar> obtenerActualizacionesBodegaSeleccionada(Bodega bodega) {
		return (List<VinosActualizar>) vinosActualizarRepository.findAllByBodega(bodega);
	}

}
