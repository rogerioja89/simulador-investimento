package com.github.rogerioja89.service;

import java.math.BigDecimal;

public interface CalculoFinanceiroService {

    BigDecimal calcularValorFinal(BigDecimal valorInvestido, BigDecimal rentabilidadeAnual, Integer prazoMeses);
}

