package br.com.mytravels.apimytravels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mytravels.apimytravels.data.model.Estado;
import br.com.mytravels.apimytravels.services.EstadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Estado") 
@RestController
@RequestMapping("/api/estado/v1")
public class EstadoController {

	@Autowired
	private EstadoService service;
	
	@ApiOperation(value = "Listar todos os estados" )
	@GetMapping(produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Estado>> findAll() {
		List<Estado> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Buscar um estado específico através do ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Estado> findById(@PathVariable("id") Long id) {
		Estado estado = service.findById(id);
		return ResponseEntity.ok().body(estado);
	}
	
}
