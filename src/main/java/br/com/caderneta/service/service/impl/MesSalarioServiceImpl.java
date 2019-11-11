package br.com.caderneta.service.service.impl;

import static br.com.caderneta.service.common.util.CadernetaUtil.formatValor;
import static br.com.caderneta.service.common.util.CadernetaUtil.parseObject;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caderneta.service.common.exceptions.EmptyResultDataAccessException;
import br.com.caderneta.service.common.exceptions.NotFoundException;
import br.com.caderneta.service.configuracao.seguranca.User;
import br.com.caderneta.service.configuracao.seguranca.UserService;
import br.com.caderneta.service.models.dto.MesDTO;
import br.com.caderneta.service.models.dto.MesSalarioDTO;
import br.com.caderneta.service.models.dto.UsuarioDTO;
import br.com.caderneta.service.models.entity.MesEntity;
import br.com.caderneta.service.models.entity.MesSalarioEntity;
import br.com.caderneta.service.models.entity.UsuarioEntity;
import br.com.caderneta.service.repository.IMesSalarioRepository;
import br.com.caderneta.service.service.IMesSalarioService;
import br.com.caderneta.service.service.IUsuarioService;

@Service
public class MesSalarioServiceImpl implements IMesSalarioService {

	@Autowired
	private IMesSalarioRepository repository;

	@Autowired
	private IUsuarioService userService;

	@Override
	public void salvar(MesSalarioDTO dto) {

		if (repository.existsByMes(new MesEntity(dto.getMes()))) {
			throw new NotFoundException("Já foi cadastro um salario para mês de " + dto.getMes().getDsMes());
		}

		UsuarioDTO user = userService.recuperarUsuarioLogado();
		dto.setUsuario(user);
		repository.saveAndFlush(new MesSalarioEntity(dto));
	}

	@Override
	public void atualizar(MesSalarioDTO dto) {
		repository.saveAndFlush(new MesSalarioEntity(this.buscarPorCodigo(dto)));
	}

	@Override
	public void deletar(MesSalarioDTO dto) {
		repository.delete(new MesSalarioEntity(this.buscarPorCodigo(dto)));
	}

	@Override
	public List<MesSalarioDTO> buscarTodos() {
		UsuarioEntity usuario = (UsuarioEntity) parseObject(userService.recuperarUsuarioLogado(), new UsuarioEntity());
		return repository.findByUsuario(usuario).stream().map(s ->

		MesSalarioDTO.builder()
			.codigo(s.getCodigo())
			.valorSalario(formatValor(s.getValorSalario()))
			.mes((MesDTO) parseObject(s.getMes(), new MesDTO()))
			.build())
		.collect(Collectors.toList());
	}

	@Override
	public MesSalarioDTO buscarPorCodigo(MesSalarioDTO dto) {
		User user = UserService.authenticated();

		MesSalarioEntity salario = repository.findById(dto.getCodigo())
				.orElseThrow(() -> new EmptyResultDataAccessException("Conta não encontrada"));
		userService.userValid(user, salario.getUsuario().getCodigo());

		return MesSalarioDTO.builder().codigo(salario.getCodigo()).valorSalario(formatValor(salario.getValorSalario()))
				.mes((MesDTO) parseObject(salario.getMes(), new MesDTO())).build();
	}
}
