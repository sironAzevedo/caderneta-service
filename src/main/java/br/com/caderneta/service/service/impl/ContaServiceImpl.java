package br.com.caderneta.service.service.impl;

import static br.com.caderneta.service.common.util.CadernetaUtil.formatValor;
import static br.com.caderneta.service.common.util.CadernetaUtil.parseObject;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.caderneta.service.common.enums.StatusConta;
import br.com.caderneta.service.common.exceptions.EmptyResultDataAccessException;
import br.com.caderneta.service.common.exceptions.QuantidadeParcelasException;
import br.com.caderneta.service.configuracao.seguranca.User;
import br.com.caderneta.service.configuracao.seguranca.UserService;
import br.com.caderneta.service.models.dto.ContaDTO;
import br.com.caderneta.service.models.dto.MesDTO;
import br.com.caderneta.service.models.dto.StatusContaDTO;
import br.com.caderneta.service.models.dto.TipoContaDTO;
import br.com.caderneta.service.models.entity.ContaEntity;
import br.com.caderneta.service.models.entity.MesEntity;
import br.com.caderneta.service.models.entity.StatusContaEntity;
import br.com.caderneta.service.models.entity.TipoContaEntity;
import br.com.caderneta.service.models.entity.UsuarioEntity;
import br.com.caderneta.service.repository.IContaRepository;
import br.com.caderneta.service.repository.IStatusContaRepository;
import br.com.caderneta.service.service.IContaService;
import br.com.caderneta.service.service.IMesService;
import br.com.caderneta.service.service.IUsuarioService;

@Service
public class ContaServiceImpl implements IContaService {

	@Autowired
	private IContaRepository repository;
	
	@Autowired
	private IStatusContaRepository statusRepo;

	@Autowired
	private IUsuarioService userService;

	@Autowired
	private IMesService mesService;

	@Override
	public void salvar(ContaDTO dto) {
		isParcelado(dto.getStatus().getDescricao(), dto.getQtdParcelas()); 
		
		UsuarioEntity user = (UsuarioEntity) parseObject(userService.recuperarUsuarioLogado(), new UsuarioEntity());
		MesDTO mes = mesService.buscarPorCodigo(dto.getMes().getCodigo());
		
		ContaEntity entity = ContaEntity.
				builder()
				.valorConta(formatValor(dto.getValorConta()))
				.dataVencimento(dto.getDataVencimento())
				.dataPagamento(dto.getDataPagamento())
				.status((StatusContaEntity) parseObject(dto.getStatus(), new StatusContaEntity()))
				.qtdParcelas(dto.getQtdParcelas())
				.comentario(dto.getComentario())
				.mes(new MesEntity(mes))
				.tipoConta(new TipoContaEntity(dto.getTipoConta()))
				.usuario(user)
				.createdAt(new Date()) 
				.build();  
		repository.saveAndFlush(entity);
	} 

	@Override
	public void atualizar(ContaDTO dto) {
		isParcelado(dto.getStatus().getDescricao(), dto.getQtdParcelas()); 
		buscarContaPorId(dto.getCodigo()); 
		
		UsuarioEntity user = (UsuarioEntity) parseObject(userService.recuperarUsuarioLogado(), new UsuarioEntity());
		MesDTO mes = mesService.buscarPorCodigo(dto.getMes().getCodigo());
		
		ContaEntity entity = ContaEntity.
				builder()
				.codigo(dto.getCodigo())
				.valorConta(formatValor(dto.getValorConta()))
				.dataVencimento(dto.getDataVencimento())
				.dataPagamento(dto.getDataPagamento())
				.status((StatusContaEntity) parseObject(dto.getStatus(), new StatusContaEntity()))
				.qtdParcelas(dto.getQtdParcelas())
				.comentario(dto.getComentario())
				.mes(new MesEntity(mes))
				.tipoConta(new TipoContaEntity(dto.getTipoConta()))
				.usuario(user)
				.build();
		repository.saveAndFlush(entity);
	}
	
	@Override
	public void deletar(Long id) {
		this.buscarContaPorId(id);
		repository.deleteById(id);
	}

	@Override
	public List<ContaDTO> findAll() {
		UsuarioEntity usuario = (UsuarioEntity) parseObject(userService.recuperarUsuarioLogado(), new UsuarioEntity());

		return repository.findByUsuario(usuario).stream().map(c ->
		ContaDTO.builder()
			.codigo(c.getCodigo())
			.valorConta(formatValor(c.getValorConta()))
			.dataVencimento(c.getDataVencimento())
			.dataPagamento(c.getDataPagamento())
			.status((StatusContaDTO) parseObject(c.getStatus(), new StatusContaDTO()))
			.qtdParcelas(c.getQtdParcelas())
			.comentario(c.getComentario())
			.mes(MesDTO.builder().codigo(c.getMes().getCodigo()).build())
			.tipoConta((TipoContaDTO) parseObject(c.getTipoConta(), new TipoContaDTO()))
			.build()
		).collect(Collectors.toList());
	}

