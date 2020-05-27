package br.com.caderneta.service.service;

import br.com.caderneta.service.models.dto.MensagemDTO;

public interface IEmailService {

	void sendEmail(MensagemDTO msg);

	void sendNewPasswordEmail(String email, String newPass);

}
