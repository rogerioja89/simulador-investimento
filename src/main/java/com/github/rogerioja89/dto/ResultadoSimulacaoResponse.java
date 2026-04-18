package com.github.rogerioja89.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResultadoSimulacaoResponse {

    private BigDecimal valorFinal;
    private Integer prazoMeses;

}
