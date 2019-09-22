package br.com.caderneta.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.caderneta.service.models.entity.MesEntity;

public interface IMesRepository extends JpaRepository<MesEntity, Long> {
	
	@Query(value = "SELECT m.id, m.desc_mes FROM {h-schema}TB_MES m WHERE NOT EXISTS ( SELECT * FROM {h-schema}TB_MES_SALARIO s WHERE s.id_mes = m.id)", nativeQuery = true)
	List<MesEntity> buscarMesesPorFiltro();

}
