package ar.com.bonvino.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import ar.com.bonvino.Constantes;

@Entity
public class Bodega extends ObjetoDB {

	//@Column(name = "fechaactualizacion" )
	private Date fechaActualizacion;
	
	//@Column(name = "coordenadasUbicacion" )
	private String coordenadasUbicacion;
	
	//@Column(name = "descripcion" )
	private String descripcion;
	
	//@Column(name = "historia" )
	private String historia;
	
	//@Column(name = "nombre" )
	private String nombre;
	
	//@Column(name = "periodoActualizacion" )
	private Integer periodoActualizacion; //hace relacion a cada cuantos meses se actualiza
	
	
	
	public Bodega() {
		super();
	}
	
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public String getCoordenadasUbicacion() {
		return coordenadasUbicacion;
	}
	public void setCoordenadasUbicacion(String coordenadasUbicacion) {
		this.coordenadasUbicacion = coordenadasUbicacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getHistoria() {
		return historia;
	}
	public void setHistoria(String historia) {
		this.historia = historia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getPeriodoActualizacion() {
		return periodoActualizacion;
	}
	public void setPeriodoActualizacion(Integer periodoActualizacion) {
		this.periodoActualizacion = periodoActualizacion;
	}
	
	
	//metodos implementados
	
	
	/**
	 * Verifica si ha transcurrido el período entre <U>fechaActualizacion</U> especificado en {@link Bodega}, con la <U>fechaActual</U>
	 * que recibe el metodo.
	 * 
	 * @param fechaActal
	 * @return <UL>
	 * 			<LI> TRUE: si el periodo de actualizacion es mayor o igual al establecido en <U>fechaActualizacion</U>
	 * 			<LI> FALSE: para el caso contrario
	 * 		   </UL>
	 */
	public boolean verificarPeriodicidadActualizacion(Date fechaActal) {
		//convierto las fechas a otro tipo de dato
		LocalDate fechaInicio = Constantes.convertirADate(fechaActualizacion);
        LocalDate fechaFin = Constantes.convertirADate(fechaActal);
        
        //calculo el periodo
		Period period = Period.between(fechaInicio, fechaFin);
		
		//Verifica si ha transcurrido el periodo
        return ( (period.getYears() * 12 + period.getMonths()) >= periodoActualizacion );
		
	}
	
}
