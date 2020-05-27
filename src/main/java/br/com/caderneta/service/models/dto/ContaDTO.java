package br.com.caderneta.service.models.dto;

import static br.com.caderneta.service.common.util.CadernetaUtil.formatValor;
import static br.com.caderneta.service.common.util.CadernetaUtil.parseObject;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.caderneta.service.models.entity.ContaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;

	@NotEmpty(message = "Campo Obrigatorio")
	private String valorConta;

	@NotEmpty(message = "Campo Obrigatorio")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "America/Sao_Paulo")
	private Date dataVencimento;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "America/Sao_Paulo")
	private Date dataPagamento;
	
	@NotEmpty(message = "Campo Obrigatorio")
	private StatusContaDTO status;
	
	private Integer qtdParcelas;
	private String comentario;

	@NotEmpty(message = "Campo Obrigatorio")
	private MesDTO mes;

	@NotEmpty(message = "Campo Obrigatorio")
	private TipoContaDTO tipoConta;
	
	public static ContaDTO conta(ContaEntity c) {
		return ContaDTO.builder()
				.codigo(c.getCodigo())
				.valorConta(formatValor(c.getValorConta()))
				.dataVencimento(c.getDataVencimento())
				.dataPagamento(c.getDataPagamento())
				.status((StatusContaDTO) parseObject(c.getStatus(), new StatusContaDTO()))
				.qtdParcelas(c.getQtdParcelas())
				.comentario(c.getComentario())
				.mes(MesDTO.builder().codigo(c.getMes().getCodigo()).build())
				.tipoConta((TipoContaDTO) parseObject(c.getTipoConta(), new TipoContaDTO()))
				.build();
	}
}
