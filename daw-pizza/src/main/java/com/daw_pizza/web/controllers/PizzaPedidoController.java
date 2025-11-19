package com.daw_pizza.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw_pizza.persistence.entities.Pizza;
import com.daw_pizza.persistence.entities.PizzaPedido;
import com.daw_pizza.persistence.repositories.PizzaPedidoRepository;

@RestController
@RequestMapping("/pizzapedidos")
public class PizzaPedidoController {
	@Autowired
    private PizzaPedidoRepository pizzaPedidoRepository;
    
	@GetMapping
	public ResponseEntity<List<PizzaPedido>> list(){
		return ResponseEntity.ok(this.pizzaPedidoRepository.findAll());
	}
}
