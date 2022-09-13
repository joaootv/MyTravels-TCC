package br.com.mytravels.apimytravels.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

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

import br.com.mytravels.apimytravels.data.dto.PedagioDTO;
import br.com.mytravels.apimytravels.data.model.Pedagio;
import br.com.mytravels.apimytravels.services.PedagioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Pedagio") 
@RestController
@RequestMapping("/api/pedagio")
public class PedagioController {

	@Autowired
	private PedagioService pedagioService;
	
	@ApiOperation(value = "Listar todos os pedagios" )
	@GetMapping(produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Pedagio>> findAll() {
		List<Pedagio> list = pedagioService.findAll();
		list.stream().forEach(p -> p.add(
					linkTo(methodOn(PedagioController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value = "Buscar um pedagio específico através do ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Pedagio> findById(@PathVariable("id") Long id) {
		Pedagio pedagio = pedagioService.findById(id);
		return ResponseEntity.ok().body(pedagio);
	}
	
	@ApiOperation(value = "Listar todos os pedagios por viagem" )
	@GetMapping(value = "/viagem/{id}",produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Pedagio>> findAll(@PathVariable("id") Long id) {
		List<Pedagio> list = pedagioService.findByViagem(id);
		list.stream().forEach(p -> p.add(
					linkTo(methodOn(PedagioController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value = "Cadastrar novo Pedagio")
	@PostMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public ResponseEntity<Void> create(@Valid @RequestBody PedagioDTO obj) {
		Pedagio pedagio = pedagioService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pedagio.getKey()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Alteração do Pedagio")
	@PutMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public Pedagio update(@RequestBody PedagioDTO obj) {
		Pedagio pedagio = pedagioService.update(obj);
		pedagio.add(linkTo(methodOn(PedagioController.class).findById(pedagio.getKey())).withSelfRel());
		return pedagio;
	}	
	
	@ApiOperation(value = "Excluir pedagio por Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		pedagioService.delete(id);
		return ResponseEntity.ok().build();
	}
}
