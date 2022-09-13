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

import br.com.mytravels.apimytravels.data.dto.DespesaDTO;
import br.com.mytravels.apimytravels.data.model.Despesas;
import br.com.mytravels.apimytravels.services.DespesasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Despesas") 
@RestController
@RequestMapping("/api/despesas")
public class DespesasController {

	@Autowired
	private DespesasService despesasService;
	
	@ApiOperation(value = "Listar todas as despesas" )
	@GetMapping(produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Despesas>> findAll() {
		List<Despesas> list = despesasService.findAll();
		list.stream().forEach(p -> p.add(
					linkTo(methodOn(DespesasController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value = "Buscar uma despesa específica através do ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Despesas> findById(@PathVariable("id") Long id) {
		Despesas despesa = despesasService.findById(id);
		return ResponseEntity.ok().body(despesa);
	}
	
	@ApiOperation(value = "Listar todas as despesas por viagem" )
	@GetMapping(value = "/viagem/{id}",produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Despesas>> findAll(@PathVariable("id") Long id) {
		List<Despesas> list = despesasService.findByViagem(id);
		list.stream().forEach(p -> p.add(
					linkTo(methodOn(DespesasController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value = "Cadastrar nova Despesa")
	@PostMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public ResponseEntity<Void> create(@Valid @RequestBody DespesaDTO obj) {
		Despesas despesas = despesasService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(despesas.getKey()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Alteração da Despesa")
	@PutMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public Despesas update(@RequestBody DespesaDTO obj) {
		Despesas despesa = despesasService.update(obj);
		despesa.add(linkTo(methodOn(DespesasController.class).findById(despesa.getKey())).withSelfRel());
		return despesa;
	}	
	
	@ApiOperation(value = "Excluir abastecimento por Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		despesasService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
