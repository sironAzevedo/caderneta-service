package br.com.caderneta.service.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import br.com.caderneta.service.models.dto.DashboardDTO;

public interface IDashboardService {

	List<DashboardDTO> findAll(Pageable pageable);
}
