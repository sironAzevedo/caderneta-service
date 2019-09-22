package br.com.caderneta.service.service.impl;

import static br.com.caderneta.service.common.util.CadernetaUtil.parseObject;
import static br.com.caderneta.service.common.util.ImageUtil.cropSquare;
import static br.com.caderneta.service.common.util.ImageUtil.getInputStream;
import static br.com.caderneta.service.common.util.ImageUtil.getJpgImageFromFile;
import static br.com.caderneta.service.common.util.ImageUtil.resize;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.caderneta.service.common.enums.Perfil;
import br.com.caderneta.service.common.exceptions.AuthorizationException;
import br.com.caderneta.service.common.exceptions.UserException;
import br.com.caderneta.service.configuracao.seguranca.User;
import br.com.caderneta.service.configuracao.seguranca.UserService;
import br.com.caderneta.service.models.dto.UsuarioDTO;
import br.com.caderneta.service.models.entity.UsuarioEntity;
import br.com.caderneta.service.repository.IRoleRepository;
import br.com.caderneta.service.repository.IUsuarioRepository;
import br.com.caderneta.service.service.IS3Service;
import br.com.caderneta.service.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	private static final String ACESSO_NEGADO = "Acesso Negado!";
	private static final String USUARIO_NOT_NULL = "Usuário não existe!";

	@Autowired
	private IUsuarioRepository repository;

	@Autowired
	private IRoleRepository iRole;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private IS3Service s3Service;

	@Value("${img.prefix.client.profile}")
	private String prefix;

	@Value("${img.profile.size}")
	private Integer size;

	@Override
	public void salvar(UsuarioDTO dto) {

		if (repository.existsByEmail(dto.getEmail())) {
			throw new UserException("Este e-mail já existe");
		}

		UsuarioEntity user = UsuarioEntity.builder()
				.nome(dto.getNome())				
				.email(dto.getEmail())
				.senha(pe.encode(dto.getSenha()))
				.roles(iRole.findByName(Perfil.ROLE_USER))
				.createdAt(new Date()).build();
		repository.saveAndFlush(user);
	}

	@Override
	public void atualizar(UsuarioDTO dto) {
		find(dto);
		UsuarioEntity user = UsuarioEntity.builder()
				.codigo(dto.getCodigo())
				.nome(dto.getNome())
				.email(dto.getEmail())
				.senha(pe.encode(dto.getSenha()))
				.roles(iRole.findByName(Perfil.ROLE_USER)).updatedAt(new Date()).build();
		repository.saveAndFlush(user);
	}

	@Override
	public void deletar(UsuarioDTO dto) {
		find(dto);
		repository.deleteById(dto.getCodigo());
	}

	@Override
	public List<UsuarioDTO> findAll() {
		List<UsuarioDTO> result = new ArrayList<>();
		repository.findAll().forEach(user -> {
			UsuarioDTO u = (UsuarioDTO) parseObject(user, new UsuarioDTO());
			result.add(u);
		});
		return result;
	}

	@Override
	public UsuarioDTO find(UsuarioDTO dto) {
		return recuperarUsuarioLogadoPorId(dto.getCodigo());
	}

	@Override
	public UsuarioDTO findEmail(String email) {
		return recuperarUsuarioLogadoPorEmail(email);
	}

	@Override
	public UsuarioDTO recuperarUsuarioLogado() {
		User user = UserService.authenticated();

		if (user == null) {
			throw new AuthorizationException(ACESSO_NEGADO);
		}

		Optional<UsuarioEntity> usuario = repository.findById(user.getCodigo());
		if (!usuario.isPresent()) {
			throw new UserException(USUARIO_NOT_NULL);
		}

		return (UsuarioDTO) parseObject(usuario.get(), new UsuarioDTO());
	}

	@Override
	public UsuarioDTO recuperarUsuarioLogadoPorId(Long id) {
		User user = UserService.authenticated();
		this.userValid(user, id);

		Optional<UsuarioEntity> usuario = repository.findById(id);
		if (!usuario.isPresent()) {
			throw new UserException(USUARIO_NOT_NULL);
		}

		return (UsuarioDTO) parseObject(usuario.get(), new UsuarioDTO());
	}

	@Override
	public UsuarioDTO recuperarUsuarioLogadoPorEmail(String email) {
		User user = UserService.authenticated();

		if (user == null || !user.hasRole(Perfil.ROLE_ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException(ACESSO_NEGADO);
		}

		Optional<UsuarioEntity> usuario = repository.findByEmail(email);
		if (!usuario.isPresent()) {
			throw new UserException(USUARIO_NOT_NULL);
		}

		return (UsuarioDTO) parseObject(usuario.get(), new UsuarioDTO());
	}

	@Override
	public void userValid(User user, Long userConta) {
		if (user == null || !user.hasRole(Perfil.ROLE_ADMIN) && !userConta.equals(user.getCodigo())) {
			throw new AuthorizationException("Acesso negado");
		}
	}

	@Override
	public URI uploadProfilePicture(MultipartFile file) {
		UsuarioDTO user = recuperarUsuarioLogado();

		BufferedImage jpgImage = getJpgImageFromFile(file);
		jpgImage = cropSquare(jpgImage);
		jpgImage = resize(jpgImage, size);

		String fileName = prefix + user.getCodigo() + ".jpg";

		return s3Service.uploadFile(getInputStream(jpgImage, "jpg"), fileName, "image", "avatar");
	}

	@Override
	public void uploadProfilePicture(Set<MultipartFile> files) {
		User user = UserService.authenticated();

		if (user == null || !user.hasRole(Perfil.ROLE_ADMIN)) {
			throw new AuthorizationException(ACESSO_NEGADO);
		}
		
		
		Iterator<MultipartFile> iterator = files.iterator();
		while (iterator.hasNext()) {
			MultipartFile it = iterator.next();

			BufferedImage jpgImage = getJpgImageFromFile(it);
			jpgImage = cropSquare(jpgImage);
			jpgImage = resize(jpgImage, size);

			String fileName = it.getOriginalFilename().split("\\.")[0] + ".jpg";
			s3Service.uploadFile(getInputStream(jpgImage, "jpg"), fileName, "image", "tipo-conta");
		}
	}
}
