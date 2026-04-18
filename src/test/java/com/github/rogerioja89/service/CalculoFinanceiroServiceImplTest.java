package com.github.rogerioja89.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculoFinanceiroServiceImplTest {

    private static final MathContext EXPECTED_MC = new MathContext(20, RoundingMode.HALF_UP);

    private final CalculoFinanceiroServiceImpl service = new CalculoFinanceiroServiceImpl();

    @Test
    void deveCalcularValorFinalParaCdbCaixa2026() {
        BigDecimal valorInvestido = new BigDecimal("1000.00");
        BigDecimal rentabilidadeAnual = new BigDecimal("0.120000");
        int prazoMeses = 6;

        BigDecimal esperado = calcularEsperado(valorInvestido, rentabilidadeAnual, prazoMeses);
        BigDecimal resultado = service.calcularValorFinal(valorInvestido, rentabilidadeAnual, prazoMeses);

        assertEquals(esperado, resultado);
        assertEquals(2, resultado.scale());
    }

    @Test
    void deveCalcularValorFinalParaLciCaixa2027() {
        BigDecimal valorInvestido = new BigDecimal("5000.00");
        BigDecimal rentabilidadeAnual = new BigDecimal("0.105000");
        int prazoMeses = 12;

        BigDecimal esperado = calcularEsperado(valorInvestido, rentabilidadeAnual, prazoMeses);
        BigDecimal resultado = service.calcularValorFinal(valorInvestido, rentabilidadeAnual, prazoMeses);

        assertEquals(esperado, resultado);
        assertEquals(2, resultado.scale());
    }

    @Test
    void deveCalcularValorFinalParaLcaAgroCaixa() {
        BigDecimal valorInvestido = new BigDecimal("3000.00");
        BigDecimal rentabilidadeAnual = new BigDecimal("0.130000");
        int prazoMeses = 9;

        BigDecimal esperado = calcularEsperado(valorInvestido, rentabilidadeAnual, prazoMeses);
        BigDecimal resultado = service.calcularValorFinal(valorInvestido, rentabilidadeAnual, prazoMeses);

        assertEquals(esperado, resultado);
        assertEquals(2, resultado.scale());
    }

    @Test
    void deveCalcularValorFinalParaCdbPlus() {
        BigDecimal valorInvestido = new BigDecimal("15000.00");
        BigDecimal rentabilidadeAnual = new BigDecimal("0.145000");
        int prazoMeses = 3;

        BigDecimal esperado = calcularEsperado(valorInvestido, rentabilidadeAnual, prazoMeses);
        BigDecimal resultado = service.calcularValorFinal(valorInvestido, rentabilidadeAnual, prazoMeses);

        assertEquals(esperado, resultado);
        assertEquals(2, resultado.scale());
    }

    private BigDecimal calcularEsperado(BigDecimal valorInvestido, BigDecimal rentabilidadeAnual, int prazoMeses) {
        BigDecimal taxaMensal = rentabilidadeAnual.divide(BigDecimal.valueOf(12), 16, RoundingMode.HALF_UP);
        BigDecimal fator = BigDecimal.ONE.add(taxaMensal).pow(prazoMeses, EXPECTED_MC);
        return valorInvestido.multiply(fator).setScale(2, RoundingMode.HALF_UP);
    }
}

