package br.com.caderneta.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caderneta.service.configuracao.email.EmailConfig;
import br.com.caderneta.service.configuracao.email.Mensagem;
import br.com.caderneta.service.service.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService {
	
	@Autowired
	private EmailConfig email;
	
	@Override
	public void sendEmail(Mensagem msg) {
		email.sendEmail(msg);
	}

	@Override
	public void sendNewPasswordEmail(String email, String newPass) {
		Mensagem msg = Mensagem.builder()
				.destinatario(email)
				.assunto("Solicitação de nova senha")
				.texto("Nova senha: " + newPass)
				.html(Boolean.FALSE)
				.anexo(Boolean.FALSE)
				.build();
		this.sendEmail(msg);
	}
}
