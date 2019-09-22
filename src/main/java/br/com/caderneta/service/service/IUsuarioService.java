package br.com.caderneta.service.service;

import java.net.URI;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import br.com.caderneta.service.configuracao.seguranca.User;
import br.com.caderneta.service.models.dto.UsuarioDTO;

public interface IUsuarioService {
	
	void salvar(UsuarioDTO dto);
	
	void atualizar(UsuarioDTO dto);
	
	void deletar(UsuarioDTO dto);
	
	List<UsuarioDTO> findAll();

	UsuarioDTO find(UsuarioDTO user);
	
	UsuarioDTO findEmail(String email);
	
	UsuarioDTO recuperarUsuarioLogado();
	
	UsuarioDTO recuperarUsuarioLogadoPorId(Long id);
	
	UsuarioDTO recuperarUsuarioLogadoPorEmail(String email);
	
	void userValid(User user, Long userConta);

	URI uploadProfilePicture(MultipartFile file);

	void uploadProfilePicture(Set<MultipartFile> files);

}
