package com.pokemon.pokedaw.services;



import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemon.pokedaw.persistence.entities.Pokemon;
import com.pokemon.pokedaw.persistence.entities.enums.Captura;
import com.pokemon.pokedaw.persistence.entities.enums.Tipo;
import com.pokemon.pokedaw.persistence.repositories.PokeRepository;
import com.pokemon.pokedaw.services.exceptions.PokeException;
import com.pokemon.pokedaw.services.exceptions.PokeNotFoundException;


@Service
public class PokeService {
	
	@Autowired
	private PokeRepository pokeRepository;
	
	//findAll
	public List<Pokemon> findAll(){
		return this.pokeRepository.findAll();
	}
	
	//findById
	public Pokemon findById(int idPokemon){
		if(!this.pokeRepository.existsById(idPokemon)) {
			throw new PokeNotFoundException("El ID del pokemon " + idPokemon + " no existe");
		}
		return this.pokeRepository.findById(idPokemon).get();
	}
	
	//Crear un pokemon
	public Pokemon create(Pokemon pokemon) {
		if (pokemon.getTipo1().equals(pokemon.getTipo2())) {
			throw new PokeException("El tipo 1 no puede ser igaul al tipo 2");
		}
		
		if(pokemon.getTipo1() == null) {
			pokemon.setTipo1(Tipo.NINGUNO);
		}
		if(pokemon.getTipo2() == null) {
			pokemon.setTipo2(Tipo.NINGUNO);
		}
		
		if(pokemon.getCapturado() == null) {
			pokemon.setCapturado(Captura.POKEBALL);
		}
		
		pokemon.setId(0);
		pokemon.setFechaCaptura(LocalDate.now());

		return this.pokeRepository.save(pokemon);
	}
	
	//modificar un pokemon
	public Pokemon update(Pokemon pokemon, int idPokemon) {
		if(pokemon.getId() != idPokemon) {
			throw new PokeException(
					String.format("El ID del body (%d) no corresponde con el ID del path (%d)", pokemon.getId(), idPokemon));
		}
		
		if(!this.pokeRepository.existsById(idPokemon)) {
			throw new PokeNotFoundException("La tarea con id " + idPokemon + " no existe.");
		}
		
		if(pokemon.getFechaCaptura() != null) {
			throw new PokeException("No se puede modificar la fecha de la captura");
		}
		
		if(pokemon.getCapturado() != null) {
			throw new PokeException("No se puede modificar el modo de captura");
		}
		
		Pokemon pokemonBD = this.findById(idPokemon);
		pokemonBD.setTitulo(pokemon.getTitulo());
		pokemonBD.setTipo1(pokemon.getTipo1());
		pokemonBD.setTipo2(pokemon.getTipo2());

		return this.pokeRepository.save(pokemonBD);
	}
	
	//borrar un pokemon
	public void delete(int idTarea) {
		
	}
	
	
}
