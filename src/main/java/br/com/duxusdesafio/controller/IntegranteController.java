package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.service.IntegranteService;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/integrantes")
public class IntegranteController {

    private final IntegranteService integranteService;

    public IntegranteController(IntegranteService integranteService) {
        this.integranteService = integranteService;
    }

    @PostMapping
    public ResponseEntity<Integrante> cadastrar(@RequestBody @Valid Integrante integrante) {
        Integrante integranteCriado =
                integranteService.cadastrar(integrante);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(integranteCriado);
    }
}