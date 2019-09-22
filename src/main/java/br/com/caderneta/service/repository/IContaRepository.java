package br.com.caderneta.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.caderneta.service.models.entity.ContaEntity;
import br.com.caderneta.service.models.entity.MesEntity;
import br.com.caderneta.service.models.entity.StatusContaEntity;
import br.com.caderneta.service.models.entity.UsuarioEntity;

@Repository
public interface IContaRepository extends JpaRepository<ContaEntity, Long> {

	@Query("SELECT c FROM ContaEntity c WHERE c.usuario = (:user)")
	List<ContaEntity> findByUsuario(@Param("user") UsuarioEntity user);

	Page<ContaEntity> findByMesAndUsuario(MesEntity mes, UsuarioEntity usuario, Pageable pageable);

	Page<ContaEntity> findByStatusAndMesAndUsuario(StatusContaEntity status, MesEntity mes, UsuarioEntity usuario, Pageable pageable);

}
