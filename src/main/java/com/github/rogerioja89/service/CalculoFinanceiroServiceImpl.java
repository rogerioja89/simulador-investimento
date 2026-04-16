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
        BigDecimal base = BigDecimal.ONE.add(taxaMensal, MC);

        BigDecimal fator = BigDecimal.ONE;
        for (int i = 0; i < prazoMeses; i++) {
            fator = fator.multiply(base, MC);
        }

        return valorInvestido.multiply(fator, MC).setScale(2, RoundingMode.HALF_UP);
    }
}

