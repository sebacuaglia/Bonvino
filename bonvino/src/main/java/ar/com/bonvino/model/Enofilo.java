package ar.com.bonvino.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Enofilo extends ObjetoDB {

	private String apellido;
	
	
	private String nombre;
	
	
	private String imagenPrefil;
	
	@OneToOne
	private Usuario usuario;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private List<Vino> vino; //VINOS FAVORITOS
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Siguiendo> seguido;

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagenPrefil() {
		return imagenPrefil;
	}

	public void setImagenPrefil(String imagenPrefil) {
		this.imagenPrefil = imagenPrefil;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Vino> getVino() {
		return vino;
	}

	public void setVino(List<Vino> vino) {
		this.vino = vino;
	}

	public List<Siguiendo> getSeguido() {
		return seguido;
	}

	public void setSeguido(List<Siguiendo> seguido) {
		this.seguido = seguido;
	}
	
	
}
