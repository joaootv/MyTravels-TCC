package br.com.mytravels.apimytravels.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mytravels.apimytravels.data.dto.DespesaDTO;
import br.com.mytravels.apimytravels.data.model.Despesas;
import br.com.mytravels.apimytravels.data.model.Viagens;
import br.com.mytravels.apimytravels.data.model.enums.EstadoPagamento;
import br.com.mytravels.apimytravels.data.model.enums.TipoDespesa;
import br.com.mytravels.apimytravels.repository.DespesaRepository;

@Service
public class DespesasService {
	
	@Autowired
	private DespesaRepository despesaRepository;
	
	@Autowired
	private ViagensService viagemService;
	
	public List<Despesas> findAll() {
		return despesaRepository.findAll();
	}
	
	public List<Despesas> findByViagem(Long id) {
		Viagens viagem = viagemService.findById(id).orElse(null);
		return despesaRepository.findByViagem(viagem);
	}
	
	public Despesas findById(Long id) {
		return despesaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
	}
	
	@Transactional
	public Despesas insert(DespesaDTO obj) {
		Despesas despesas = new Despesas();
		Viagens viagem = new Viagens(obj.getViagemId());
		despesas.setKey(null);
		despesas.setViagem(viagem);
		despesas.setDescricao(obj.getDescricao());
		despesas.setLocal(obj.getLocal());
		despesas.setPreco(obj.getPreco());
		despesas.setData(obj.getData());
		despesas.setTipoDespesa(TipoDespesa.toEnum(obj.getTipoDespesa()));
		despesas.setEstadoPagamento(EstadoPagamento.toEnum(obj.getEstadoPagamento()));
		despesaRepository.save(despesas);
		return despesas;
	}
	
	public Despesas update(DespesaDTO obj) {
		var despesas = despesaRepository.findById(obj.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		Viagens viagem = new Viagens(obj.getViagemId());
		despesas.setKey(obj.getKey());
		despesas.setViagem(viagem);
		despesas.setDescricao(obj.getDescricao());
		despesas.setLocal(obj.getLocal());
		despesas.setPreco(obj.getPreco());
		despesas.setData(obj.getData());
		despesas.setTipoDespesa(TipoDespesa.toEnum(obj.getTipoDespesa()));
		despesas.setEstadoPagamento(EstadoPagamento.toEnum(obj.getEstadoPagamento()));
		despesaRepository.save(despesas);
		return despesas;
	}
	
	public void delete(Long id) {
		Despesas entity = despesaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		despesaRepository.delete(entity);
	}

}
