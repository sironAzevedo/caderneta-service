package br.com.caderneta.service.service.impl;


import static br.com.caderneta.service.common.util.CadernetaUtil.formatValor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.caderneta.service.models.dto.DashboardDTO;
import br.com.caderneta.service.models.dto.UsuarioDTO;
import br.com.caderneta.service.models.entity.DashboardEntity;
import br.com.caderneta.service.repository.IDashboardRepository;
import br.com.caderneta.service.service.IDashboardService;
import br.com.caderneta.service.service.IUsuarioService;

@Service
public class DashboardServiceImpl implements IDashboardService {

	@Autowired
	private IDashboardRepository repository;

	@Autowired
	private IUsuarioService userService;

	@Override
	public List<DashboardDTO> findAll(Pageable pageable) {
		UsuarioDTO user = userService.recuperarUsuarioLogado();
		Page<DashboardEntity> result = repository.findByIdUsuario(new BigDecimal(user.getCodigo()), pageable);
		 return result.getContent().stream().map(d -> 
		 	DashboardDTO.builder()
				.codigo(d.getCodigo())
				.mes(d.getMes())
				.ano(d.getAno())
				.salario(formatValor(d.getSalario()))
				.qtdConta(d.getQtdConta())
				.totalGastos(formatValor(d.getTotalGastos()))
				.saldoFinal(formatValor(d.getSaldoFinal())) 
				.build()).collect(Collectors.toList());
	}
}