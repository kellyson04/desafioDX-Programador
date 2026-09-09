package br.com.duxusdesafio.service;

import br.com.duxusdesafio.dto.CriarTimeRequest;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TimeService {

    private final TimeRepository timeRepository;
    private final IntegranteRepository integranteRepository;

    public TimeService(
            TimeRepository timeRepository,
            IntegranteRepository integranteRepository) {

        this.timeRepository = timeRepository;
        this.integranteRepository = integranteRepository;
    }

    @Transactional
    public void cadastrar(CriarTimeRequest request) {

        Set<Long> integrantesIds =
                new HashSet<>(request.getIntegrantesIds());

        List<Integrante> integrantes =
                integranteRepository.findAllById(integrantesIds);

        if (integrantes.size() != integrantesIds.size()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Um ou mais integrantes não foram encontrados"
            );
        }

        List<ComposicaoTime> composicoes = new ArrayList<>();

        Time time = new Time(
                request.getNomeDoClube(),
                request.getData(),
                composicoes
        );

        for (Integrante integrante : integrantes) {
            ComposicaoTime composicao =
                    new ComposicaoTime(time, integrante);

            composicoes.add(composicao);
        }

        timeRepository.save(time);
    }
}