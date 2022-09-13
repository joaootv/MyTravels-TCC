package br.com.mytravels.apimytravels.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.mytravels.apimytravels.data.model.Motorista;
import br.com.mytravels.apimytravels.data.model.Pessoa;
import br.com.mytravels.apimytravels.data.model.Transportadora;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long>{
	
	@Transactional(readOnly=true)
	List<Motorista> findByTransportadora(Optional<Transportadora> transportadora);
	
	@Transactional(readOnly=true)
	Motorista findByPessoa(Pessoa pessoa);

}
