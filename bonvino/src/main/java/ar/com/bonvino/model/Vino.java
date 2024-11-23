package ar.com.bonvino.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Vino extends ObjetoDB {
	
    private Integer añada;
    
    
    private Date fechaActualizacion;
    
    
    private String nombre;
    
    
    private String notaDeCataBodega;
    
    
    private float precioARS;
    
    
    private String imagenEtiqueta;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Bodega bodega;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Varietal> varietal;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Madrinaje> madrinaje;
    

    public Vino(){
    	super();
    } 

    
    public Integer getAñada() {
		return añada;
	}

	public void setAñada(Integer añada) {
		this.añada = añada;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNotaDeCataBodega() {
		return notaDeCataBodega;
	}

	public void setNotaDeCataBodega(String notaDeCataBodega) {
		this.notaDeCataBodega = notaDeCataBodega;
	}

	public float getPrecioARS() {
		return precioARS;
	}

	public void setPrecioARS(float precioARS) {
		this.precioARS = precioARS;
	}

	public String getImagenEtiqueta() {
		return imagenEtiqueta;
	}

	public void setImagenEtiqueta(String imagenEtiqueta) {
		this.imagenEtiqueta = imagenEtiqueta;
	}

	public Bodega getBodega() {
		return bodega;
	}

	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}

	public List<Varietal> getVarietal() {
		return varietal;
	}

	public void setVarietal(List<Varietal> varietal) {
		this.varietal = varietal;
	}

	public List<Madrinaje> getMadrinaje() {
		return madrinaje;
	}

	public void setMadrinaje(List<Madrinaje> madrinaje) {
		this.madrinaje = madrinaje;
	}
	
	public Vino(String nombre, Integer añada, Date fechaActualizacion, String notaDeCataBodega, float precioARS,
			String imagenEtiqueta, Bodega bodega, List<VarietalActualizar> varietal, List<TipoUva> tiposUvas) {
		super();
		this.añada = añada;
		this.fechaActualizacion = fechaActualizacion;
		this.nombre = nombre;
		this.notaDeCataBodega = notaDeCataBodega;
		this.precioARS = precioARS;
		this.imagenEtiqueta = imagenEtiqueta;
		this.bodega = bodega;
		this.varietal = crearVarietal(varietal, tiposUvas);
	}

	public List<Varietal> crearVarietal(List<VarietalActualizar> varietal, List<TipoUva> tiposUvas){
		List<Varietal> result = new ArrayList<Varietal>();
		for (VarietalActualizar varietalActualizar : varietal) {
			result.add(new Varietal(
					varietalActualizar.getDescripcion(),
					varietalActualizar.getPorcentajeComposicion(),
					varietalActualizar.getTipoUva().getNombre(),
					tiposUvas));
		}
		return result;
	}

	public boolean perteneceABodegaSeleccionada(Bodega bodega) {
    	//ver si funciona la camparacio
    	return (this.bodega.equals(bodega));
    }
    
    public boolean esVinoSeleccionado(VinosActualizar vino) {
    	return (this.getNombre().equals(vino.getNombre()) && 
    			this.getAñada() == vino.getAñada());
    }
    
    public boolean esFechaActualizacionIguales(Date fechaExterna) {
    	return (this.fechaActualizacion.compareTo(fechaExterna) == 0);
    }

    public void actualizarDatos(VinosActualizar vino) {
    	this.añada=vino.getAñada();
    	this.fechaActualizacion=vino.getFechaActualizacion();
    	this.precioARS=vino.getPrecioARS();
    	this.notaDeCataBodega=vino.getNotaDeCataBodega();
    }
}

