package br.com.caderneta.service.configuracao.email;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.caderneta.service.common.exceptions.EmailException;
import br.com.caderneta.service.configuracao.seguranca.CredenciaisDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class EmailConfig {

	public static final void sendEmail(Mensagem mensagem, EmailConfigDTO config) {
		Message msg = new MimeMessage(getSession(config));

		try {
			msg.setFrom(new InternetAddress(config.getFrom()));
			
			if(mensagem.getDestinatarios() != null) {
				msg.setRecipients(Message.RecipientType.TO, getAddress(mensagem.getDestinatarios()));
			}else {
				msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mensagem.getDestinatario()));
			}
			
			msg.setSubject(mensagem.getAssunto());

			String anexo = null;

			if (mensagem.isAnexo()) {
				anexo = "C:\\Users\\Desenvolvimento\\Documents\\SIRON AZEVEDO SANTOS DA SILVA.pdf";
				msg.setContent(getAnexo(msg, mensagem.getTexto(), anexo));
			} else {
				msg.setText(mensagem.getTexto());
			}

			getTransport(msg, config);
			log.info("E-mail enviado com sucesso");
		} catch (Exception e) {
			throw new EmailException("E-mail não enviado: " + e.getLocalizedMessage());
		}
	}

	protected static Session getSession(EmailConfigDTO config) {
		Session session = Session.getInstance(emailProperties(config), auth(config));
		session.setDebug(true);
		return session;
	}

	protected static Authenticator auth(EmailConfigDTO config) {
		return new CredenciaisDTO(config.getUserName(), config.getPassword());
	}

	protected static Address[] getAddress(Set<EmailDTO> destinatarios) throws AddressException {
		List<EmailDTO> destinatario = new ArrayList<>(destinatarios);
		InternetAddress[] address = new InternetAddress[destinatario.size()];
		for (int i = 0; i < destinatario.size(); i++) {
			address[i] = new InternetAddress(destinatario.get(i).getEmail());
		}
		return address;
	}

	protected static Multipart getAnexo(Message msg, String mensagem, String anexo) {
		MimeBodyPart mpb = new MimeBodyPart();
		Multipart mp = new MimeMultipart();
		MimeBodyPart mbpAnexo = new MimeBodyPart();

		try {
			mpb.setText(mensagem);
			mp.addBodyPart(mpb);
			File file = new File(anexo);
			mbpAnexo.setDataHandler(new DataHandler(new FileDataSource(file)));
			mbpAnexo.setFileName(file.getName());
			mp.addBodyPart(mbpAnexo);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return mp;
	}

	protected static void getTransport(Message msg, EmailConfigDTO config) {
		Transport transport;

		try {
			transport = getSession(config).getTransport(config.getTransportProtocol());
			transport.connect(config.getHost(), config.getUserName(), config.getPassword());
			msg.saveChanges();
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			throw new EmailException("E-mail não enviado" + e.getLocalizedMessage());
		}
	}

	protected static Properties emailProperties(EmailConfigDTO config) {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", config.getHost());
		prop.put("mail.smtp.port", config.getPort());
		prop.put("mail.smtp.user", config.getUserName());
		prop.put("mail.transport.protocol", config.getTransportProtocol());
		prop.put("mail.smtp.auth", config.getSmtpAuth());
		prop.put("mail.smtp.starttls.enable", config.getStarttlsEnable());
		prop.put("mail.smtp.socketFactory.port", config.getSmtpSocketFactoryPort());
		prop.put("mail.smtp.socketFactory.class", config.getSmtpSocketFactoryClass());
		prop.put("mail.smtp.socketFactory.fallback", config.getSmtpSocketFactoryFallback());
		prop.put("mail.smtp.debug", config.getSmtpDebug());
		return prop;
	}

	private EmailConfig() {
		super();
	}
}
