package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.TimeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProcessamentoService {

    private final TimeRepository timeRepository;
    private final ApiService apiService;

    public ProcessamentoService(
            TimeRepository timeRepository,
            ApiService apiService) {

        this.timeRepository = timeRepository;
        this.apiService = apiService;
    }

    public String clubeMaisRecorrente(
            LocalDate dataInicial,
            LocalDate dataFinal) {

        List<Time> todosOsTimes = timeRepository.findAll();

        return apiService.clubeMaisRecorrente(
                dataInicial,
                dataFinal,
                todosOsTimes
        );
    }
}