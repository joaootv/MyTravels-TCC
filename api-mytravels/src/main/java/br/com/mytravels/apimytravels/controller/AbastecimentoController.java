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

import br.com.mytravels.apimytravels.data.dto.AbastecimentoDTO;
import br.com.mytravels.apimytravels.data.model.Abastecimento;
import br.com.mytravels.apimytravels.services.AbastecimentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Abastecimentos") 
@RestController
@RequestMapping("/api/abastecimentos")
public class AbastecimentoController {

	@Autowired
	private AbastecimentoService abastecimentoService;
	
	@ApiOperation(value = "Listar todas as abastecidas" )
	@GetMapping(produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Abastecimento>> findAll() {
		List<Abastecimento> list = abastecimentoService.findAll();
		list.stream().forEach(p -> p.add(
					linkTo(methodOn(DespesasController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value = "Buscar uma abastecida específica através do ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Abastecimento> findById(@PathVariable("id") Long id) {
		Abastecimento abastecimento = abastecimentoService.findById(id);
		return ResponseEntity.ok().body(abastecimento);
	}
	
	@ApiOperation(value = "Listar todos os abastecimentos por viagem" )
	@GetMapping(value = "/viagem/{id}",produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Abastecimento>> findAll(@PathVariable("id") Long id) {
		List<Abastecimento> list = abastecimentoService.findByViagem(id);
		list.stream().forEach(p -> p.add(
					linkTo(methodOn(AbastecimentoController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value = "Cadastrar novo Abastecimento")
	@PostMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public ResponseEntity<Void> create(@Valid @RequestBody AbastecimentoDTO obj) {
		Abastecimento abastecimento = abastecimentoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(abastecimento.getKey()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Alteração do Abastecimento")
	@PutMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public Abastecimento update(@RequestBody AbastecimentoDTO obj) {
		Abastecimento abastecimento = abastecimentoService.update(obj);
		abastecimento.add(linkTo(methodOn(AbastecimentoController.class).findById(abastecimento.getKey())).withSelfRel());
		return abastecimento;
	}	
	
	@ApiOperation(value = "Excluir abastecimento por Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		abastecimentoService.delete(id);
		return ResponseEntity.ok().build();
	}
}
