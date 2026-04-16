package com.github.rogerioja89.repository;

import com.github.rogerioja89.entity.Produto;
import com.github.rogerioja89.entity.TipoProduto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.util.Optional;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {

    public Optional<Produto> buscarProdutoElegivel(TipoProduto tipoProduto, BigDecimal valor, Integer prazoMeses) {
        return find(
                "tipoProduto = ?1 and valorMin <= ?2 and valorMax >= ?2 and prazoMinMeses <= ?3 and prazoMaxMeses >= ?3 order by rentabilidadeAnual desc",
                tipoProduto,
                valor,
                prazoMeses
        ).firstResultOptional();
    }
}

