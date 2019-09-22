package br.com.caderneta.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.caderneta.service.configuracao.seguranca.User;
import br.com.caderneta.service.models.entity.UsuarioEntity;
import br.com.caderneta.service.repository.IUsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		UsuarioEntity user = repository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));

		return User.build(user);
	}
}
