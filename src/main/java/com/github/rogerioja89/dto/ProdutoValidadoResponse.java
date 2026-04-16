package com.github.rogerioja89.dto;

import com.github.rogerioja89.entity.Risco;
import com.github.rogerioja89.entity.TipoProduto;

import java.math.BigDecimal;

public class ProdutoValidadoResponse {

    private Long id;
    private String nome;
    private TipoProduto tipo;
    private BigDecimal rentabilidade;
    private Risco risco;

    public ProdutoValidadoResponse() {
    }

    public ProdutoValidadoResponse(Long id, String nome, TipoProduto tipo, BigDecimal rentabilidade, Risco risco) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.rentabilidade = rentabilidade;
        this.risco = risco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getRentabilidade() {
        return rentabilidade;
    }

    public void setRentabilidade(BigDecimal rentabilidade) {
        this.rentabilidade = rentabilidade;
    }

    public Risco getRisco() {
        return risco;
    }

    public void setRisco(Risco risco) {
        this.risco = risco;
    }
}
