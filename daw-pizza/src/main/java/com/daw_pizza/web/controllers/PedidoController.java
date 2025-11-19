package com.daw_pizza.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw_pizza.persistence.entities.Pedido;
import com.daw_pizza.services.PedidoService;
import com.daw_pizza.services.exceptions.PedidoException;
import com.daw_pizza.services.exceptions.PedidoNotFoundException;
import com.daw_pizza.services.exceptions.PizzaNotFoundException;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> list(){
		return ResponseEntity.ok(this.pedidoService.findAll());
	}
	
	@GetMapping("/{idPedido}")
	public ResponseEntity<?> findById(@PathVariable int idPedido){
		try {
			return ResponseEntity.ok(this.pedidoService.findById(idPedido));
		}
		catch(PedidoNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Pedido pedido){
//		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.pedidoService.create(pedido));
//		}
//		catch(PizzaException ex) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//		}
	}
	
	@PutMapping("/{idPedido}")
	public ResponseEntity<?> update(@PathVariable int idPedido, @RequestBody Pedido pedido){
		try {
			return ResponseEntity.ok(this.pedidoService.update(idPedido, pedido));
		}
		catch(PedidoNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch(PedidoException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@DeleteMapping("/{idPizza}")
	public ResponseEntity<?> delete(@PathVariable int idPedido){
		try {
			this.pedidoService.deleteById(idPedido);
			return ResponseEntity.ok().build();
		}
		catch(PizzaNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	
	//ejercicio 4
	// Añadir notas al pedido
	@PutMapping("/{idPedido}/notas")
	public ResponseEntity<?> agregarNotas(
	        @PathVariable int idPedido,
	        @RequestBody String notas) {

	    try {
	        return ResponseEntity.ok(this.pedidoService.añadirNotas(idPedido, notas));
	    } catch (PedidoNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	
}
