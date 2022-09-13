package br.com.mytravels.apimytravels.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mytravels.apimytravels.data.model.TipoVeiculo;
import br.com.mytravels.apimytravels.repository.TipoVeiculoRepository;

@Service
public class TipoVeiculoService {
	
	@Autowired
	private TipoVeiculoRepository repository;
	
	public List<TipoVeiculo> findAll() {
		return repository.findAll();
	}
	
	public Optional<TipoVeiculo> findById(Long id) {
		return repository.findById(id);
	}
	
	public TipoVeiculo create(TipoVeiculo tipoVeiculo) {
		return repository.save(tipoVeiculo);
	}
	
	public TipoVeiculo update(TipoVeiculo tipo) {
		var entity = repository.findById(tipo.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		entity.setDescricao(tipo.getDescricao());
		repository.save(entity);
		return entity;
	}	
	
	public void delete(Long id) {
		TipoVeiculo entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		repository.delete(entity);
	}

}
