package br.com.caderneta.service.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.caderneta.service.models.dto.MesSalarioDTO;
import br.com.caderneta.service.service.IMesSalarioService;

@RestController
@RequestMapping("/v1")
public class MesSalarioController {

	@Autowired
	private IMesSalarioService service;

	@ResponseBody
	@PostMapping(value = "/salario")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void salvarMesSalario(@Valid @RequestBody MesSalarioDTO dto) {
		service.salvar(dto);
	}

	@ResponseBody
	@PutMapping(value = "/salario")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void atualizarMesSalario(@Valid @RequestBody MesSalarioDTO dto) {
		service.atualizar(dto);
	}

	@ResponseBody
	@GetMapping(value = "/salarios")
	@ResponseStatus(value = HttpStatus.OK)
	public List<MesSalarioDTO> consultarMesSalario() {
		return service.buscarTodos();
	}

	@ResponseBody
	@GetMapping(value = "/salario")
	@ResponseStatus(value = HttpStatus.OK)
	public MesSalarioDTO buscarSalarioPorID(@RequestBody MesSalarioDTO dto) {
		return service.buscarPorCodigo(dto);
	}

	@ResponseBody
	@DeleteMapping(value = "/salario")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void deletarMesSalario(@Valid @RequestBody MesSalarioDTO dto) {
		service.deletar(dto);
	}
}
