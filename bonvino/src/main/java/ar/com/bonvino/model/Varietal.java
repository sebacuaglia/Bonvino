package ar.com.bonvino.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Varietal extends ObjetoDB{
   
    private String descripcion;
    
    private float porcentajeComposicion;
    
    @OneToOne
    private TipoUva tipoUva;

    public Varietal(String descripcion, float porcentajeComposicion, String tipoUvaNombre, List<TipoUva> tiposUvas){
        this.descripcion = descripcion;
        this.porcentajeComposicion = porcentajeComposicion;
        this.tipoUva = obtenerTipoUva(tipoUvaNombre, tiposUvas);
    }

    private TipoUva obtenerTipoUva(String tipoUvaNombre, List<TipoUva> tiposUvas) {
		for (TipoUva tipoUva : tiposUvas) {
			if (tipoUva.esTipoUva(tipoUvaNombre)) {
				return tipoUva;
			}
		}
		return new TipoUva(tipoUvaNombre, null);
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
    
	public boolean esDeTipoUva(String tipoUvaNombre) {
		return this.tipoUva.getNombre().equals(tipoUvaNombre);
	}
    
}