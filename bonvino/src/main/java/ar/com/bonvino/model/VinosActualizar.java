package ar.com.bonvino.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class VinosActualizar {

private Integer añada;
    
    
    private Date fechaActualizacion;
    
    
    private String nombre;
    
    
    private String notaDeCataBodega;
    
    
    private float precioARS;
    
    
    private String imagenEtiqueta;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Bodega bodega;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<VarietalActualizar> varietalactualizar;

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

	public List<VarietalActualizar> getVarietalactualizar() {
		return varietalactualizar;
	}

	public void setVarietalactualizar(List<VarietalActualizar> varietalactualizar) {
		this.varietalactualizar = varietalactualizar;
	}
    
    
    
}
