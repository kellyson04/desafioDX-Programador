package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(DataProviderRunner.class)
public class TesteApiService {

    private final static LocalDate data1993 = LocalDate.of(1993,1, 1);
    private final static LocalDate data1994 = LocalDate.of(1994,1, 1);
    private final static LocalDate data1995 = LocalDate.of(1995,1, 1);

    @Spy
    private ApiService apiService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @DataProvider
    public static Object[][] testTimeDaDataParams() {

        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();

        List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        Time timeChicagoBullsDe1995 = dadosParaTesteApiService.getTimeChicagoBullsDe1995();
        Time timeDetroidPistonsDe1993 = dadosParaTesteApiService.getTimeDetroidPistonsDe1993();

        return new Object[][]{
                {
                        data1995,
                        todosOsTimes,
                        timeChicagoBullsDe1995
                },
                {
                        data1993,
                        todosOsTimes,
                        timeDetroidPistonsDe1993
                }
        };
    }

    @Test
    @UseDataProvider("testTimeDaDataParams")
    public void testTimeDaData(LocalDate data, List<Time> todosOsTimes, Time esperado) {

        Time timeRetornado = apiService.timeDaData(data, todosOsTimes);

        assertEquals(esperado, timeRetornado);
    }



    @DataProvider
    public static Object[][] testIntegranteMaisUsadoParams() {

        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();

        List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        dadosParaTesteApiService.getDenis_rodman()
                }
        };
    }


    @Test
    @UseDataProvider("testIntegranteMaisUsadoParams")
    public void testIntegranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, Integrante esperado) {

        Integrante integranteRetornado = apiService.integranteMaisUsado(dataInicial, dataFinal, todosOsTimes);

        assertEquals(esperado, integranteRetornado);
    }



    @DataProvider
    public static Object[][] testTimeMaisRecorrenteParams() {
        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
        List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        List<String> integrantesEsperados = Arrays.asList(
                dadosParaTesteApiService.getDenis_rodman().getNome(),
                dadosParaTesteApiService.getMichael_jordan().getNome(),
                dadosParaTesteApiService.getScottie_pippen().getNome()
        );
        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        integrantesEsperados
                }
        };
    }

    @Test
    @UseDataProvider("testTimeMaisRecorrenteParams")
    public void testIntegrantesDoTimeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, List<String> esperado) {

        List<String> nomeDosIntegrantesDoTimeMaisRecorrente = apiService.integrantesDoTimeMaisRecorrente(dataInicial, dataFinal, todosOsTimes);

        if(nomeDosIntegrantesDoTimeMaisRecorrente != null){
            nomeDosIntegrantesDoTimeMaisRecorrente.sort(Comparator.naturalOrder());
        }

        assertEquals(esperado, nomeDosIntegrantesDoTimeMaisRecorrente);
    }



    @DataProvider
    public static Object[][] testFuncaoMaisRecorrenteParams() {

        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
        List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        "ala"
                }
        };
    }

    @Test
    @UseDataProvider("testFuncaoMaisRecorrenteParams")
    public void testFuncaoMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, String esperado) {

        String funcaoMaisRecorrente = apiService.funcaoMaisRecorrente(dataInicial, dataFinal, todosOsTimes);

        assertEquals(esperado, funcaoMaisRecorrente);
    }

    @DataProvider
    public static Object[][] testClubeMaisRecorrenteParams() {
        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
        List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        dadosParaTesteApiService.getClubeChicagoBulls()
                }
        };
    }

    @Test
    @UseDataProvider("testClubeMaisRecorrenteParams")
    public void testClubeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, String esperado) {

        String clubeMaisRecorrente = apiService.clubeMaisRecorrente(dataInicial, dataFinal, todosOsTimes);
        assertEquals(esperado, clubeMaisRecorrente);
    }

    @DataProvider
    public static Object[][] testContagemDeClubesParams() {

        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
        List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        Map<String, Long> esperado1 = new HashMap<>();
        esperado1.put(dadosParaTesteApiService.getClubeDetroitPistons(), 1L);
        esperado1.put(dadosParaTesteApiService.getClubeChicagoBulls(), 2L);

        Map<String, Long> esperado2 = new HashMap<>();
        esperado2.put(dadosParaTesteApiService.getClubeChicagoBulls(), 2L);

        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        esperado1
                },
                {
                        data1994,
                        data1995,
                        todosOsTimes,
                        esperado2
                }
        };
    }

    @Test
    @UseDataProvider("testContagemDeClubesParams")
    public void testcontagemDeClubesNoPeriodo(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, Map<String, Long> esperado) {

        Map<String, Long> contagemDeClubesNoPeriodo = apiService.contagemDeClubesNoPeriodo(dataInicial, dataFinal, todosOsTimes);
        assertEquals(esperado, contagemDeClubesNoPeriodo);
    }



    @DataProvider
    public static Object[][] testContagemPorFuncaoParams() {

        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
        List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        Map<String, Long> esperado = new HashMap<>();
        esperado.put("ala", 2L);
        esperado.put("ala-pivô", 1L);

        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        esperado
                }
        };
    }

    @Test
    @UseDataProvider("testContagemPorFuncaoParams")
    public void testContagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, Map<String, Long> esperado) {

        Map<String, Long> contagemPorFuncao = apiService.contagemPorFuncao(dataInicial, dataFinal, todosOsTimes);
        assertEquals(esperado, contagemPorFuncao);
    }

    @Test
    public void deveRetornarNullQuandoNaoExistirTimeNaData() {
        DadosParaTesteApiService dados = new DadosParaTesteApiService();

        List<Time> todosOsTimes = dados.getTodosOsTimes();
        LocalDate dataInexistente = LocalDate.of(2000, 1, 1);

        Time resultado = apiService.timeDaData(
                dataInexistente,
                todosOsTimes
        );

        assertNull(resultado);
    }

    @Test
    public void deveBuscarIntegranteMaisUsadoSemLimiteInicial() {
        DadosParaTesteApiService dados = new DadosParaTesteApiService();

        List<Time> todosOsTimes = dados.getTodosOsTimes();

        Integrante resultado = apiService.integranteMaisUsado(
                null,
                data1995,
                todosOsTimes
        );

        assertEquals(dados.getDenis_rodman(), resultado);
    }

    @Test
    public void deveBuscarClubeMaisRecorrenteSemLimiteFinal() {
        DadosParaTesteApiService dados = new DadosParaTesteApiService();

        List<Time> todosOsTimes = dados.getTodosOsTimes();

        String resultado = apiService.clubeMaisRecorrente(
                data1993,
                null,
                todosOsTimes
        );

        assertEquals(dados.getClubeChicagoBulls(), resultado);
    }

    @Test
    public void deveContarTodosOsClubesQuandoPeriodoForNulo() {
        DadosParaTesteApiService dados = new DadosParaTesteApiService();

        List<Time> todosOsTimes = dados.getTodosOsTimes();

        Map<String, Long> esperado = new HashMap<>();
        esperado.put(dados.getClubeChicagoBulls(), 2L);
        esperado.put(dados.getClubeDetroitPistons(), 1L);

        Map<String, Long> resultado =
                apiService.contagemDeClubesNoPeriodo(
                        null,
                        null,
                        todosOsTimes
                );

        assertEquals(esperado, resultado);
    }

    @Test
    public void deveRetornarContagemVaziaQuandoNaoExistiremTimes() {
        List<Time> todosOsTimes = new ArrayList<>();

        Map<String, Long> esperado = new HashMap<>();

        Map<String, Long> resultado =
                apiService.contagemDeClubesNoPeriodo(
                        data1993,
                        data1995,
                        todosOsTimes
                );

        assertEquals(esperado, resultado);
    }

    @Test
    public void deveContarClubeQuandoDataInicialEFinalForemIguais() {
        DadosParaTesteApiService dados = new DadosParaTesteApiService();

        Map<String, Long> esperado = new HashMap<>();
        esperado.put(dados.getClubeDetroitPistons(), 1L);

        Map<String, Long> resultado = apiService.contagemDeClubesNoPeriodo(
                data1993,
                data1993,
                dados.getTodosOsTimes()
        );

        assertEquals(esperado, resultado);
    }

    @Test
    public void deveRetornarNullQuandoNaoExistiremClubesNoPeriodo() {
        DadosParaTesteApiService dados = new DadosParaTesteApiService();

        LocalDate dataInicial = LocalDate.of(1996, 1, 1);

        String resultado = apiService.clubeMaisRecorrente(
                dataInicial,
                null,
                dados.getTodosOsTimes()
        );

        assertNull(resultado);
    }

    @Test
    public void deveSepararComposicoesIguaisDeClubesDiferentes() {
        // Escalações fictícias para testar os mesmos jogadores em clubes diferentes.
        Integrante faker = new Integrante("Faker", "mid", new ArrayList<>());
        Integrante zeus = new Integrante("Zeus", "top", new ArrayList<>());
        Integrante bin = new Integrante("Bin", "top", new ArrayList<>());
        Integrante canyon = new Integrante("Canyon", "jungle", new ArrayList<>());

        List<Time> todosOsTimes = new ArrayList<>();

        String[] clubes = {
                "T1", "T1",
                "BLG", "BLG",
                "Gen.G", "Gen.G", "Gen.G"
        };

        for (int i = 0; i < clubes.length; i++) {
            List<ComposicaoTime> composicoes = new ArrayList<>();

            Time time = new Time(
                    clubes[i],
                    LocalDate.of(2025, 1, 1).plusDays(i),
                    composicoes
            );

            if (clubes[i].equals("Gen.G")) {
                composicoes.add(new ComposicaoTime(time, bin));
                composicoes.add(new ComposicaoTime(time, canyon));
            } else {
                composicoes.add(new ComposicaoTime(time, faker));
                composicoes.add(new ComposicaoTime(time, zeus));
            }

            todosOsTimes.add(time);
        }

        List<String> resultado = apiService.integrantesDoTimeMaisRecorrente(
                null,
                null,
                todosOsTimes
        );

        List<String> esperado = Arrays.asList("Bin", "Canyon");

        assertEquals(esperado, resultado);
    }
}
