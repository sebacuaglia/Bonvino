package ar.com.bonvino;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Constantes {

	public final static String URL_PATH_GESTOR_ACTUALIZAR_VINOS = "/gestorActualizarVinos";
	
	
	public static LocalDate convertirADate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
