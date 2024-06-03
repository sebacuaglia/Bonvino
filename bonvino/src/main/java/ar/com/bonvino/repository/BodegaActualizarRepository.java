package ar.com.bonvino.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.bonvino.model.BodegaActualizar;

@Repository
public interface BodegaActualizarRepository extends CrudRepository<BodegaActualizar, Integer>{

}
