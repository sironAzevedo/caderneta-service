package br.com.caderneta.service.configuracao.seguranca;

import org.springframework.security.core.context.SecurityContextHolder;

public final class UserService {

	public static User authenticated() {
		try {
			return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}

	private UserService() {
		super();
	} 
}
