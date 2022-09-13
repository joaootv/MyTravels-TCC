package br.com.mytravels.apimytravels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mytravels.apimytravels.data.model.Cidade;
import br.com.mytravels.apimytravels.services.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Cidade") 
@RestController
@RequestMapping("/api/cidade/v1")
public class CidadeController {

	@Autowired
	private CidadeService service;
	
	@ApiOperation(value = "Listar todas as cidades" )
	@GetMapping(produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Cidade>> findAll() {
		List<Cidade> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Buscar uma cidade específica através do ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Cidade> findById(@PathVariable("id") Long id) {
		Cidade cidade = service.findById(id);
		return ResponseEntity.ok().body(cidade);
	}
	
}
