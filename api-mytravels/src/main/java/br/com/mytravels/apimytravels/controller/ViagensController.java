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

import br.com.mytravels.apimytravels.data.dto.ViagemDTO;
import br.com.mytravels.apimytravels.data.dto.ViagensNewDTO;
import br.com.mytravels.apimytravels.data.model.Viagens;
import br.com.mytravels.apimytravels.services.ViagensService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Viagens") 
@RestController
@RequestMapping("/api/viagens")
public class ViagensController {
	
	@Autowired
	private ViagensService viagensService;
	
	@ApiOperation(value = "Listar todas as viagens" )
	@GetMapping(produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Viagens>> findAll() {
		List<Viagens> list = viagensService.findAll();
		list.stream().forEach(p -> p.add(
					linkTo(methodOn(ViagensController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value = "Buscar uma viagem específica através do ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Optional<Viagens>> findById(@PathVariable("id") Long id) {
		Optional<Viagens> viagens = viagensService.findById(id);
		return ResponseEntity.ok().body(viagens);
	}
	
	@ApiOperation(value = "Listar todas as viagens por transportadora" )
	@GetMapping(value = "/trasnportadora/{id}",produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Viagens>> findAll(@PathVariable("id") Long id) {
		List<Viagens> list = viagensService.findByTransportadora(id);
		list.stream().forEach(p -> p.add(
					linkTo(methodOn(ViagensController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value = "Listar todas as viagens por transportadora" )
	@GetMapping(value = "/motorista/{id}",produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Viagens>> findByMotorista(@PathVariable("id") Long id) {
		List<Viagens> list = viagensService.findByMotorista(id);
		list.stream().forEach(p -> p.add(
					linkTo(methodOn(ViagensController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value = "Cadastrar nova Viagem")
	@PostMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public ResponseEntity<Void> create(@Valid @RequestBody ViagensNewDTO obj) {
		Viagens viagem = viagensService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(viagem.getKey()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Alteração da Viagem")
	@PutMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public Viagens update(@RequestBody ViagemDTO obj) {
		Viagens viagem = viagensService.update(obj);
		viagem.add(linkTo(methodOn(ViagensController.class).findById(viagem.getKey())).withSelfRel());
		return viagem;
	}	
	
	@ApiOperation(value = "Excluir viagem por Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		viagensService.delete(id);
		return ResponseEntity.ok().build();
	}	

}
