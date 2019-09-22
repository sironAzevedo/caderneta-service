package br.com.caderneta.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.caderneta.service.common.enums.Perfil;
import br.com.caderneta.service.models.entity.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, String>{

	List<Role> findByName(Perfil name);
}
