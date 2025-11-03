package com.registro.sevices.exceptions;

public class RegistroNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 8954845870461706054L;

	public RegistroNotFoundException(String message) {
		super(message);
	}
}