package com.pokemon.pokedaw.web.controllers;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.pokedaw.persistence.entities.Pokemon;
import com.pokemon.pokedaw.persistence.entities.enums.Tipo;
import com.pokemon.pokedaw.services.PokeService;
import com.pokemon.pokedaw.services.exceptions.PokeException;
import com.pokemon.pokedaw.services.exceptions.PokeNotFoundException;

@RestController
@RequestMapping("/pokemons")
public class PokeController {
	
	@Autowired
	private PokeService pokeService;
	
	@GetMapping
	public ResponseEntity<List<Pokemon>> list(){
		return ResponseEntity.ok(this.pokeService.findAll());
	}
	
	@GetMapping("/{idPokemon}")
	public ResponseEntity<?> findById(@PathVariable int idPokemon){
		return ResponseEntity.ok(this.pokeService.findById(idPokemon));
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Pokemon pokemon){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.pokeService.create(pokemon));
		} catch (PokeException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	
	@PutMapping("/{idPokemon}")
	public ResponseEntity<?> update(@PathVariable int idPokemon, @RequestBody Pokemon Pokemon) {
		try {
			return ResponseEntity.ok(this.pokeService.update(Pokemon, idPokemon));
		} catch (PokeNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		} catch (PokeException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}

	@DeleteMapping("/{idPokemon}")
	public ResponseEntity<?> delete(@PathVariable int idPokemon) {

		try {
			this.pokeService.delete(idPokemon);
			return ResponseEntity.ok().build();
		} catch (PokeNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	
	@GetMapping("/pokedex/{numeroPokedex}")
	public ResponseEntity<?> findByPokedex(@PathVariable int numeroPokedex) {
		try {
	        return ResponseEntity.ok(this.pokeService.findByPokedex(numeroPokedex));
	    } catch (PokeNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	} 
	
	
	//Buscar los pokemon capturados en un rango de fechas.
	@GetMapping("/fecha")
	public ResponseEntity<List<Pokemon>> findByFechaCaptura(@RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin) {

	    //LocalDate fechaInicio = LocalDate.parse(inicio);
	    //LocalDate fechaFin = LocalDate.parse(fin);

	    return ResponseEntity.ok(this.pokeService.findByFechaCapturaBetween(fechaInicio, fechaFin));
	}

		
	//Buscar pokemon de un tipo determinado (tipo1 o tipo2)
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<Pokemon>> findByTipo(@PathVariable Tipo tipo) {
	    List<Pokemon> pokemons = pokeService.findByTipo(tipo);
	    return ResponseEntity.ok(pokemons);
	}

	//cambiar tipo
	@PutMapping("/{idPokemon}/tipo")
	public ResponseEntity<?> cambiarTipo(
	        @PathVariable int idPokemon,
	        @RequestParam(required = false) Tipo tipo1,
	        @RequestParam(required = false) Tipo tipo2) {
	    try {
	        return ResponseEntity.ok(this.pokeService.cambiarTipo(idPokemon, tipo1, tipo2));
	    } catch (PokeException ex) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	    } catch (PokeNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}


	
		
	
	
	
}
