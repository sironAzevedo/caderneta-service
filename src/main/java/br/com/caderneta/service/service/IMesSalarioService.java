package br.com.caderneta.service.service;

import java.util.List;

import br.com.caderneta.service.models.dto.MesSalarioDTO;

public interface IMesSalarioService {

	void salvar(MesSalarioDTO dto);
	
	void atualizar(MesSalarioDTO dto);
	
	void deletar(MesSalarioDTO dto);

	public List<MesSalarioDTO> buscarTodos();

	public MesSalarioDTO buscarPorCodigo(MesSalarioDTO dto);

}
