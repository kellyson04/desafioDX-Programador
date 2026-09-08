package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.repository.IntegranteRepository;
import org.springframework.stereotype.Service;

@Service
public class IntegranteService {

    private final IntegranteRepository integranteRepository;

    public IntegranteService(
            IntegranteRepository integranteRepository) {

        this.integranteRepository = integranteRepository;
    }

    public Integrante cadastrar(Integrante integrante) {
        return integranteRepository.save(integrante);
    }
}