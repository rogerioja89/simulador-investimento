package com.github.rogerioja89.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoProduto tipoProduto;

    @Column(nullable = false, precision = 10, scale = 6)
    private BigDecimal rentabilidadeAnual;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Risco risco;

    @Column(nullable = false)
    private Integer prazoMinMeses;

    @Column(nullable = false)
    private Integer prazoMaxMeses;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valorMin;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valorMax;

    public Produto() {
    }

    public Produto(
            Long id,
            String nome,
            TipoProduto tipoProduto,
            BigDecimal rentabilidadeAnual,
            Risco risco,
            Integer prazoMinMeses,
            Integer prazoMaxMeses,
            BigDecimal valorMin,
            BigDecimal valorMax
    ) {
        this.id = id;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.rentabilidadeAnual = rentabilidadeAnual;
        this.risco = risco;
        this.prazoMinMeses = prazoMinMeses;
        this.prazoMaxMeses = prazoMaxMeses;
        this.valorMin = valorMin;
        this.valorMax = valorMax;
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

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public BigDecimal getRentabilidadeAnual() {
        return rentabilidadeAnual;
    }

    public void setRentabilidadeAnual(BigDecimal rentabilidadeAnual) {
        this.rentabilidadeAnual = rentabilidadeAnual;
    }

    public Risco getRisco() {
        return risco;
    }

    public void setRisco(Risco risco) {
        this.risco = risco;
    }

    public Integer getPrazoMinMeses() {
        return prazoMinMeses;
    }

    public void setPrazoMinMeses(Integer prazoMinMeses) {
        this.prazoMinMeses = prazoMinMeses;
    }

    public Integer getPrazoMaxMeses() {
        return prazoMaxMeses;
    }

    public void setPrazoMaxMeses(Integer prazoMaxMeses) {
        this.prazoMaxMeses = prazoMaxMeses;
    }

    public BigDecimal getValorMin() {
        return valorMin;
    }

    public void setValorMin(BigDecimal valorMin) {
        this.valorMin = valorMin;
    }

    public BigDecimal getValorMax() {
        return valorMax;
    }

    public void setValorMax(BigDecimal valorMax) {
        this.valorMax = valorMax;
    }
}
