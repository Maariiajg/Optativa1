package com.pokemon.pokedaw.persistence.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.pokemon.pokedaw.persistence.entities.Pokemon;
import com.pokemon.pokedaw.persistence.entities.enums.Tipo;

public interface PokeRepository extends ListCrudRepository<Pokemon, Integer> {

    @Query("SELECT p FROM Pokemon p WHERE p.numeroPokedex = :numero")
    List<Pokemon> findByPokedex(@Param("numero") int numeroPokedex);

    List<Pokemon> findByFechaCapturaBetween(LocalDate fechaInicio, LocalDate fechaFin);

    // Nuevo método para tipo1 o tipo2
    List<Pokemon> findByTipo1OrTipo2(Tipo tipo1, Tipo tipo2);
}
