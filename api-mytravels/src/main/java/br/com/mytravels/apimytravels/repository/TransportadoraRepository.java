package br.com.mytravels.apimytravels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.mytravels.apimytravels.data.model.Transportadora;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transportadora, Long>{
	
	@Transactional(readOnly=true)
	Transportadora findByEmail(String email);

}


