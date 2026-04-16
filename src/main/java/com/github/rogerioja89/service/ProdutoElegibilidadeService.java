package com.github.rogerioja89.service;

import com.github.rogerioja89.entity.Produto;
import com.github.rogerioja89.entity.TipoProduto;

import java.math.BigDecimal;

public interface ProdutoElegibilidadeService {

    Produto selecionarProdutoElegivel(TipoProduto tipoProduto, BigDecimal valor, Integer prazoMeses);
}

