package ar.com.bonvino.service;

import org.springframework.stereotype.Service;

import ar.com.bonvino.model.Enofilo;

@Service
public class ServiceNotificacionPush implements InterfaceNotificacionPush{

	@Override
	public void enviarNotificacionPush(Enofilo enofilo) {
		System.out.println("notificacion enviada a " + enofilo.getNombre() + " " + enofilo.getApellido());
	}

}
