package br.com.caderneta.service.configuracao.email;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;

public class EmailConfigDTO {
	
	@Getter
	@Value("${mail.smtp.host}")
	private String host;
	
	@Getter
	@Value("${mail.smtp.port}")
	private String port;

	@Getter
	@Value("${mail.smtp.from}")
	private String from;

	@Getter
	@Value("${mail.username}")
	private String userName;

	@Getter
	@Value("${mail.password}")
	private String password;

	@Getter
	@Value("${mail.transport.protocol}")
	private String transportProtocol;

	@Getter
	@Value("${mail.smtp.auth}")
	private String smtpAuth;

	@Getter
	@Value("${mail.smtp.starttls.enable}")
	private String starttlsEnable;
	
	@Getter
	@Value("${mail.smtp.starttls.required}")
	private String starttlsRequired;
	
	@Getter
	@Value("${mail.smtp.ssl.enable}")
	private String smtpSslEnable;
	
	@Getter
	@Value("${mail.test-connection}")
	private String testConnection;
	
	@Getter
	@Value("${mail.smtp.debug}")
	private String smtpDebug;
	
	@Getter
	@Value("${mail.smtp.socketFactory.port}")
	private String smtpSocketFactoryPort;
	
	@Getter
	@Value("${mail.smtp.socketFactory.class}")
	private String smtpSocketFactoryClass;
	
	@Getter
	@Value("${mail.smtp.socketFactory.fallback}")
	private String smtpSocketFactoryFallback;
}
