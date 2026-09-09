package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.TimeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ProcessamentoService {

    private final TimeRepository timeRepository;
    private final ApiService apiService;

    public ProcessamentoService(TimeRepository timeRepository, ApiService apiService) {
        this.timeRepository = timeRepository;
        this.apiService = apiService;
    }

    public Time timeDaData(LocalDate data) {

        List<Time> todosOsTimes = timeRepository.findAll();

        return apiService.timeDaData(data, todosOsTimes);
    }

    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal) {

        List<Time> todosOsTimes = timeRepository.findAll();

        return apiService.integranteMaisUsado(dataInicial, dataFinal, todosOsTimes);
    }

    public List<String> integrantesDoTimeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal) {

        List<Time> todosOsTimes = timeRepository.findAll();

        return apiService.integrantesDoTimeMaisRecorrente(dataInicial, dataFinal, todosOsTimes);
    }

    public String funcaoMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal) {

        List<Time> todosOsTimes = timeRepository.findAll();

        return apiService.funcaoMaisRecorrente(dataInicial, dataFinal, todosOsTimes);
    }

    public String clubeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal) {

        List<Time> todosOsTimes = timeRepository.findAll();

        return apiService.clubeMaisRecorrente(dataInicial, dataFinal, todosOsTimes);
    }

    public Map<String, Long> contagemDeClubesNoPeriodo(LocalDate dataInicial, LocalDate dataFinal) {

        List<Time> todosOsTimes = timeRepository.findAll();

        return apiService.contagemDeClubesNoPeriodo(dataInicial, dataFinal, todosOsTimes);
    }

    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal) {

        List<Time> todosOsTimes = timeRepository.findAll();

        return apiService.contagemPorFuncao(dataInicial, dataFinal, todosOsTimes);
    }
}