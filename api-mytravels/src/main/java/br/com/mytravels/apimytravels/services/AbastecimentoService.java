package br.com.mytravels.apimytravels.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mytravels.apimytravels.data.dto.AbastecimentoDTO;
import br.com.mytravels.apimytravels.data.model.Abastecimento;
import br.com.mytravels.apimytravels.data.model.Viagens;
import br.com.mytravels.apimytravels.data.model.enums.EstadoPagamento;
import br.com.mytravels.apimytravels.data.model.enums.TipoAbastecimento;
import br.com.mytravels.apimytravels.repository.AbastecimentoRepository;

@Service
public class AbastecimentoService {
	
	@Autowired
	private AbastecimentoRepository abastecimentoRepository;
	
	@Autowired
	private ViagensService viagemService;
	
	public List<Abastecimento> findAll() {
		return abastecimentoRepository.findAll();
	}
	
	public List<Abastecimento> findByViagem(Long id) {
		Viagens viagem = viagemService.findById(id).orElse(null);
		return abastecimentoRepository.findByViagem(viagem);
	}
	
	public Abastecimento findById(Long id) {
		return abastecimentoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
	}
	
	@Transactional
	public Abastecimento insert(AbastecimentoDTO obj) {
		Abastecimento abastecimento = new Abastecimento();
		Viagens viagem = new Viagens(obj.getViagemId());
		abastecimento.setKey(null);
		abastecimento.setViagem(viagem);
		abastecimento.setLocal(obj.getLocal());
		abastecimento.setTipoAbastecimento(TipoAbastecimento.toEnum(obj.getTipoAbastecimento()));
		abastecimento.setLitros(obj.getLitros());
		abastecimento.setPreco(obj.getPreco());
		abastecimento.setKmVeiculo(obj.getKmVeiculo());
		abastecimento.setEstadoPagamento(EstadoPagamento.toEnum(obj.getEstadoPagamento()));
		abastecimentoRepository.save(abastecimento);
		return abastecimento;
	}
	
	public Abastecimento update(AbastecimentoDTO obj) {
		var abastecimento = abastecimentoRepository.findById(obj.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		Viagens viagem = new Viagens(obj.getViagemId());
		abastecimento.setKey(obj.getKey());
		abastecimento.setViagem(viagem);
		abastecimento.setLocal(obj.getLocal());
		abastecimento.setTipoAbastecimento(TipoAbastecimento.toEnum(obj.getTipoAbastecimento()));
		abastecimento.setLitros(obj.getLitros());
		abastecimento.setPreco(obj.getPreco());
		abastecimento.setKmVeiculo(obj.getKmVeiculo());
		abastecimento.setEstadoPagamento(EstadoPagamento.toEnum(obj.getEstadoPagamento()));
		abastecimentoRepository.save(abastecimento);
		return abastecimento;
	}
	
	public void delete(Long id) {
		Abastecimento entity = abastecimentoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		abastecimentoRepository.delete(entity);
	}

}
