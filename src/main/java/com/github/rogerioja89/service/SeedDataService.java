package com.github.rogerioja89.service;

import com.github.rogerioja89.entity.Produto;
import com.github.rogerioja89.entity.Risco;
import com.github.rogerioja89.entity.TipoProduto;
import com.github.rogerioja89.repository.ProdutoRepository;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class SeedDataService {

    @Inject
    ProdutoRepository produtoRepository;

    @Transactional
    public void onStart(@Observes StartupEvent event) {
        if (produtoRepository.count() > 0) {
            return;
        }

        List<Produto> produtos = List.of(
                criarProduto("CDB Caixa 2026", TipoProduto.CDB, "0.120000", Risco.BAIXO, 6, 24, "1000.00", "50000.00"),
                criarProduto("LCI Caixa 2027", TipoProduto.LCI, "0.105000", Risco.BAIXO, 12, 36, "5000.00", "100000.00"),
                criarProduto("LCA Agro Caixa", TipoProduto.LCA, "0.130000", Risco.MEDIO, 9, 30, "3000.00", "80000.00"),
                criarProduto("CDB Plus", TipoProduto.CDB, "0.145000", Risco.ALTO, 3, 18, "15000.00", "150000.00")
        );

        produtoRepository.persist(produtos);
    }

    private Produto criarProduto(
            String nome,
            TipoProduto tipoProduto,
            String rentabilidade,
            Risco risco,
            int prazoMin,
            int prazoMax,
            String valorMin,
            String valorMax
    ) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setTipoProduto(tipoProduto);
        produto.setRentabilidadeAnual(new BigDecimal(rentabilidade));
        produto.setRisco(risco);
        produto.setPrazoMinMeses(prazoMin);
        produto.setPrazoMaxMeses(prazoMax);
        produto.setValorMin(new BigDecimal(valorMin));
        produto.setValorMax(new BigDecimal(valorMax));
        return produto;
    }
}
