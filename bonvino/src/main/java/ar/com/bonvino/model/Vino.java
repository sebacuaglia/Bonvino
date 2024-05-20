package ar.com.bonvino.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Vino {
	
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

    public String getImagenEtiqueta() {
        return imagenEtiqueta;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAñada() {
        return añada;
    }

    public String getNotaDeCataBodega() {
        return notaDeCataBodega; 
    }

    public float getPrecio() {
        return precioARS;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setNombre (String nombre){
        this.nombre = nombre;

    }

    public void setAñada (int añada){
        this.añada = añada;
        actualizarFecha( new Date(System.currentTimeMillis()) );
    }

    public void setNotaDeCataBodega (String notaDeCataBodega){
        this.notaDeCataBodega = notaDeCataBodega;
        actualizarFecha( new Date(System.currentTimeMillis()) );
    }

    public void setPrecio (float precioARS){
        this.precioARS = precioARS;
        actualizarFecha( new Date(System.currentTimeMillis()) );
    }

    public void setImagen (String imagenEtiqueta){
        this.imagenEtiqueta = imagenEtiqueta;
        actualizarFecha( new Date(System.currentTimeMillis()) );
    }

    public void actualizarFecha (Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    //falta agregar la imagen en el return
    
    public String toString() {
        return "Vino{" +
                "nombre='" + nombre + '\'' +
                ", añada=" + añada +
                ", fechaActualizacion=" + fechaActualizacion +
                ", notaDeCataBodega='" + notaDeCataBodega + '\'' +
                ", precioARS=" + precioARS +
                '}';
    }

}

