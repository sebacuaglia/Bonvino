package ar.com.bonvino.service;

import java.util.List;

import ar.com.bonvino.model.Enofilo;

public interface ISujetoNotificacionEnofilo {

	public void notificar();
	public void quitar(IObservador observador);
	public void suscribir(IObservador observador,List<Enofilo> listaSeguidoresBodega);
}
