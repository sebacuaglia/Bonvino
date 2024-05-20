package ar.com.bonvino.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.bonvino.Constantes;
import ar.com.bonvino.model.Bodega;
import ar.com.bonvino.service.GestorActualizaVinos;

@RestController
public class GestorActualizaVinosController {

	
	private GestorActualizaVinos gestorActualizaVinos;
	
	@RequestMapping(value = Constantes.URL_PATH_GESTOR_ACTUALIZAR_VINOS, method = RequestMethod.POST)
	public List<Bodega> inicioCU() {
		return gestorActualizaVinos.opcionImportarActualizacionVinoBodegas();
	}
	
	@RequestMapping(value = Constantes.URL_PATH_GESTOR_ACTUALIZAR_VINOS, method = RequestMethod.POST)
	public void actualizarBodegas(Integer bodegasId) {
		//return 
		gestorActualizaVinos.tomarSeleccionBodega(bodegasId);
	}
}
