package com.github.rogerioja89.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class SimulacaoResponse {

    @JsonProperty("produtoValidado")
    private ProdutoValidadoResponse produtoValidado;
    @JsonProperty("resultadoSimulacao")
    private ResultadoSimulacaoResponse resultadoSimulacao;
    @JsonProperty("dataSimulacao")
    private OffsetDateTime dataSimulacao;
}
