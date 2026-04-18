package com.github.rogerioja89.dto;

import com.github.rogerioja89.entity.TipoProduto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class HistoricoSimulacaoResponse {

    private Long id;
    private Long clienteId;
    private String produto;
    private TipoProduto tipoProduto;
    private BigDecimal valorInvestido;
    private BigDecimal valorFinal;
    private Integer prazoMeses;
    private OffsetDateTime dataSimulacao;
}
