package ar.com.bonvino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.bonvino.model.Bodega;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega, Long>  {

}
