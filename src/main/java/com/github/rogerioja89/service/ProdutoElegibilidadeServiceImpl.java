package com.github.rogerioja89.service;

import com.github.rogerioja89.entity.Produto;
import com.github.rogerioja89.entity.TipoProduto;
import com.github.rogerioja89.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;

import java.math.BigDecimal;

@ApplicationScoped
public class ProdutoElegibilidadeServiceImpl implements ProdutoElegibilidadeService {

    @Inject
    ProdutoRepository produtoRepository;

    @Override
    public Produto selecionarProdutoElegivel(TipoProduto tipoProduto, BigDecimal valor, Integer prazoMeses) {
        return produtoRepository.buscarProdutoElegivel(tipoProduto, valor, prazoMeses)
                .orElseThrow(() -> new WebApplicationException("Nenhum produto elegivel para os parametros informados.", 422));
    }
}

