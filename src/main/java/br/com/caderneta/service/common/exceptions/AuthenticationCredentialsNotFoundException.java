package br.com.caderneta.service.common.exceptions;

public class AuthenticationCredentialsNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AuthenticationCredentialsNotFoundException(String message) {
		super(message);
	} 
}
