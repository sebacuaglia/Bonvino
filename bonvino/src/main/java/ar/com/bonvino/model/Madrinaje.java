package ar.com.bonvino.model;

import javax.persistence.Entity;

@Entity
public class Madrinaje extends ObjetoDB{

	private String descripcion;
	private String nombre;
	
	public Madrinaje() {
		super();
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
}
