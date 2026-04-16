package com.github.rogerioja89.dto;

import com.github.rogerioja89.entity.TipoProduto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class HistoricoSimulacaoResponse {

    public Long id;
    public Long clienteId;
    public String produto;
    public TipoProduto tipoProduto;
    public BigDecimal valorInvestido;
    public BigDecimal valorFinal;
    public Integer prazoMeses;
    public OffsetDateTime dataSimulacao;
}

