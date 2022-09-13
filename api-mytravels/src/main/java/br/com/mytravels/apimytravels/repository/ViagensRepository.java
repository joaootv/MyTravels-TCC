package br.com.mytravels.apimytravels.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.mytravels.apimytravels.data.model.Motorista;
import br.com.mytravels.apimytravels.data.model.Transportadora;
import br.com.mytravels.apimytravels.data.model.Viagens;

@Repository
public interface ViagensRepository extends JpaRepository<Viagens, Long>{

	@Transactional(readOnly=true)
	List<Viagens> findByTransportadora(Optional<Transportadora> transportadora);
	
	@Transactional(readOnly=true)
	List<Viagens> findByMotorista(Optional<Motorista> motorista);
}
