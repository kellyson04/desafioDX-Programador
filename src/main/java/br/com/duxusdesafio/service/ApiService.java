package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados
 * solicitados no desafio!
 *
 * OBS ao candidato: PREFERENCIALMENTE, NÃO ALTERE AS ASSINATURAS DOS MÉTODOS!
 * Trabalhe com a proposta pura.
 *
 * @author carlosau
 */
@Service
public class ApiService {

    /**
     * Vai retornar um Time, com a composição do time daquela data
     */
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!

        for (Time time : todosOsTimes) {
            if (time.getData().equals(data)) {
                return time;
            }
        }

        return null;
    }

    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times
     * dentro do período
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!

        Integrante maisUsado = null;
        int maiorQuantidade = 0;

        Map<Integrante, Integer> contagem = new HashMap<>();

        for (Time time : todosOsTimes) {
            boolean dentroDoPeriodo =
                    (dataInicial == null
                            || !time.getData().isBefore(dataInicial))
                            &&
                            (dataFinal == null
                                    || !time.getData().isAfter(dataFinal));

            if (dentroDoPeriodo) {

                for (ComposicaoTime composicao : time.getComposicaoTime()) {

                    Integrante integrante = composicao.getIntegrante();

                    if (contagem.containsKey(integrante)) {
                        int quantidadeAtual = contagem.get(integrante);
                        contagem.put(integrante, quantidadeAtual + 1);
                    }else {
                        contagem.put(integrante, 1);
                    }

                    int quantidadeDoIntegrante = contagem.get(integrante);

                    if (quantidadeDoIntegrante > maiorQuantidade) {
                        maiorQuantidade = quantidadeDoIntegrante;
                        maisUsado = integrante;
                    }
                }

            }

        }


        return maisUsado;
    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais recorrente dentro do período.
     * OBS: Time é o clube + composição em determinada data
     */
    public List<String> integrantesDoTimeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!

        Map<Set<Integrante>, Integer> contagem = new HashMap<>();

        List<Integrante> integrantesMaisRecorrentes = null;
        int maiorQuantidade = 0;

        for (Time time : todosOsTimes) {
            boolean dentroDoPeriodo =
                    (dataInicial == null
                            || !time.getData().isBefore(dataInicial))
                            &&
                            (dataFinal == null
                                    || !time.getData().isAfter(dataFinal));

            if (dentroDoPeriodo) {
                List<ComposicaoTime> composicaoTime = time.getComposicaoTime();
                List<Integrante> integrantes = new ArrayList<>();

                for (ComposicaoTime composicao : composicaoTime) {
                    Integrante integrante = composicao.getIntegrante();
                    integrantes.add(integrante);
                }

                Set<Integrante> composicaoComSet = new HashSet<>(integrantes);

                if (contagem.containsKey(composicaoComSet)) {
                    int quantidadeAtual = contagem.get(composicaoComSet);
                    contagem.put(composicaoComSet, quantidadeAtual + 1);
                }else {
                    contagem.put(composicaoComSet, 1);
                }

                int quantidadeDaComposicao =  contagem.get(composicaoComSet);

                if (quantidadeDaComposicao > maiorQuantidade) {
                    maiorQuantidade = quantidadeDaComposicao;
                    integrantesMaisRecorrentes = integrantes;
                }
            }
        }

        List<String> nomes = new ArrayList<>();
        if (integrantesMaisRecorrentes != null) {
            for (Integrante integrante : integrantesMaisRecorrentes) {
                nomes.add(integrante.getNome());
            }
        }

        return nomes;
    }

    /**
     * Vai retornar a função mais recorrente nos times dentro do período
     */
    public String funcaoMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!

        Map<String, Integer> contagem = new HashMap<>();

        String funcaoMaisRecorrente = null;
        int maiorQuantidade = 0;

        for (Time time : todosOsTimes) {

            boolean dentroDoPeriodo =
                    (dataInicial == null
                            || !time.getData().isBefore(dataInicial))
                            &&
                            (dataFinal == null
                                    || !time.getData().isAfter(dataFinal));

            if (dentroDoPeriodo) {
                for (ComposicaoTime composicao : time.getComposicaoTime()) {

                    Integrante integrante = composicao.getIntegrante();
                    String funcao = integrante.getFuncao();

                    if (contagem.containsKey(funcao)) {
                        int quantidadeAtual = contagem.get(funcao);
                        contagem.put(funcao, quantidadeAtual + 1);
                    } else {
                        contagem.put(funcao, 1);
                    }

                    int quantidadeDaFuncao = contagem.get(funcao);

                    if (quantidadeDaFuncao > maiorQuantidade) {
                        maiorQuantidade = quantidadeDaFuncao;
                        funcaoMaisRecorrente = funcao;
                    }
                }
            }
        }

        return funcaoMaisRecorrente;
    }

    /**
     * Vai retornar o nome do Clube mais comum dentro do período
     */
    public String clubeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // TODO Implementar método seguindo as instruções!
        Map<String, Integer> contagem = new HashMap<>();

        String clubeMaisRecorrente = null;
        int maiorQuantidade = 0;

        for (Time time : todosOsTimes) {

            boolean dentroDoPeriodo =
                    (dataInicial == null
                            || !time.getData().isBefore(dataInicial))
                            &&
                            (dataFinal == null
                                    || !time.getData().isAfter(dataFinal));

            if (dentroDoPeriodo) {
                String clube = time.getNomeDoClube();

                if (contagem.containsKey(clube)) {
                    int quantidadeAtual = contagem.get(clube);
                    contagem.put(clube, quantidadeAtual + 1);
                } else {
                    contagem.put(clube, 1);
                }

                int quantidadeDoClube = contagem.get(clube);

                if (quantidadeDoClube > maiorQuantidade) {
                    maiorQuantidade = quantidadeDoClube;
                    clubeMaisRecorrente = clube;
                }
            }
        }

        return clubeMaisRecorrente;
    }


    /**
     * Vai retornar o número (quantidade) de aparições de cada Clube participante no período
     */
    public Map<String, Long> contagemDeClubesNoPeriodo(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        Map<String, Long> contagem = new HashMap<>();

        for (Time time : todosOsTimes) {

            boolean dentroDoPeriodo =
                    (dataInicial == null
                            || !time.getData().isBefore(dataInicial))
                            &&
                            (dataFinal == null
                                    || !time.getData().isAfter(dataFinal));

            if (dentroDoPeriodo) {
                String clube = time.getNomeDoClube();

                if (contagem.containsKey(clube)) {
                    long quantidadeAtual = contagem.get(clube);
                    contagem.put(clube, quantidadeAtual + 1L);
                } else {
                    contagem.put(clube, 1L);
                }
            }
        }

        return contagem;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período.
     * Dica - pense sobre repetições!
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        Map<String, Long> contagem = new HashMap<>();

        for (Time time : todosOsTimes) {

            boolean dentroDoPeriodo =
                    (dataInicial == null
                            || !time.getData().isBefore(dataInicial))
                            &&
                            (dataFinal == null
                                    || !time.getData().isAfter(dataFinal));

            if (dentroDoPeriodo) {

                for (ComposicaoTime composicao : time.getComposicaoTime()) {
                    Integrante integrante = composicao.getIntegrante();
                    String funcao = integrante.getFuncao();

                    if (contagem.containsKey(funcao)) {
                        long quantidadeAtual = contagem.get(funcao);
                        contagem.put(funcao, quantidadeAtual + 1L);
                    } else {
                        contagem.put(funcao, 1L);
                    }
                }
            }
        }

        return contagem;
    }

}
