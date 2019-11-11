package br.com.caderneta.service.common.exceptions.exception;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Integer status;
	
	@Getter
	@Setter
	private String mensagem;
	
	@Getter
	@Setter
	private String metodo;
	
	@Getter
	@Setter
	private String path;
	
	@Getter
	@Setter
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo")
	private Date timestamp; 
}
