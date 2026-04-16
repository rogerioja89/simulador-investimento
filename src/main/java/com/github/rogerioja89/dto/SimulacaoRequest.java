package com.github.rogerioja89.dto;

import com.github.rogerioja89.entity.TipoProduto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class SimulacaoRequest {

    @NotNull
    @Positive
    public Long clienteId;

    @NotNull
    @Positive
    public BigDecimal valor;

    @NotNull
    @Positive
    public Integer prazoMeses;

    @NotNull
    public TipoProduto tipoProduto;
}

