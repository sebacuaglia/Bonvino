package ar.com.bonvino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.bonvino.model.Bodega;
import ar.com.bonvino.model.VinosActualizar;

@Repository
public interface VinosActualizarRepository extends JpaRepository<VinosActualizar, Long>{

	public Iterable<VinosActualizar> findAllByBodega(Bodega bodega);
}
