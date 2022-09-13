package br.com.mytravels.apimytravels.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.mytravels.apimytravels.data.dto.PessoaDTO;
import br.com.mytravels.apimytravels.data.dto.PessoaNewDTO;
import br.com.mytravels.apimytravels.data.model.Pessoa;
import br.com.mytravels.apimytravels.services.PessoaServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Pessoa") 
@RestController
@RequestMapping("/api/pessoa/v1")
public class PessoaController {
	
	@Autowired
	private PessoaServices pessoaServices;
	
	@ApiOperation(value = "Listar todas as pessoas" )
	@GetMapping(produces = { "application/json", "application/xml" })
	public ResponseEntity<List<PessoaDTO>> findAll() {
		List<Pessoa> list = pessoaServices.findAll();
		List<PessoaDTO> listDto = list.stream().map(obj -> new PessoaDTO(obj)).collect(Collectors.toList());
		listDto.stream().forEach(p -> p.add(
					linkTo(methodOn(PessoaController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return ResponseEntity.ok().body(listDto);
	}

	@ApiOperation(value = "Buscar uma pessoa específica através do ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Optional<Pessoa>> findById(@PathVariable("id") Long id) {
		Optional<Pessoa> pessoa = pessoaServices.findById(id);
		return ResponseEntity.ok().body(pessoa);
	}
	
	@ApiOperation(value = "Listar todas as pessoas por transportadora" )
	@GetMapping(value = "/trasnportadora/{id}",produces = { "application/json", "application/xml" })
	public ResponseEntity<List<PessoaDTO>> findAll(@PathVariable("id") Long id) {
		List<Pessoa> list = pessoaServices.findByTransportadora(id);
		List<PessoaDTO> listDto = list.stream().map(obj -> new PessoaDTO(obj)).collect(Collectors.toList());
		listDto.stream().forEach(p -> p.add(
					linkTo(methodOn(TransportadoraController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return ResponseEntity.ok().body(listDto);
	}
	
	@ApiOperation(value = "Cadastrar nova Pessoa")
	@PostMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public ResponseEntity<Void> create(@Valid @RequestBody PessoaNewDTO objDto) {
		Pessoa obj = pessoaServices.fromDTO(objDto);
		obj = pessoaServices.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getKey()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Alteração no cadastro da pessoa")
	@PutMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public Pessoa update(@RequestBody PessoaDTO obj) {
		Pessoa pessoa = pessoaServices.update(obj);
		pessoa.add(linkTo(methodOn(PessoaController.class).findById(pessoa.getKey())).withSelfRel());
		return pessoa;
	}	
	
	@ApiOperation(value = "Excluir pessoa por Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		pessoaServices.delete(id);
		return ResponseEntity.ok().build();
	}	
	
}
