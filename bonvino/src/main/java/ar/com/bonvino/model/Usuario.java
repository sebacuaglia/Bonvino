package ar.com.bonvino.model;

import javax.persistence.Entity;

@Entity
public class Usuario extends ObjetoDB {

	
	private String nombre;
	
	
	private String contraseña;
	
	
	private boolean premium;

	public Usuario() {
		super();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getContraseña() {
		return contraseña;
	}
	
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public boolean isPremium() {
		return premium;
	}
	
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
	
	
	
	
	
	
	
}