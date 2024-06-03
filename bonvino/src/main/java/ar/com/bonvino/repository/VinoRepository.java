package ar.com.bonvino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.bonvino.model.Vino;

@Repository
public interface VinoRepository extends JpaRepository<Vino, Long>  {

}
