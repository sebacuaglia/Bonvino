package ar.com.bonvino.model;

import java.util.Date;

public class Sommelier {

	private String nombre;
	private Date fechaValidacion;
	private String notaPresentacion;
	
	public Sommelier() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaValidacion() {
		return fechaValidacion;
	}

	public void setFechaValidacion(Date fechaValidacion) {
		this.fechaValidacion = fechaValidacion;
	}

	public String getNotaPresentacion() {
		return notaPresentacion;
	}

	public void setNotaPresentacion(String notaPresentacion) {
		this.notaPresentacion = notaPresentacion;
	}
	
	
	
}
