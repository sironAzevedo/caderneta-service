package br.com.caderneta.service.service;

import br.com.caderneta.service.configuracao.email.Mensagem;

public interface IEmailService {

	void sendEmail(Mensagem msg);

	void sendNewPasswordEmail(String email, String newPass);

}
