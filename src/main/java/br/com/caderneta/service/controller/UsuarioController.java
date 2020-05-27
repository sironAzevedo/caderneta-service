package br.com.caderneta.service.controller;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.caderneta.service.models.dto.UsuarioDTO;
import br.com.caderneta.service.service.IUsuarioService;

@RestController
@RequestMapping("/v1")
public class UsuarioController {

	@Autowired
	private IUsuarioService service;

	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	@PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String criarUsuario(@Valid @RequestBody UsuarioDTO dto) {		
		service.criarUsuario(dto);
		String res = "Cadastro realizado com sucesso";
		return "{\"mensagem\": \"" + res + "\"}";
	}
	
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String atualiarUsuario(@Valid @RequestBody UsuarioDTO dto) {
		service.atualizarUsuario(dto);
		String res = "Cadastro atualizado com sucesso";
		return "{\"mensagem\": \"" + res + "\"}";
	}
	
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String deletarUsuario(@Valid @RequestBody UsuarioDTO dto) {
		service.deletarUsuario(dto);
		String res = "Usuario delete com sucesso";
		return "{\"mensagem\": \"" + res + "\"}";
	}

	@ResponseBody
	@GetMapping(value = "/users")
	@ResponseStatus(value = HttpStatus.OK)
	public List<UsuarioDTO> buscarUsuarios() {
		return service.buscarUsuarios();
	}
	
	@ResponseBody
	@GetMapping(value = "/user")
	@ResponseStatus(value = HttpStatus.OK)
	public UsuarioDTO buscarUsuario(@RequestBody UsuarioDTO user) {
		return service.buscarUsuario(user);
	}
	
	@ResponseBody
	@GetMapping(value = "/user/email")
	@ResponseStatus(value = HttpStatus.OK)
	public UsuarioDTO buscarUsuarioPorEmail(@RequestParam(value="email") String email) {
		return service.buscarUsuarioPorEmail(email);
	}
	
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(value = "/user/picture", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
		URI uri = service.uploadProfilePicture(file);
		return "{\"avatar\": \"" + uri.toString() + "\"}";
	}
	
	@ResponseBody
	@PostMapping(value = "/user/pictures")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void uploadProfilePictures(@RequestParam(name="files") Set<MultipartFile> files) {
		service.uploadProfilePicture(files);
	}
}
