package com.pokemon.pokedaw.persistence.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.pokemon.pokedaw.persistence.entities.Pokemon;

public interface PokeRepository extends ListCrudRepository<Pokemon, Integer>{

}
