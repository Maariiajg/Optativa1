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

import com.daw_pizza.persistence.entities.Cliente;
import com.daw_pizza.persistence.entities.Pizza;
import com.daw_pizza.services.ClienteService;
import com.daw_pizza.services.exceptions.ClienteException;
import com.daw_pizza.services.exceptions.ClienteNotFoundException;
import com.daw_pizza.services.exceptions.PizzaNotFoundException;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> list(){
		return ResponseEntity.ok(this.clienteService.findAll());
	}
	
	@GetMapping("/{idCliente}")
	public ResponseEntity<?> findById(@PathVariable int idCliente){
		try {
			return ResponseEntity.ok(this.clienteService.findById(idCliente));
		}
		catch(ClienteNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Cliente cliente){
//		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.clienteService.create(cliente));
//		}
//		catch(PizzaException ex) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//		}
	}
	
	@PutMapping("/{idCliente}")
	public ResponseEntity<?> update(@PathVariable int idCliente, @RequestBody Cliente cliente){
		try {
			return ResponseEntity.ok(this.clienteService.update(idCliente, cliente));
		}
		catch(ClienteNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch(ClienteException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<?> delete(@PathVariable int idCliente){
		try {
			this.clienteService.deleteById(idCliente);
			return ResponseEntity.ok().build();
		}
		catch(ClienteNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	// Buscar cliente por teléfono
	@GetMapping("/telefono/{telefono}")
	public ResponseEntity<?> findByTelefono(@PathVariable String telefono) {
	    try {
	        return ResponseEntity.ok(this.clienteService.findByTelefono(telefono));
	    } catch (ClienteNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	// Modificar dirección de envío
	@PutMapping("/{idCliente}/direccion")
	public ResponseEntity<?> cambiarDireccion(
	        @PathVariable int idCliente,
	        @RequestBody String nuevaDireccion) {

	    try {
	        return ResponseEntity.ok(this.clienteService.modificarDireccion(idCliente, nuevaDireccion));
	    } catch (ClienteNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	
}
