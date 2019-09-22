package br.com.caderneta.service.configuracao.seguranca;

import java.io.Serializable;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CredenciaisDTO extends Authenticator implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private String email;
	
	@Getter
	@Setter
	private String senha;
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(email, senha);
	}

}