	@Override
	public ContaDTO buscarContaPorId(Long id) {
		User user = UserService.authenticated();
		Optional<ContaEntity> c = Optional.ofNullable(repository.findById(id)).orElseThrow(() -> new EmptyResultDataAccessException("Conta não encontrada"));
		userService.userValid(user, c.get().getUsuario().getCodigo());
		
		return ContaDTO.builder()
				.codigo(c.get().getCodigo())
				.valorConta(formatValor(c.get().getValorConta()))
				.dataVencimento(c.get().getDataVencimento())
				.dataPagamento(c.get().getDataPagamento())
				.status((StatusContaDTO) parseObject(c.get().getStatus(), new StatusContaDTO()))
				.qtdParcelas(c.get().getQtdParcelas())
				.comentario(c.get().getComentario())
				.mes(MesDTO.builder().codigo(c.get().getMes().getCodigo()).dsMes(c.get().getMes().getDsMes()).build())
				.tipoConta((TipoContaDTO) parseObject(c.get().getTipoConta(), new TipoContaDTO()))
				.build(); 
	} 

	@Override
	public List<ContaDTO> buscarContasPorMes(Long mes, Pageable pageable) {
		UsuarioEntity user = (UsuarioEntity) parseObject(userService.recuperarUsuarioLogado(), new UsuarioEntity());
		MesDTO mesDTO = mesService.buscarPorCodigo(mes);
		Page<ContaEntity> result = repository.findByMesAndUsuario(new MesEntity(mesDTO), user, pageable);
		List<ContaDTO> appList = result.getContent().stream().map(c ->			
			ContaDTO.builder()
				.codigo(c.getCodigo())
				.valorConta(formatValor(c.getValorConta()))
				.dataVencimento(c.getDataVencimento())
				.dataPagamento(c.getDataPagamento())
				.status((StatusContaDTO) parseObject(c.getStatus(), new StatusContaDTO()))
				.qtdParcelas(c.getQtdParcelas())
				.comentario(c.getComentario())
				.mes(MesDTO.builder().codigo(c.getMes().getCodigo()).dsMes(c.getMes().getDsMes()).build())
				.tipoConta((TipoContaDTO) parseObject(c.getTipoConta(), new TipoContaDTO()))
				.build()				
		 ).collect(Collectors.toList());
		
		return appList;
	}

	@Override
	public List<ContaDTO> busarContasPorStatus(Long status, Long mes, Pageable pageable){
		UsuarioEntity user = (UsuarioEntity) parseObject(userService.recuperarUsuarioLogado(), new UsuarioEntity());
		Optional<StatusContaEntity> statusEntity = statusRepo.findById(status);
		MesDTO mesDTO = mesService.buscarPorCodigo(mes);
		
		Page<ContaEntity> contas = repository.findByStatusAndMesAndUsuario(statusEntity.get(), new MesEntity(mesDTO), user, pageable);
		List<ContaDTO>  appList = contas.getContent().stream().map(c ->ContaDTO.builder()
				.codigo(c.getCodigo())
				.valorConta(formatValor(c.getValorConta()))
				.dataVencimento(c.getDataVencimento())
				.dataPagamento(c.getDataPagamento())
				.status((StatusContaDTO) parseObject(c.getStatus(), new StatusContaDTO()))
				.qtdParcelas(c.getQtdParcelas())
				.comentario(c.getComentario())
				.mes(MesDTO.builder().codigo(c.getCodigo()).build())
				.tipoConta((TipoContaDTO) parseObject(c.getTipoConta(), new TipoContaDTO()))
				.build()
		).collect(Collectors.toList());
		
		return appList;		
	}
	
	private void isParcelado(String status, Integer qtdParcelas) {
		if(StatusConta.PARCELADO.getCodigo().equals(status)) {
			if(qtdParcelas == null) {
				throw new QuantidadeParcelasException("Informe a quantidade de parcelas");
			}
		} 
	}

	@Override
	@Cacheable("statusConta")
	public List<StatusContaDTO> buscarStatusConta() {
		return statusRepo.findAll().stream().map(s -> StatusContaDTO.builder()
				.codigo(s.getCodigo())
				.descricao(s.getDescricao())
				.build()
			).collect(Collectors.toList());
	}
}
