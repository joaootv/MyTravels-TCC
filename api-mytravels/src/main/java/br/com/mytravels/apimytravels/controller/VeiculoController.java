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

import br.com.mytravels.apimytravels.data.dto.VeiculoDTO;
import br.com.mytravels.apimytravels.data.dto.VeiculosNewDTO;
import br.com.mytravels.apimytravels.data.model.Veiculos;
import br.com.mytravels.apimytravels.services.VeiculoServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Veículo") 
@RestController
@RequestMapping("/api/veiculo/v1")
public class VeiculoController {
	
	@Autowired
	private VeiculoServices veiculoServices;
	
	@ApiOperation(value = "Listar todos os veiculos" )
	@GetMapping(produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Veiculos>> findAll() {
		List<Veiculos> list = veiculoServices.findAll();
		list.stream().forEach(p -> p.add(
					linkTo(methodOn(VeiculoController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Buscar um veiculo específico através do ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Optional<Veiculos>> findById(@PathVariable("id") Long id) {
		Optional<Veiculos> veiculos = veiculoServices.findById(id);
		return ResponseEntity.ok().body(veiculos);
	}
	
	@ApiOperation(value = "Buscar um veiculo específico através do ID" )
	@GetMapping(value = "/motorista/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Optional<Veiculos>> findByMotorista(@PathVariable("id") Long id) {
		Optional<Veiculos> veiculos = veiculoServices.findByMotorista(id);
		return ResponseEntity.ok().body(veiculos);
	}
	
	@ApiOperation(value = "Listar todos os veiculos por transportadora" )
	@GetMapping(value = "/trasnportadora/{id}",produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Veiculos>> findAll(@PathVariable("id") Long id) {
		List<Veiculos> list = veiculoServices.findByTransportadora(id);
		list.stream().forEach(p -> p.add(
					linkTo(methodOn(VeiculoController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return ResponseEntity.ok().body(list);
	}	
	
	@ApiOperation(value = "Cadastrar novo veiculo")
	@PostMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public ResponseEntity<Void> create(@Valid @RequestBody VeiculosNewDTO objDto) {
		Veiculos obj = veiculoServices.fromDTO(objDto);
		obj = veiculoServices.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Alteração no cadastro do veiculo")
	@PutMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public Veiculos update(@RequestBody VeiculoDTO obj) {
		Veiculos veiculo = veiculoServices.update(obj);
		veiculo.add(linkTo(methodOn(VeiculoController.class).findById(veiculo.getKey())).withSelfRel());
		return veiculo;
	}	
	
	@ApiOperation(value = "Excluir veiculo por Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		veiculoServices.delete(id);
		return ResponseEntity.ok().build();
	}	

}
