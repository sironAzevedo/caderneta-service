package br.com.caderneta.service.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caderneta.service.common.exceptions.EmptyResultDataAccessException;
import br.com.caderneta.service.models.dto.MesDTO;
import br.com.caderneta.service.models.entity.MesEntity;
import br.com.caderneta.service.repository.IMesRepository;
import br.com.caderneta.service.service.IMesService;

@Service
public class MesServiceImpl implements IMesService {

	@Autowired
	private IMesRepository repo;

	@Override
	public List<MesDTO> findAll() {
		return repo.findAll().stream().map(m -> new MesDTO(m.getCodigo(), m.getDsMes())).collect(Collectors.toList());
	}

	@Override
	public MesDTO buscarPorCodigo(Long codigo) {
		Optional<MesEntity> obj = Optional.ofNullable(repo.findById(codigo))
				.orElseThrow(() -> new EmptyResultDataAccessException("Mês não encontrado"));
		return new MesDTO(obj.get().getCodigo(), obj.get().getDsMes());
	}

	@Override
	public List<MesDTO> buscarMesesPorFiltro() {
		return repo.buscarMesesPorFiltro().stream().map(m -> new MesDTO(m.getCodigo(), m.getDsMes()))
				.collect(Collectors.toList());
	}
}