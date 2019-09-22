package br.com.caderneta.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.caderneta.service.models.entity.UsuarioEntity;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	Optional<UsuarioEntity> findByEmail(String email);

	Boolean existsByEmail(String email);
}
