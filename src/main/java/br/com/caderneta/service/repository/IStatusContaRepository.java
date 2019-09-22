package br.com.caderneta.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.caderneta.service.models.entity.StatusContaEntity;

@Repository
public interface IStatusContaRepository extends JpaRepository<StatusContaEntity, Long> {
	 
}
