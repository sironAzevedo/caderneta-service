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

import br.com.caderneta.service.models.dto.MesDTO;
import br.com.caderneta.service.service.IMesService;

@RestController
@RequestMapping("/v1")
public class MesController {

	@Autowired
	private IMesService service;

	@ResponseBody
	@GetMapping(value = "/meses")
	@ResponseStatus(value = HttpStatus.OK)
	public List<MesDTO> consultarMeses() {
		return service.findAll();
	}

	@ResponseBody
	@GetMapping(value = "/mes")
	@ResponseStatus(value = HttpStatus.OK)
	public MesDTO buscarMesPorID(@RequestParam(value = "mes") Long mes) {
		return service.buscarPorCodigo(mes);
	}

	@ResponseBody
	@GetMapping(value = "/meses/filtro")
	@ResponseStatus(value = HttpStatus.OK)
	public List<MesDTO> buscarMesesPorFiltro() {
		return service.buscarMesesPorFiltro();
	}
}
