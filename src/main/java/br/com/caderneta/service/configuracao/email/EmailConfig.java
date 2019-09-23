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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.caderneta.service.common.exceptions.EmailException;
import br.com.caderneta.service.configuracao.seguranca.CredenciaisDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmailConfig {

	@Value("${mail.smtp.host}")
	private String host;
	@Value("${mail.smtp.port}")
	private String port;
	@Value("${mail.smtp.from}")
	private String from;
	@Value("${mail.username}")
	private String userName;
	@Value("${mail.password}")
	private String password;
	@Value("${mail.transport.protocol}")
	private String transportProtocol;
	@Value("${mail.smtp.auth}")
	private String smtpAuth;
	@Value("${mail.smtp.starttls.enable}")
	private String starttlsEnable;
	@Value("${mail.smtp.starttls.required}")
	private String starttlsRequired;
	@Value("${mail.smtp.ssl.enable}")
	private String smtpSslEnable;
	@Value("${mail.test-connection}")
	private String testConnection;
	@Value("${mail.smtp.debug}")
	private String smtpDebug;
	@Value("${mail.smtp.socketFactory.port}")
	private String smtpSocketFactoryPort;
	@Value("${mail.smtp.socketFactory.class}")
	private String smtpSocketFactoryClass;
	@Value("${mail.smtp.socketFactory.fallback}")
	private String smtpSocketFactoryFallback;

	public void sendEmail(Mensagem mensagem) {
		Message msg = new MimeMessage(getSession());

		try {
			msg.setFrom(new InternetAddress(from));

			if (mensagem.getDestinatarios() != null) {
				msg.setRecipients(Message.RecipientType.TO, getAddress(mensagem.getDestinatarios()));
			} else {
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

			getTransport(msg);
			log.info("E-mail enviado com sucesso");
		} catch (Exception e) {
			throw new EmailException("E-mail não enviado: " + e.getLocalizedMessage());
		}
	}

	protected Session getSession() {
		Session session = Session.getInstance(emailProperties(), auth());
		session.setDebug(true);
		return session;
	}

	protected Authenticator auth() {
		return new CredenciaisDTO(userName, password);
	}

	protected Address[] getAddress(Set<EmailDTO> destinatarios) throws AddressException {
		List<EmailDTO> destinatario = new ArrayList<>(destinatarios);
		InternetAddress[] address = new InternetAddress[destinatario.size()];
		for (int i = 0; i < destinatario.size(); i++) {
			address[i] = new InternetAddress(destinatario.get(i).getEmail());
		}
		return address;
	}

	protected Multipart getAnexo(Message msg, String mensagem, String anexo) {
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

	protected void getTransport(Message msg) {
		Transport transport;

		try {
			transport = getSession().getTransport(transportProtocol);
			transport.connect(host, userName, password);
			msg.saveChanges();
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			throw new EmailException("E-mail não enviado" + e.getLocalizedMessage());
		}
	}

	protected Properties emailProperties() {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", port);
		prop.put("mail.smtp.user", userName);
		prop.put("mail.transport.protocol", transportProtocol);
		prop.put("mail.smtp.auth", smtpAuth);
		prop.put("mail.smtp.starttls.enable", starttlsEnable);
		prop.put("mail.smtp.socketFactory.port", smtpSocketFactoryPort);
		prop.put("mail.smtp.socketFactory.class", smtpSocketFactoryClass);
		prop.put("mail.smtp.socketFactory.fallback", smtpSocketFactoryFallback);
		prop.put("mail.smtp.debug", smtpDebug);
		return prop;
	}
}
