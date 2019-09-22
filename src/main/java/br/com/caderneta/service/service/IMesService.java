package br.com.caderneta.service.service;

import java.util.List;

import br.com.caderneta.service.models.dto.MesDTO;

public interface IMesService {

    List<MesDTO> findAll();
    
    MesDTO buscarPorCodigo(Long codigo);

	List<MesDTO> buscarMesesPorFiltro();
}
