package br.com.caderneta.service.configuracao.email;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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
public class EmailDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Email(message="Email inválido")
	@NotEmpty(message="Preenchimento obrigatório")
	private String email;

}
