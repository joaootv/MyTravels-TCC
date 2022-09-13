package br.com.mytravels.apimytravels.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

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

import br.com.mytravels.apimytravels.data.model.TipoVeiculo;
import br.com.mytravels.apimytravels.services.TipoVeiculoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Tipo Veiculo") 
@RestController
@RequestMapping("/api/tipoVeiculo/v1")
public class TipoVeiculoController {
	
	@Autowired
	private TipoVeiculoService service;
	
	@ApiOperation(value = "Listar todas os tipos de veiculos" )
	@GetMapping(produces = { "application/json", "application/xml" })
	public ResponseEntity<List<TipoVeiculo>> findAll() {
		List<TipoVeiculo> list = service.findAll();
		list.stream().forEach(p -> p.add(
					linkTo(methodOn(TipoVeiculoController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Buscar uma tipo de veiculo específico através do ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Optional<TipoVeiculo>> findById(@PathVariable("id") Long id) {
		Optional<TipoVeiculo> tipo = service.findById(id);
		return ResponseEntity.ok().body(tipo);
	}
	
	@ApiOperation(value = "Cadastrar novo tipo de Veiculo")
	@PostMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public TipoVeiculo create(@RequestBody TipoVeiculo tipoVeiculo) {
		TipoVeiculo tipo = service.create(tipoVeiculo);
		tipo.add(linkTo(methodOn(TipoVeiculoController.class).findById(tipo.getKey())).withSelfRel());
		return tipo;
	}
	
	@ApiOperation(value = "Alteração no cadastro do tipo de veiculo")
	@PutMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public TipoVeiculo update(@RequestBody TipoVeiculo obj) {
		TipoVeiculo tipo = service.update(obj);
		tipo.add(linkTo(methodOn(PessoaController.class).findById(tipo.getKey())).withSelfRel());
		return tipo;
	}	
	
	@ApiOperation(value = "Excluir tipo de veiculo por Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}	

}
