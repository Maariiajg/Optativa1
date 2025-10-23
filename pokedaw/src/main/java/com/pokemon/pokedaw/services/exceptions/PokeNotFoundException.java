package com.pokemon.pokedaw.services.exceptions;

public class PokeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -1040591088342996302L;

	public PokeNotFoundException(String message) {
		super(message);
	}
}
