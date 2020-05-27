package br.com.caderneta.service.service.impl;

import static br.com.caderneta.service.common.util.EmailUtil.getAddress;
import static br.com.caderneta.service.common.util.EmailUtil.getAnexo;
import static br.com.caderneta.service.common.util.EmailUtil.transport;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caderneta.service.common.exceptions.EmailException;
import br.com.caderneta.service.models.dto.MensagemDTO;
import br.com.caderneta.service.service.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService {

	@Autowired
	private Session session;

	@Override
	public void sendEmail(MensagemDTO dto) {

		try {
			Message msg = new MimeMessage(session);
			msg.setSubject(dto.getAssunto());
			msg.setFrom(new InternetAddress(session.getProperty("from")));

			if (!dto.getDestinatarios().isEmpty()) {
				msg.setRecipients(Message.RecipientType.TO, getAddress(dto.getDestinatarios()));
			} else {
				msg.setRecipient(Message.RecipientType.TO, new InternetAddress(dto.getDestinatario()));
			}

			String anexo = null;

			if (dto.isAnexo()) {
				anexo = "C:\\Users\\Desenvolvimento\\Documents\\SIRON AZEVEDO SANTOS DA SILVA.pdf";
				msg.setContent(getAnexo(msg, dto.getTexto(), anexo));
			} else {
				msg.setText(dto.getTexto());
			}

			transport(session, msg, session.getProperty("protocol"), session.getProperty("host"),
					session.getProperty("userName"), session.getProperty("password"));
		} catch (Exception e) {
			throw new EmailException("E-mail não enviado: " + e.getLocalizedMessage());
		}
	}

	@Override
	public void sendNewPasswordEmail(String email, String newPass) {
		MensagemDTO msg = MensagemDTO
				.builder()
				.destinatario(email)
				.assunto("Solicitação de nova senha")
				.texto("Nova senha: " + newPass)
				.html(Boolean.FALSE)
				.anexo(Boolean.FALSE)
				.build();
		this.sendEmail(msg);
	}
}
