package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.service.ProcessamentoService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/processamentos")
public class ProcessamentoController {

    private final ProcessamentoService processamentoService;

    public ProcessamentoController(ProcessamentoService processamentoService) {

        this.processamentoService = processamentoService;
    }

    @GetMapping("/time-da-data")
    public ResponseEntity<Time> timeDaData(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate data) {

        return ResponseEntity.ok(processamentoService.timeDaData(data));
    }

    @GetMapping("/integrante-mais-usado")
    public ResponseEntity<Integrante> integranteMaisUsado(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataInicial,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataFinal) {

        return ResponseEntity.ok(processamentoService.integranteMaisUsado(dataInicial, dataFinal));
    }

    @GetMapping("/integrantes-do-time-mais-recorrente")
    public ResponseEntity<List<String>> integrantesDoTimeMaisRecorrente(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataInicial,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataFinal) {

        return ResponseEntity.ok(processamentoService.integrantesDoTimeMaisRecorrente(dataInicial, dataFinal));
    }

    @GetMapping("/funcao-mais-recorrente")
    public ResponseEntity<String> funcaoMaisRecorrente(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataInicial,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataFinal) {

        return ResponseEntity.ok(processamentoService.funcaoMaisRecorrente(dataInicial, dataFinal));
    }

    @GetMapping("/clube-mais-recorrente")
    public ResponseEntity<String> clubeMaisRecorrente(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataInicial,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataFinal) {

        return ResponseEntity.ok(processamentoService.clubeMaisRecorrente(dataInicial, dataFinal));
    }

    @GetMapping("/contagem-de-clubes")
    public ResponseEntity<Map<String, Long>> contagemDeClubesNoPeriodo(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataInicial,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataFinal) {

        return ResponseEntity.ok(processamentoService.contagemDeClubesNoPeriodo(dataInicial, dataFinal));
    }

    @GetMapping("/contagem-por-funcao")
    public ResponseEntity<Map<String, Long>> contagemPorFuncao(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataInicial,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataFinal) {

        return ResponseEntity.ok(processamentoService.contagemPorFuncao(dataInicial, dataFinal));
    }
}