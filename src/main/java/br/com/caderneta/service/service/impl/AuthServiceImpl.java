package br.com.caderneta.service.service.impl;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.caderneta.service.common.exceptions.UserException;
import br.com.caderneta.service.models.entity.UsuarioEntity;
import br.com.caderneta.service.repository.IUsuarioRepository;
import br.com.caderneta.service.service.IAuthService;
import br.com.caderneta.service.service.IEmailService;

@Service
public class AuthServiceImpl implements IAuthService {

	@Autowired
	private IUsuarioRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private IEmailService emailService;

	private Random rand = new Random();

	@Override
	public void sendNewPassword(String email) {
		UsuarioEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("Usuario não encontrado"));
		
		String newPass = newPassword();
		user.setSenha(pe.encode(newPass));
		user.setUpdatedAt(new Date());
		userRepository.saveAndFlush(user);
		emailService.sendNewPasswordEmail(user.getEmail() , newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		} else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
