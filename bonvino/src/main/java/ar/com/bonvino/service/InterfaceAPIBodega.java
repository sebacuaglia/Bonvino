package ar.com.bonvino.service;

import java.util.List;

import ar.com.bonvino.model.Bodega;
import ar.com.bonvino.model.VinosActualizar;

public interface InterfaceAPIBodega {

	public void conectar();
	
	public List<VinosActualizar> obtenerActualizacionesBodegaSeleccionada(Bodega bodega);
}
