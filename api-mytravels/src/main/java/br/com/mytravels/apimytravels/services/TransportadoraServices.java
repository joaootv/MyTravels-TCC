package br.com.mytravels.apimytravels.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mytravels.apimytravels.data.dto.TransportadoraDTO;
import br.com.mytravels.apimytravels.data.dto.TransportadoraNewDTO;
import br.com.mytravels.apimytravels.data.model.Cidade;
import br.com.mytravels.apimytravels.data.model.Endereco;
import br.com.mytravels.apimytravels.data.model.Transportadora;
import br.com.mytravels.apimytravels.data.model.enums.TipoPessoa;
import br.com.mytravels.apimytravels.repository.EnderecoRepository;
import br.com.mytravels.apimytravels.repository.TransportadoraRepository;

@Service
public class TransportadoraServices {
	
	@Autowired
	private TransportadoraRepository transportadoraRepository;
	
	@Autowired 
	private EnderecoRepository enderecoRepository;
	
	public List<Transportadora> findAll() {
		return transportadoraRepository.findAll();
	}
	
	public Optional<Transportadora> findById(Long id) {
		return transportadoraRepository.findById(id);
	}
	
	@Transactional
	public Transportadora insert(Transportadora obj) {
		obj.setKey(null);
		obj = transportadoraRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Transportadora fromDTO(TransportadoraNewDTO objDto) {
		Transportadora cli = new Transportadora(null, objDto.getNome(), TipoPessoa.toEnum(objDto.getTipo()), objDto.getCpfOuCnpj(), objDto.getEmail(), objDto.getRNTRC(), objDto.getVencimentoRNTRC());
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, null, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	public Transportadora update(TransportadoraDTO transportadora) {
		var entity = transportadoraRepository.findById(transportadora.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		
		entity.setNome(transportadora.getNome());
		entity.setEmail(transportadora.getEmail());
		entity.setRNTRC(transportadora.getRntrc());
		entity.setVencimentoRNTRC(transportadora.getVencimentoRNTRC());
		entity.setTelefones(transportadora.getTelefones());
		
		transportadoraRepository.save(entity);
		return entity;
	}	
	
	public void delete(Long id) {
		Transportadora entity = transportadoraRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID"));
		transportadoraRepository.delete(entity);
	}
	
	

}
