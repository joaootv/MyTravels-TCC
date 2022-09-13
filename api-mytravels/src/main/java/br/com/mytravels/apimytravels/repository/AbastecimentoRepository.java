package br.com.mytravels.apimytravels.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.mytravels.apimytravels.data.model.Abastecimento;
import br.com.mytravels.apimytravels.data.model.Viagens;

@Repository
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long>{

	@Transactional(readOnly=true)
	List<Abastecimento> findByViagem(Viagens viagem);
}