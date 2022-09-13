package br.com.mytravels.apimytravels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mytravels.apimytravels.data.model.User;
import br.com.mytravels.apimytravels.services.UserServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Users") 
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserServices service;
	
	@ApiOperation(value = "Listar todos os usuarios por transportadora" )
	@GetMapping(value = "/trasnportadora/{id}",produces = { "application/json", "application/xml" })
	public ResponseEntity<List<User>> findAll(@PathVariable("id") Long id) {
		List<User> list = service.findByTransportadora(id);
		return ResponseEntity.ok().body(list);
	}
}
