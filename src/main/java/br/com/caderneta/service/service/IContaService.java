package br.com.caderneta.service.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import br.com.caderneta.service.models.dto.ContaDTO;
import br.com.caderneta.service.models.dto.StatusContaDTO;

public interface IContaService {

	void salvar(ContaDTO conta);
	
	void atualizar(ContaDTO conta);

	List<ContaDTO> findAll();

	ContaDTO buscarContaPorId(Long id);
	
	List<ContaDTO> buscarContasPorMes(Long mes, Pageable pageable);
	
	List<ContaDTO> busarContasPorStatus(Long status, Long mes, Pageable pageable);
	
	List<StatusContaDTO> buscarStatusConta();

	void deletar(ContaDTO dto);

}
