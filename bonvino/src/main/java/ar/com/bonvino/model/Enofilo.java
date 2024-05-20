package ar.com.bonvino.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;



public class Enofilo extends ObjetoDB {

	private String apellido;
	
	
	private String nombre;
	
	
	private String imagenPrefil;
	
	@OneToOne
	private Usuario usuario;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private List<Vino> vino; //VINOS FAVORITOS
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Siguiendo seguido;
	
	
}
