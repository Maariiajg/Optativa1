package com.pokemon.pokedaw.persistence.entities;

import java.time.LocalDate;

import com.pokemon.pokedaw.persistence.entities.enums.Captura;
import com.pokemon.pokedaw.persistence.entities.enums.Tipo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Pokemon")
@Getter
@Setter
public class Pokemons {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int numeroPokedex;
	private String titulo;
	
	@Enumerated(value = EnumType.STRING)
	private Tipo tipo1;
	
	@Enumerated(value = EnumType.STRING)
	private Tipo tipo2;
	
	@Column(name = "fecha_captura")
	private LocalDate fechaCaptura;
	
	@Enumerated(value = EnumType.STRING)
	private Captura capturado;
	
}
