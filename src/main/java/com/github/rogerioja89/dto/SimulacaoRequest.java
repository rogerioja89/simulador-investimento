package com.github.rogerioja89.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.rogerioja89.entity.TipoProduto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SimulacaoRequest {

    @NotNull
    @Positive
    @JsonProperty("clienteId")
    private Long clienteId;

    @NotNull
    @Positive
    @JsonProperty("valor")
    private BigDecimal valor;

    @NotNull
    @Positive
    @JsonProperty("prazoMeses")
    private Integer prazoMeses;

    @NotNull
    @JsonProperty("tipoProduto")
    private TipoProduto tipoProduto;

}
