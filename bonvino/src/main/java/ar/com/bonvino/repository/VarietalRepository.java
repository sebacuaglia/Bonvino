package ar.com.bonvino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.bonvino.model.Varietal;

@Repository
public interface VarietalRepository  extends JpaRepository<Varietal, Long> {
	

}
