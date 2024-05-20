package ar.com.bonvino.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Varietal extends ObjetoDB{
   
    private String descripcion;
    
    private float porcentajeComposicion;
    
    @OneToOne
    private TipoUva tipoUva;

    public Varietal(String descripcion, float porcentajeComposicion){
        this.descripcion = descripcion;
        this.porcentajeComposicion = porcentajeComposicion;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public float getPorcentajeComposicion(){
        return porcentajeComposicion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public void setPorcentajeComposicion(float porcentajeComposicion){
        this.porcentajeComposicion = porcentajeComposicion;
    }

	public TipoUva getTipoUva() {
		return tipoUva;
	}

	public void setTipoUva(TipoUva tipoUva) {
		this.tipoUva = tipoUva;
	}
    
    
}