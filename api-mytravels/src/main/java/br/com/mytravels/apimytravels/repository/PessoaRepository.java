package br.com.mytravels.apimytravels.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.mytravels.apimytravels.data.model.Pessoa;
import br.com.mytravels.apimytravels.data.model.Transportadora;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	@Transactional(readOnly=true)
	List<Pessoa> findByTransportadora(Optional<Transportadora> transportadora);

}
