package br.com.caderneta.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import br.com.caderneta.service.models.dto.ContaDTO;
import br.com.caderneta.service.models.dto.StatusContaDTO;
import br.com.caderneta.service.service.IContaService;

@RestController
@RequestMapping("/v1")
public class ContaController {

	@Autowired
	private IContaService service;

	@ResponseBody
	@PostMapping(value = "/conta")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void insert(@RequestBody ContaDTO dto) {
		this.service.salvar(dto);
	}

	@ResponseBody
	@PutMapping(value = "/conta")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void atualizar(@RequestBody ContaDTO dto) {
		this.service.atualizar(dto);
	}

	@ResponseBody
	@GetMapping(value = "/contas")
	@ResponseStatus(value = HttpStatus.OK)
	public List<ContaDTO> consultar() {
		return service.findAll();
	}

	@ResponseBody
	@GetMapping(value = "/conta")
	@ResponseStatus(value = HttpStatus.OK)
	public ContaDTO buscarConta(@RequestParam(value = "id") Long id) {
		return service.buscarContaPorId(id);
	}

	@ResponseBody
	@DeleteMapping(value = "/conta")
	@ResponseStatus(value = HttpStatus.OK)
	public void deletarConta(@RequestBody ContaDTO dto) {
		service.deletar(dto);
	}

	@ResponseBody
	@GetMapping(value = "/conta/mes")
	@ResponseStatus(value = HttpStatus.OK)
	public List<ContaDTO> buscarContaPorMes(@RequestParam(value = "mes") Long mes, Pageable pageable) {
		return service.buscarContasPorMes(mes, pageable);
	}

	@ResponseBody
	@GetMapping(value = "/conta/status")
	@ResponseStatus(value = HttpStatus.OK)
	public List<StatusContaDTO> buscarStatusConta() {
		return service.buscarStatusConta();
	}

	@ResponseBody
	@GetMapping(value = "/conta/status/tipo")
	@ResponseStatus(value = HttpStatus.OK)
	public List<ContaDTO> buscarContaPorStatus(@RequestParam(value = "status") Long status,
			@RequestParam(value = "mes") Long mes, Pageable pageable) {
		return service.busarContasPorStatus(status, mes, pageable);
	}
}
