package br.com.caderneta.service.service;

import java.net.URI;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import br.com.caderneta.service.configuracao.seguranca.User;
import br.com.caderneta.service.models.dto.UsuarioDTO;

public interface IUsuarioService {
	
	void criarUsuario(UsuarioDTO dto);
	
	void atualizarUsuario(UsuarioDTO dto);
	
	void deletarUsuario(UsuarioDTO dto);
	
	List<UsuarioDTO> buscarUsuarios();

	UsuarioDTO buscarUsuario(UsuarioDTO user);
	
	UsuarioDTO buscarUsuarioPorEmail(String email);
	
	UsuarioDTO recuperarUsuarioLogado();
	
	UsuarioDTO recuperarUsuarioLogadoPorId(Long id);
	
	UsuarioDTO recuperarUsuarioLogadoPorEmail(String email);
	
	void userValid(User user, Long userConta);

	URI uploadProfilePicture(MultipartFile file);

	void uploadProfilePicture(Set<MultipartFile> files);

}
