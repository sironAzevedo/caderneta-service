package br.com.caderneta.service.models.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MensagemDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Set<EmailDTO> destinatarios;
	private String destinatario;
	private String assunto;
	private String texto;
	private boolean html;
	private boolean anexo;
}
