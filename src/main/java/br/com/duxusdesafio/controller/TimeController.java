package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.CriarTimeRequest;
import br.com.duxusdesafio.service.TimeService;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/times")
public class TimeController {

    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid CriarTimeRequest request) {
        timeService.cadastrar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}