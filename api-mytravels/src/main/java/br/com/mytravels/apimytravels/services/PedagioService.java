package br.com.mytravels.apimytravels.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mytravels.apimytravels.data.dto.PedagioDTO;
import br.com.mytravels.apimytravels.data.model.Pedagio;
import br.com.mytravels.apimytravels.data.model.Viagens;
import br.com.mytravels.apimytravels.repository.PedagioRepository;

@Service
public class PedagioService {
	
	@Autowired
	private PedagioRepository pedagioRepository;
	
	@Autowired
	private ViagensService viagemService;
	
	public List<Pedagio> findAll() {
		return pedagioRepository.findAll();
	}
	
	public List<Pedagio> findByViagem(Long id) {
		Viagens viagem = viagemService.findById(id).orElse(null);
		return pedagioRepository.findByViagem(viagem);
	}
	
	public Pedagio findById(Long id) {
		return pedagioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
	}
	
	@Transactional
	public Pedagio insert(PedagioDTO obj) {
		Pedagio pedagio = new Pedagio();
		Viagens viagem = new Viagens(obj.getViagemId());
		pedagio.setKey(null);
		pedagio.setViagem(viagem);
		pedagio.setDescricao(obj.getDescricao());
		pedagio.setNumEixos(obj.getNumEixos());
		pedagio.setValor(obj.getValor());
		pedagio.setDataHora(obj.getDataHora());
		pedagioRepository.save(pedagio);
		return pedagio;
	}
	
	public Pedagio update(PedagioDTO obj) {
		var pedagio = pedagioRepository.findById(obj.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		Viagens viagem = new Viagens(obj.getViagemId());
		pedagio.setKey(obj.getKey());
		pedagio.setViagem(viagem);
		pedagio.setDescricao(obj.getDescricao());
		pedagio.setNumEixos(obj.getNumEixos());
		pedagio.setValor(obj.getValor());
		pedagio.setDataHora(obj.getDataHora());
		pedagioRepository.save(pedagio);
		return pedagio;
	}
	
	public void delete(Long id) {
		Pedagio entity = pedagioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		pedagioRepository.delete(entity);
	}

}
