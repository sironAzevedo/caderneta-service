package br.com.caderneta.service.common.exceptions;

public class InternalException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InternalException(String message) {
		super(message);
	} 
}
