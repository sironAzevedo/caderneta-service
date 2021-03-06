package br.com.caderneta.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.caderneta.service.models.dto.TipoContaDTO;
import br.com.caderneta.service.service.ITipoContaService;

@RestController
@RequestMapping("/v1")
public class TipoContaController {

	@Autowired
	private ITipoContaService service;

	@ResponseBody
	@GetMapping(value = "/tipo/contas")
	@ResponseStatus(value = HttpStatus.OK)
	public List<TipoContaDTO> consultarTipoConta() {
		return service.buscarTodos();
	}

	@ResponseBody
	@GetMapping(value = "/tipo/conta")
	@ResponseStatus(value = HttpStatus.OK)
	public TipoContaDTO buscarTipoConta(@RequestParam(value = "tipo") Long tipo) {
		return service.buscarTipoConta(tipo);
	}
}
