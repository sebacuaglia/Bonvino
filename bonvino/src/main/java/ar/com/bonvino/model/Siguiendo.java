package ar.com.bonvino.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Siguiendo{

    private Date fechaInicio;

    private Date fechaFin;

    @OneToOne
    private Enofilo enofilo; //AMIGO RELACION MUTUAMENTE EXCLUYENTE
    
    @OneToOne
    private Bodega bodega; //RELACION MUTUAMENTE EXCLUYENTE
    
    @OneToOne
    private Sommelier sommelier; //RELACION MUTUAMENTE EXCLUYENTE
   

    public Siguiendo(Date fechaInicio, Date fechaFin){
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio(){
        return fechaInicio;
    }

    public Date getFechaFin(){
        return fechaFin;
    }

    public void setFechaInicio(Date fechaInicio){
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin){
        this.fechaFin = fechaFin;
    }

	public Enofilo getEnofilo() {
		return enofilo;
	}

	public void setEnofilo(Enofilo enofilo) {
		if (sommelier != null || bodega != null) { //RELACION MUTUAMENTE EXCLUYENTE
			throw new IllegalStateException("No se puede asignar un enofilo cuando ya hay un sommelier o bodega asignado.");
		}
		this.enofilo = enofilo;
	}

	public Bodega getBodega() {
		return bodega;
	}

	public void setBodega(Bodega bodega) {
		if (sommelier != null || enofilo != null) { //RELACION MUTUAMENTE EXCLUYENTE
            throw new IllegalStateException("No se puede asignar una bodega cuando ya hay un sommelier o enofilo asignado.");
        }
		this.bodega = bodega;
	}

	public Sommelier getSomelier() {
		return sommelier;
	}

	public void setSomelier(Sommelier sommelier) { //RELACION MUTUAMENTE EXCLUYENTE
		if (bodega != null || enofilo != null) {
            throw new IllegalStateException("No se puede asignar un sommelier cuando ya hay una bodega o enofilo asignado.");
        }
		this.sommelier = sommelier;
	}
}