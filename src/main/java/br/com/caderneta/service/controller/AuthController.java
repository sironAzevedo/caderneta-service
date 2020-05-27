package br.com.caderneta.service.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.caderneta.service.configuracao.seguranca.User;
import br.com.caderneta.service.configuracao.seguranca.UserService;
import br.com.caderneta.service.configuracao.seguranca.jwt.JWTUtil;
import br.com.caderneta.service.models.dto.EmailDTO;
import br.com.caderneta.service.service.IAuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private IAuthService authService;
	
	@ResponseBody
	@PostMapping(value = "/refresh_token")
	@ResponseStatus(value = HttpStatus.OK)
	public void refreshToken(HttpServletResponse response) throws IOException {
		User user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		
		final String bearerToken = "Bearer " + token;
		final String tokenBody = "{\"token\": \"" + bearerToken + "\"}";
		response.setContentType("application/json");
		response.getWriter().append(tokenBody);
		response.addHeader("Authorization", bearerToken);
		response.addHeader("access-control-expose-headers", "Authorization");
	}
	
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	@PostMapping(value = "/forgot", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String novaSenha(@Valid @RequestBody EmailDTO dto) {
		authService.sendNewPassword(dto.getEmail());
		String res = "E-mail enviado com sucesso";
		return "{\"mensagem\": \"" + res + "\"}";
	}
}
