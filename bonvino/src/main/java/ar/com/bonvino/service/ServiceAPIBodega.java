package ar.com.bonvino.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.bonvino.model.Bodega;
import ar.com.bonvino.model.Vino;

@Service
public class ServiceAPIBodega implements InterfazAPIBodega{

	@Override
	public void conectar() {
		System.out.println("Conectado al sistema de bodega");
	}
	
	@Override
	public List<Vino> obtenerActualizacionesBodegaSeleccionada(Bodega bodega) {
		// TODO Auto-generated method stub
		return null;
	}

}
