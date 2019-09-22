package br.com.caderneta.service.common.exceptions;

public class EmptyResultDataAccessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmptyResultDataAccessException(String message) {
		super(message);
	} 
}
