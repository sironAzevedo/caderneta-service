package br.com.caderneta.service.models.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ContaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Long codigo;

	@Getter
	@Setter
	@NotEmpty(message = "Campo Obrigatorio")
	private String valorConta;

	@Getter
	@Setter
	@NotEmpty(message = "Campo Obrigatorio")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date dataVencimento;

	@Getter
	@Setter
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date dataPagamento;
	
	@Getter
	@Setter
	@NotEmpty(message = "Campo Obrigatorio")
	private StatusContaDTO status;
	
	@Getter
	@Setter
	private Integer qtdParcelas;

	@Getter
	@Setter
	private String comentario;

	@Getter
	@Setter
	@NotEmpty(message = "Campo Obrigatorio")
	private MesDTO mes;

	@Getter
	@Setter
	@NotEmpty(message = "Campo Obrigatorio")
	private TipoContaDTO tipoConta;
}
