package br.com.caderneta.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.caderneta.service.models.entity.MesEntity;
import br.com.caderneta.service.models.entity.MesSalarioEntity;
import br.com.caderneta.service.models.entity.UsuarioEntity;

@Repository
@Transactional
public interface IMesSalarioRepository extends JpaRepository<MesSalarioEntity, Long> {

	List<MesSalarioEntity> findByUsuario(UsuarioEntity usuario);

	@Query("SELECT s FROM MesSalarioEntity s WHERE s.mes=(:mes) AND s.usuario=(:user)")
	MesSalarioEntity findByMesAndUsuario(@Param("mes") MesEntity mes, @Param("user") UsuarioEntity user);
	
	Boolean existsByMes(MesEntity mes);

}
