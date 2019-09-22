package br.com.caderneta.service.service;

import java.util.List;

import br.com.caderneta.service.models.dto.TipoContaDTO;

public interface ITipoContaService {
	
	List<TipoContaDTO> buscarTodos();

	TipoContaDTO buscarPorCodigo(Long codigo);
}
