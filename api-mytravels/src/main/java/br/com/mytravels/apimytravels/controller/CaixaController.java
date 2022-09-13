package br.com.mytravels.apimytravels.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mytravels.apimytravels.data.dto.CaixaDTO;
import br.com.mytravels.apimytravels.data.model.CaixaVeiculo;
import br.com.mytravels.apimytravels.services.CaixaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Caixa Veículo") 
@RestController
@RequestMapping("/api/veiculo/caixa")
public class CaixaController {
	
	@Autowired
	private CaixaService caixaService;
	
	@ApiOperation(value = "Buscar um caixa por veiculo" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<CaixaVeiculo> findById(@PathVariable("id") Long id) {
		CaixaVeiculo caixaVeiculo = caixaService.findByVeiculo(id);
		return ResponseEntity.ok().body(caixaVeiculo);
	}
	
	@ApiOperation(value = "Abrir caixa pro veiculo")
	@PostMapping(produces = { "application/json", "application/xml"}, 
			consumes = { "application/json", "application/xml"})
	public CaixaVeiculo create(@RequestBody CaixaDTO caixaVeiculo) {
		CaixaVeiculo caixa = caixaService.create(caixaVeiculo);
		return caixa;
	}
	
	@ApiOperation(value = "Alteração do saldo" )
	@PatchMapping(produces = { "application/json", "application/xml" })
	public CaixaVeiculo updateSaldo(@RequestBody CaixaDTO caixaVeiculo) {
		CaixaVeiculo caixa = caixaService.updateSaldo(caixaVeiculo);
		caixa.add(linkTo(methodOn(CaixaController.class).findById(caixa.getKey())).withSelfRel());
		return caixa;
	}

}
