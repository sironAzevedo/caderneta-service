package br.com.caderneta.service.models.dto;

import java.io.Serializable;

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
public class StatusContaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Long codigo;

	@Getter
	@Setter
	private String descricao;

}
