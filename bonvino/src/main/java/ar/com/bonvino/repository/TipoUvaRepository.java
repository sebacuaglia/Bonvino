package ar.com.bonvino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.bonvino.model.TipoUva;

@Repository
public interface TipoUvaRepository  extends JpaRepository<TipoUva, Long> {
	
	
}
