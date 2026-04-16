package com.github.rogerioja89.dto;

import com.github.rogerioja89.entity.Risco;
import com.github.rogerioja89.entity.TipoProduto;

import java.math.BigDecimal;

public class ProdutoValidadoResponse {

    public Long id;
    public String nome;
    public TipoProduto tipo;
    public BigDecimal rentabilidade;
    public Risco risco;
}

