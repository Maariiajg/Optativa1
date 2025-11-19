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

import com.daw_pizza.persistence.entities.Pizza;
import com.daw_pizza.services.PizzaService;
import com.daw_pizza.services.exceptions.PizzaException;
import com.daw_pizza.services.exceptions.PizzaNotFoundException;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;

	@GetMapping
	public ResponseEntity<List<Pizza>> list(){
		return ResponseEntity.ok(this.pizzaService.findAll());
	}
	
	@GetMapping("/{idPizza}")
	public ResponseEntity<?> findById(@PathVariable int idPizza){
		try {
			return ResponseEntity.ok(this.pizzaService.findById(idPizza));
		}
		catch(PizzaNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Pizza pizza){
//		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.pizzaService.create(pizza));
//		}
//		catch(PizzaException ex) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//		}
	}
	
	@PutMapping("/{idPizza}")
	public ResponseEntity<?> update(@PathVariable int idPizza, @RequestBody Pizza pizza){
		try {
			return ResponseEntity.ok(this.pizzaService.update(idPizza, pizza));
		}
		catch(PizzaNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch(PizzaException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@DeleteMapping("/{idPizza}")
	public ResponseEntity<?> delete(@PathVariable int idPizza){
		try {
			this.pizzaService.deleteById(idPizza);
			return ResponseEntity.ok().build();
		}
		catch(PizzaNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	//ejercicio 4
	
	// Pizzas disponibles ordenadas por precio
	@GetMapping("/disponibles")
	public ResponseEntity<List<Pizza>> disponiblesOrdenadas() {
	    return ResponseEntity.ok(this.pizzaService.findDisponiblesOrdenadasPorPrecio());
	}

	// Buscar pizzas por nombre
	@GetMapping("/buscar/{nombre}")
	public ResponseEntity<List<Pizza>> buscarPorNombre(@PathVariable String nombre) {
	    return ResponseEntity.ok(this.pizzaService.buscarPorNombre(nombre));
	}

	// Buscar pizzas que lleven ingrediente
	@GetMapping("/ingrediente/{ing}")
	public ResponseEntity<List<Pizza>> buscarConIngrediente(@PathVariable String ing) {
	    return ResponseEntity.ok(this.pizzaService.buscarQueLlevenIngrediente(ing));
	}

	// Buscar pizzas que NO lleven ingrediente
	@GetMapping("/no-ingrediente/{ing}")
	public ResponseEntity<List<Pizza>> buscarSinIngrediente(@PathVariable String ing) {
	    return ResponseEntity.ok(this.pizzaService.buscarQueNoLlevenIngrediente(ing));
	}

	// Actualizar precio
	@PutMapping("/{idPizza}/precio")
	public ResponseEntity<?> actualizarPrecio(
	        @PathVariable int idPizza,
	        @RequestBody double nuevoPrecio) {

	    try {
	        return ResponseEntity.ok(this.pizzaService.actualizarPrecio(idPizza, nuevoPrecio));
	    } catch (PizzaNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	// Cambiar disponibilidad
	@PutMapping("/{idPizza}/disponible/{valor}")
	public ResponseEntity<?> cambiarDisponibilidad(
	        @PathVariable int idPizza,
	        @PathVariable boolean valor) {

	    try {
	        return ResponseEntity.ok(this.pizzaService.cambiarDisponibilidad(idPizza, valor));
	    } catch (PizzaNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}

	
}