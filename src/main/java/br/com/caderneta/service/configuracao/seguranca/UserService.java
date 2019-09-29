package br.com.caderneta.service.configuracao.seguranca;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.caderneta.service.common.exceptions.UserException;

public final class UserService {

	public static User authenticated() {
		try {
			return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			throw new UserException(e.getLocalizedMessage());
		}
	}

	private UserService() {
		super();
	} 
}
