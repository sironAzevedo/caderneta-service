package br.com.caderneta.service.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.caderneta.service.models.entity.DashboardEntity;

@Repository
public interface IDashboardRepository extends JpaRepository<DashboardEntity, Integer> {
	
	Page<DashboardEntity> findByIdUsuario(BigDecimal idUsuario, Pageable pageable);
}
