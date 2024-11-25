package ar.com.bonvino.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Enofilo extends ObjetoDB {

	private String apellido;
	
	
	private String nombre;
	
	
	private String imagenPerfil;
	
	@OneToOne
	private Usuario usuario;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Vino> vino; //VINOS FAVORITOS
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Siguiendo> seguido;
	
	private String telefono;

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

	public String getImagenPerfil() {
		return imagenPerfil;
	}

	public void setImagenPerfil(String imagenPrefil) {
		this.imagenPerfil = imagenPrefil;
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
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean sigueABodega(Bodega bodegaSeleccionada) {
		for (Siguiendo siguiendo : this.getSeguido()) {
			if ( siguiendo.sosDeBodega() ) {
				if (siguiendo.esBodega(bodegaSeleccionada)) {
					return true;	
				}
			}
		}
		return false;
	}
	
}
