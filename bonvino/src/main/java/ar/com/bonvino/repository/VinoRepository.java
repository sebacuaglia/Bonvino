package ar.com.bonvino.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.bonvino.model.Vino;

public interface VinoRepository extends JpaRepository<Vino, Long>  {

}
