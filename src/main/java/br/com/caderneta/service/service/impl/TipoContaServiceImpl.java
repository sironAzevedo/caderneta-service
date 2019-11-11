package br.com.caderneta.service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.caderneta.service.common.exceptions.EmptyResultDataAccessException;
import br.com.caderneta.service.models.dto.TipoContaDTO;
import br.com.caderneta.service.models.entity.TipoContaEntity;
import br.com.caderneta.service.repository.ITipoContaRepository;
import br.com.caderneta.service.service.ITipoContaService;

@Service
public class TipoContaServiceImpl implements ITipoContaService {

	@Autowired
	private ITipoContaRepository repo;

	@Override
	@Cacheable("tipoConta")
	public List<TipoContaDTO> buscarTodos() {
		return repo.findAll().stream().map(tipo -> 
			TipoContaDTO.builder()
				.codigo(tipo.getCodigo())
				.tipo(tipo.getTipo())
				.descricao(tipo.getDescricao()).
				build()).collect(Collectors.toList());
	}

	@Override
	public TipoContaDTO buscarTipoConta(Long codigo) {
		TipoContaEntity obj = repo.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException("Tipo não encontrado"));
		return TipoContaDTO.builder()
				.codigo(obj.getCodigo())
				.tipo(obj.getTipo())
				.descricao(obj.getDescricao())
				.build();
	} 
}
