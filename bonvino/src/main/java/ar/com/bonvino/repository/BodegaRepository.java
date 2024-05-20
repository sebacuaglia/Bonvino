package ar.com.bonvino.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.bonvino.model.Bodega;

public interface BodegaRepository extends JpaRepository<Bodega, Long>  {

}
