package br.com.caderneta.service.configuracao.email;

import java.io.Serializable;
import java.util.Set;

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
public class Mensagem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Set<EmailDTO> destinatarios;
	
	@Getter
	@Setter
	private String destinatario;

	@Getter
	@Setter
	private String assunto;

	@Getter
	@Setter
	private String texto;

	@Getter
	@Setter
	private boolean html;

	@Getter
	@Setter
	private boolean anexo;
}
