package com.github.rogerioja89.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public class SimulacaoResponse {

    @JsonProperty("produtoValidado")
    private ProdutoValidadoResponse produtoValidado;
    @JsonProperty("resultadoSimulacao")
    private ResultadoSimulacaoResponse resultadoSimulacao;
    @JsonProperty("dataSimulacao")
    private OffsetDateTime dataSimulacao;

    public SimulacaoResponse() {
    }

    public SimulacaoResponse(
            ProdutoValidadoResponse produtoValidado,
            ResultadoSimulacaoResponse resultadoSimulacao,
            OffsetDateTime dataSimulacao
    ) {
        this.produtoValidado = produtoValidado;
        this.resultadoSimulacao = resultadoSimulacao;
        this.dataSimulacao = dataSimulacao;
    }

    public ProdutoValidadoResponse getProdutoValidado() {
        return produtoValidado;
    }

    public void setProdutoValidado(ProdutoValidadoResponse produtoValidado) {
        this.produtoValidado = produtoValidado;
    }

    public ResultadoSimulacaoResponse getResultadoSimulacao() {
        return resultadoSimulacao;
    }

    public void setResultadoSimulacao(ResultadoSimulacaoResponse resultadoSimulacao) {
        this.resultadoSimulacao = resultadoSimulacao;
    }

    public OffsetDateTime getDataSimulacao() {
        return dataSimulacao;
    }

    public void setDataSimulacao(OffsetDateTime dataSimulacao) {
        this.dataSimulacao = dataSimulacao;
    }
}
