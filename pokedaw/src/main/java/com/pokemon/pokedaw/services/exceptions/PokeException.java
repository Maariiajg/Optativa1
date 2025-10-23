package com.pokemon.pokedaw.services.exceptions;

public class PokeException extends RuntimeException {
	private static final long serialVersionUID = 8954845870461706054L;

	public PokeException(String message) {
		super(message);
	}
}
