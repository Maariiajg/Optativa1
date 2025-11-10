package com.daw_pizza.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw_pizza.persistence.entities.Cliente;
import com.daw_pizza.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> list(){
		return ResponseEntity.ok(this.clienteService.findAll());
	}
}
