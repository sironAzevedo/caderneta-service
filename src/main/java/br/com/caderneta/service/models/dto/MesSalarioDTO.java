package br.com.caderneta.service.models.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MesSalarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Long codigo;
	
	@Getter
	@Setter
	@NotNull(message = "O campo valor do salario Ã© de preenchimento obrigatorio")
	@Column(precision = 10, scale = 2)
	private String valorSalario;

	@Getter
	@Setter
	private MesDTO mes;
	
	@Getter
	@Setter
	private UsuarioDTO usuario;

	@Getter
	@Setter
	private List<ContaDTO> contas;
 
}
