package ar.com.bonvino.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.bonvino.model.Enofilo;
import ar.com.bonvino.model.Vino;
import ar.com.bonvino.model.VinosActualizar;

@Service
public class ServiceNotificacionPush implements IObservador{

	@Override
	public void enviarNotificacion(Enofilo enofilo, List<Vino> listaNuevos,List<Vino> listaActualizada) {
		notificarWpp(enofilo,listaNuevos,listaActualizada);
	}
	
	public void notificarWpp(Enofilo enofilo, List<Vino> listaNuevos,List<Vino> listaActualizada){
		
		System.out.println("notificacion enviada por WHATSAPP a " + enofilo.getNombre() + " " + enofilo.getApellido() 
								+ " que tiene vinos nuevos " + listaNuevos + " y actualizados " + listaActualizada );
	}

}
