package br.com.caderneta.service.controller;

import static br.com.caderneta.service.common.util.CadernetaUtil.formatDateToString;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.caderneta.service.models.dto.DashboardDTO;
import br.com.caderneta.service.service.IDashboardService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1")
public class DashboardController {

    @Autowired
    private IDashboardService service;

    @ResponseBody
    @GetMapping(value = "/dashboard")
    @ResponseStatus(value = HttpStatus.OK)
    public List<DashboardDTO> dashboard(Pageable pageable) {
    	log.info("O serviço insertaccount foi acionado em: " + formatDateToString(new Date()));
        return service.findAll(pageable);
    }
}
