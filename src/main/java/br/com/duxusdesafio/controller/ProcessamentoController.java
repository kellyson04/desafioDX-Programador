package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.service.ProcessamentoService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/processamentos")
public class ProcessamentoController {

    private final ProcessamentoService processamentoService;

    public ProcessamentoController(
            ProcessamentoService processamentoService) {

        this.processamentoService = processamentoService;
    }

    @GetMapping("/clube-mais-recorrente")
    public ResponseEntity<String> clubeMaisRecorrente(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataInicial,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataFinal) {

        String resultado =
                processamentoService.clubeMaisRecorrente(
                        dataInicial,
                        dataFinal
                );

        return ResponseEntity.ok(resultado);
    }
}