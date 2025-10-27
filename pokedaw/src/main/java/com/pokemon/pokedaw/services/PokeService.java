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
			throw new PokeException("El tipo 1 no puede ser igual al tipo 2");
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
	  	if (pokemon.getId() != idPokemon) {
			throw new PokeException(
					String.format("El id del body (%d) y el id del path (%d) no coinciden", pokemon.getId(), idPokemon));
		}
        // No se permite cambiar fecha ni captura
        if (pokemon.getFechaCaptura() != null || pokemon.getCapturado() != null) {
            throw new PokeException("No se puede modificar la fecha o tipo de captura");
        }

        // No se permite cambiar tipos aquí
        if (pokemon.getTipo1() != null || pokemon.getTipo2() != null) {
            throw new PokeException("Los tipos no se pueden modificar con este endpoint");
        }
        
        
        Pokemon pokemonBD = this.findById(idPokemon);
        pokemonBD.setTitulo(pokemon.getTitulo());
        pokemonBD.setNumeroPokedex(pokemon.getNumeroPokedex());

        return this.pokeRepository.save(pokemonBD);
    }
	
	//borrar un pokemon
	public void delete(int idTarea) {
		if (!this.pokeRepository.existsById(idTarea)) {
			throw new PokeNotFoundException("La tarea no existe");
		}
		this.pokeRepository.deleteById(idTarea);
	}
	
	
	//Buscar pokemon por numero pokedex
	public List<Pokemon> findByPokedex(int numeroPokedex) {
        return this.pokeRepository.findByPokedex(numeroPokedex);
    }
	
	
	//Buscar los pokemon capturados en un rango de fechas.
	public List<Pokemon> findByFechaCapturaBetween(LocalDate fechaInicio, LocalDate fechaFin) {
        return this.pokeRepository.findByFechaCapturaBetween(fechaInicio, fechaFin);
    }
	
	//Buscar pokemon de un tipo determinado (tipo1 o tipo2)
	public List<Pokemon> findByTipo(Tipo tipo) {
	    return pokeRepository.findByTipo1OrTipo2(tipo, tipo);
	}

	
	// Cambiar el tipo de un Pokémon
	public Pokemon cambiarTipo(int idPokemon, Tipo tipo1, Tipo tipo2) {
	    Pokemon pokemonBD = this.findById(idPokemon);

	    // Si no se envía tipo1 o tipo2, asignamos NINGUNO
	    Tipo nuevoTipo1 = (tipo1 != null) ? tipo1 : Tipo.NINGUNO;
	    Tipo nuevoTipo2 = (tipo2 != null) ? tipo2 : Tipo.NINGUNO;

	    // Validación: no pueden ser iguales
	    if (nuevoTipo1 == nuevoTipo2) {
	        throw new PokeException("El tipo1 no puede ser igual al tipo2");
	    }

	    pokemonBD.setTipo1(nuevoTipo1);
	    pokemonBD.setTipo2(nuevoTipo2);

	    return this.pokeRepository.save(pokemonBD);
	}


	
}
