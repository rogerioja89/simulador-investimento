package com.github.rogerioja89.service;

import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@ApplicationScoped
public class CalculoFinanceiroServiceImpl implements CalculoFinanceiroService {

    private static final MathContext MC = new MathContext(12, RoundingMode.HALF_UP);

    @Override
    public BigDecimal calcularValorFinal(BigDecimal valorInvestido, BigDecimal rentabilidadeAnual, Integer prazoMeses) {
        BigDecimal taxaMensal = rentabilidadeAnual.divide(BigDecimal.valueOf(12), MC);
        BigDecimal fator = BigDecimal.ONE.add(taxaMensal, MC).pow(prazoMeses, MC);
        return valorInvestido.multiply(fator, MC).setScale(2, RoundingMode.HALF_UP);
    }
}

