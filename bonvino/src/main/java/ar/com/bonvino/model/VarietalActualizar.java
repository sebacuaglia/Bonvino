package ar.com.bonvino.model;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class VarietalActualizar extends Varietal{

	public VarietalActualizar(String descripcion, float porcentajeComposicion, String tipoUvaNombre,
			List<TipoUva> tiposUvas) {
		super(descripcion, porcentajeComposicion, tipoUvaNombre, tiposUvas);
		// TODO Auto-generated constructor stub
	}

}
