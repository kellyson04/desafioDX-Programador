package br.com.duxusdesafio.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class CriarTimeRequest {

    @NotBlank
    private String nomeDoClube;

    @NotNull
    private LocalDate data;

    @NotEmpty
    private List<Long> integrantesIds;

    public CriarTimeRequest() {
    }

    public CriarTimeRequest(
            String nomeDoClube,
            LocalDate data,
            List<Long> integrantesIds) {

        this.nomeDoClube = nomeDoClube;
        this.data = data;
        this.integrantesIds = integrantesIds;
    }

    public String getNomeDoClube() {
        return nomeDoClube;
    }

    public void setNomeDoClube(String nomeDoClube) {
        this.nomeDoClube = nomeDoClube;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<Long> getIntegrantesIds() {
        return integrantesIds;
    }

    public void setIntegrantesIds(List<Long> integrantesIds) {
        this.integrantesIds = integrantesIds;
    }
}