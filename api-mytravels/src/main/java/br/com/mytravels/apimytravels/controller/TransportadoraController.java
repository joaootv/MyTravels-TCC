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

import br.com.mytravels.apimytravels.data.dto.TransportadoraDTO;
import br.com.mytravels.apimytravels.data.dto.TransportadoraNewDTO;
import br.com.mytravels.apimytravels.data.model.Transportadora;
import br.com.mytravels.apimytravels.services.TransportadoraServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Transportadora") 
@RestController
@RequestMapping("/api/transportadora/v1")
public class TransportadoraController {
	
	@Autowired
	private TransportadoraServices transportadoraServices;
	
	@ApiOperation(value = "Listar todas as transportadoras" )
	@GetMapping(produces = { "application/json", "application/xml" })
	public ResponseEntity<List<TransportadoraDTO>> findAll() {
		List<Transportadora> list = transportadoraServices.findAll();
		List<TransportadoraDTO> listDto = list.stream().map(obj -> new TransportadoraDTO(obj)).collect(Collectors.toList());
		listDto.stream().forEach(p -> p.add(
					linkTo(methodOn(TransportadoraController.class).findById(p.getKey())).withSelfRel()
				)
			);
		return ResponseEntity.ok().body(listDto);
	}

	@ApiOperation(value = "Buscar uma transportadora específica através do ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Optional<Transportadora>> findById(@PathVariable("id") Long id) {
		Optional<Transportadora> transportadora = transportadoraServices.findById(id);
		return ResponseEntity.ok().body(transportadora);
	}	
	
	@ApiOperation(value = "Cadastrar nova Transportadora")
	@PostMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public ResponseEntity<Void> create(@Valid @RequestBody TransportadoraNewDTO objDto) {
		Transportadora obj = transportadoraServices.fromDTO(objDto);
		obj = transportadoraServices.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Alteração no cadastro da transportadora")
	@PutMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public Transportadora update(@RequestBody TransportadoraDTO obj) {
		Transportadora transportadora = transportadoraServices.update(obj);
		transportadora.add(linkTo(methodOn(TransportadoraController.class).findById(transportadora.getKey())).withSelfRel());
		return transportadora;
	}	
	
	@ApiOperation(value = "Excluir transportadora por Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		transportadoraServices.delete(id);
		return ResponseEntity.ok().build();
	}	
		
}
