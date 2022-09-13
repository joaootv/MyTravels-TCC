package br.com.mytravels.apimytravels.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mytravels.apimytravels.data.dto.MotoristaDTO;
import br.com.mytravels.apimytravels.data.dto.MotoristaNewDTO;
import br.com.mytravels.apimytravels.data.model.Cidade;
import br.com.mytravels.apimytravels.data.model.Motorista;
import br.com.mytravels.apimytravels.data.model.Pessoa;
import br.com.mytravels.apimytravels.data.model.Transportadora;
import br.com.mytravels.apimytravels.repository.MotoristaRepository;

@Service
public class MotoristaService {
	
	@Autowired
	private MotoristaRepository motoristaRepository;
	
	@Autowired
	private PessoaServices pessoaServices;
	
	@Autowired
	private TransportadoraServices transportadoraServices;
	
	public List<Motorista> findAll() {
		return motoristaRepository.findAll();
	}
	
	public Optional<Motorista> findById(Long id) {
		return motoristaRepository.findById(id);
	}
	
	public Optional<Motorista> findByPessoa(Long id) {
		Pessoa pessoa = pessoaServices.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado!"));
		var motorista =  motoristaRepository.findByPessoa(pessoa);
		return this.findById(motorista.getKey());
	}
	
	public List<Motorista> findByTransportadora(Long id) {
		Optional<Transportadora> transportadora = this.transportadoraServices.findById(id);
		return motoristaRepository.findByTransportadora(transportadora);
	}
	
	@Transactional
	public Motorista insert(Motorista obj) {
		obj.setKey(null);
		obj = motoristaRepository.save(obj);
		return obj;
	}
	
	public Motorista fromDTO(MotoristaNewDTO objDto) {
		Cidade cidadeEmissao = new Cidade(objDto.getCidadeEmissao(), null, null);
		Transportadora transportadora = new Transportadora(objDto.getTransportadoraId(), null, null, null, null, null, null);
		Pessoa cli = new Pessoa(objDto.getPessoaId(), null, null, null, null,  null, null, null, null, null, null, null);
		Motorista motorista = new Motorista(null, cli, objDto.getCnhRegistro(), objDto.getCnhEspelho(), objDto.getValidade(), objDto.getNumSeguranca(), objDto.getRenach(), objDto.getCategoria(), cidadeEmissao, objDto.getTaxaComissao(), transportadora);
		return motorista;
	}
	
	public Motorista update(MotoristaDTO motorista) {
		var entity = motoristaRepository.findById(motorista.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		
		entity.setCnhRegistro(motorista.getCnhRegistro());
		entity.setCnhEspelho(motorista.getCnhEspelho());
		entity.setValidade(motorista.getValidade());
		entity.setNumSeguranca(motorista.getNumSeguranca());
		entity.setRenach(motorista.getRenach());
		entity.setCategoria(motorista.getCategoria());
		Cidade cidadeEmissao = new Cidade(motorista.getCidadeEmissao(), null, null);
		entity.setCidadeEmissao(cidadeEmissao);
		entity.setTaxaComissao(motorista.getTaxaComissao());
		
		motoristaRepository.save(entity);
		return entity;
	}	
	
	public void delete(Long id) {
		Motorista entity = motoristaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		motoristaRepository.delete(entity);
	}

}
