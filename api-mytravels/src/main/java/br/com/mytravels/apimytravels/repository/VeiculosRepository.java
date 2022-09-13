package br.com.mytravels.apimytravels.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.mytravels.apimytravels.data.model.Motorista;
import br.com.mytravels.apimytravels.data.model.Transportadora;
import br.com.mytravels.apimytravels.data.model.Veiculos;

@Repository
public interface VeiculosRepository extends JpaRepository<Veiculos, Long>{
	
	@Transactional(readOnly=true)
	List<Veiculos> findByTransportadora(Optional<Transportadora> transportadora);
	
	@Transactional(readOnly=true)
	Veiculos findByMotorista(Motorista motorista);

}
