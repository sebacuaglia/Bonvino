package ar.com.bonvino.service;

import java.util.List;

import ar.com.bonvino.model.Enofilo;
import ar.com.bonvino.model.Vino;
import ar.com.bonvino.model.VinosActualizar;

public interface IObservador {

	public void enviarNotificacion(Enofilo enofilo, List<Vino> listaNuevos,List<Vino> listaActualizada);
}
