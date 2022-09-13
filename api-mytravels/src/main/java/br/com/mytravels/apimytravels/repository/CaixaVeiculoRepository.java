package br.com.mytravels.apimytravels.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.mytravels.apimytravels.data.model.CaixaVeiculo;
import br.com.mytravels.apimytravels.data.model.Veiculos;

@Repository
public interface CaixaVeiculoRepository extends JpaRepository<CaixaVeiculo, Long>{

	@Transactional(readOnly=true)
	CaixaVeiculo findByVeiculo(Optional<Veiculos> veiculos);
}
