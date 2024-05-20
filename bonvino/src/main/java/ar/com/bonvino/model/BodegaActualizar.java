package ar.com.bonvino.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class BodegaActualizar extends ObjetoDB {

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
		
		
		public BodegaActualizar() {
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
}
