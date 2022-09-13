package br.com.mytravels.apimytravels.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mytravels.apimytravels.data.dto.PessoaDTO;
import br.com.mytravels.apimytravels.data.dto.PessoaNewDTO;
import br.com.mytravels.apimytravels.data.model.Cidade;
import br.com.mytravels.apimytravels.data.model.Endereco;
import br.com.mytravels.apimytravels.data.model.Estado;
import br.com.mytravels.apimytravels.data.model.Pessoa;
import br.com.mytravels.apimytravels.data.model.Transportadora;
import br.com.mytravels.apimytravels.repository.EnderecoRepository;
import br.com.mytravels.apimytravels.repository.PessoaRepository;

@Service
public class PessoaServices {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired 
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private TransportadoraServices transportadoraServices;
	
	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}
	
	public Optional<Pessoa> findById(Long id) {
		return pessoaRepository.findById(id);
	}
	
	public List<Pessoa> findByTransportadora(Long id) {
		Optional<Transportadora> transportadora = this.transportadoraServices.findById(id);
		return pessoaRepository.findByTransportadora(transportadora);
	}
	
	@Transactional
	public Pessoa insert(Pessoa obj) {
		obj.setKey(null);
		obj = pessoaRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Pessoa fromDTO(PessoaNewDTO objDto) {
		Cidade naturalidade = new Cidade(objDto.getNaturalidade(), null, null);
		Estado uf = new Estado(objDto.getUf(), null);
		Transportadora transportadora = new Transportadora(objDto.getTransportadoraId(), null, null, null, null, null, null);
		Pessoa cli = new Pessoa(null, objDto.getNome(), objDto.getCpf(), objDto.getDtNascimento(), naturalidade,  objDto.getRg(), objDto.getOrgaoExpedidor(), uf, objDto.getPai(), objDto.getMae(), objDto.getEmail(), transportadora);
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), null, cli, cid);
		cli.getEnderecos().add(end);
	
		return cli;
	}
	
	public Pessoa update(PessoaDTO pessoa) {
		var entity = pessoaRepository.findById(pessoa.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		
		entity.setNome(pessoa.getNome());
		entity.setCpf(pessoa.getCpf());
		entity.setDtNascimento(pessoa.getDtNascimento());
		entity.setNaturalidade(pessoa.getNaturalidade());
		entity.setRg(pessoa.getRg());
		entity.setOrgaoExpedidor(pessoa.getOrgaoExpedidor());
		entity.setUf(pessoa.getUf());
		entity.setPai(pessoa.getPai());
		entity.setMae(pessoa.getMae());
		entity.setEmail(pessoa.getEmail());
		entity.setTelefones(pessoa.getTelefones());
		
		pessoaRepository.save(entity);
		return entity;
	}	
	
	public void delete(Long id) {
		Pessoa entity = pessoaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		pessoaRepository.delete(entity);
	}

}
