package br.com.mytravels.apimytravels.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import br.com.mytravels.apimytravels.data.dto.MotoristaDTO;
import br.com.mytravels.apimytravels.data.dto.MotoristaNewDTO;
import br.com.mytravels.apimytravels.data.model.Motorista;
import br.com.mytravels.apimytravels.services.MotoristaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Motorista") 
@RestController
@RequestMapping("/api/motorista/v1")
public class MotoristaController {
	
	@Autowired
	private MotoristaService motoristaService;
	
	@ApiOperation(value = "Listar todas os motoristas" )
	@GetMapping(produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Motorista>> findAll() {
		List<Motorista> list = motoristaService.findAll();
		list.stream().forEach(p -> p.add(
					linkTo(methodOn(MotoristaController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Buscar um motorista específico através do ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Optional<Motorista>> findById(@PathVariable("id") Long id) {
		Optional<Motorista> motorista = motoristaService.findById(id);
		return ResponseEntity.ok().body(motorista);
	}
	
	@ApiOperation(value = "Buscar um motorista específico através do ID da pessoa" )
	@GetMapping(value = "/pessoa/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Optional<Motorista>> findByPessoa(@PathVariable("id") Long id) {
		Optional<Motorista> motorista = motoristaService.findByPessoa(id);
		return ResponseEntity.ok().body(motorista);
	}
	
	@ApiOperation(value = "Listar todos os motorista por transportadora" )
	@GetMapping(value = "/trasnportadora/{id}",produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Motorista>> findAll(@PathVariable("id") Long id) {
		List<Motorista> list = motoristaService.findByTransportadora(id);
		list.stream().forEach(p -> p.add(
					linkTo(methodOn(MotoristaController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value = "Cadastrar novo Motorista")
	@PostMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public ResponseEntity<Void> create(@Valid @RequestBody MotoristaNewDTO objDto) {
		Motorista obj = motoristaService.fromDTO(objDto);
		obj = motoristaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getKey()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Alteração no cadastro do motorista")
	@PutMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public Motorista update(@RequestBody MotoristaDTO obj) {
		Motorista motorista = motoristaService.update(obj);
		motorista.add(linkTo(methodOn(PessoaController.class).findById(motorista.getKey())).withSelfRel());
		return motorista;
	}	
	
	@ApiOperation(value = "Excluir motorista por Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		motoristaService.delete(id);
		return ResponseEntity.ok().build();
	}	

}
