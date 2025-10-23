package com.pokemon.pokedaw.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.pokedaw.persistence.entities.Pokemon;
import com.pokemon.pokedaw.services.PokeService;
import com.pokemon.pokedaw.services.exceptions.PokeException;

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
	
	@GetMapping
	public ResponseEntity<?> create(@RequestBody Pokemon pokemon){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.pokeService.create(pokemon));
		}catch (PokeException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
}
