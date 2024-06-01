package ar.com.bonvino.service;

import java.util.List;

import ar.com.bonvino.model.Bodega;
import ar.com.bonvino.model.Vino;

public interface InterfazAPIBodega {

	public void conectar();
	
	public List<Vino> obtenerActualizacionesBodegaSeleccionada(Bodega bodega);
}
