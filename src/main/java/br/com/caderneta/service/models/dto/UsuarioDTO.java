package br.com.caderneta.service.models.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Long codigo;

	@Getter
	@Setter
	@NotEmpty(message = "Campo Obrigatorio")
	private String nome; 

	@Email
	@Getter
	@Setter
	@NotEmpty(message = "Campo Obrigatorio")
	private String email;

	@Getter
	@Setter
	@NotEmpty(message = "Campo Obrigatorio")
	private String senha;
	
	
//	@Getter
//	@Setter
//	private List<Role> roles; 
}
