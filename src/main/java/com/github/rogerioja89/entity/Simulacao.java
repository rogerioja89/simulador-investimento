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
import java.time.OffsetDateTime;

@Entity
@Table(name = "simulacoes")
public class Simulacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long clienteId;

    @Column(nullable = false, length = 120)
    private String produtoNome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoProduto tipoProduto;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valorInvestido;

    @Column(nullable = false)
    private Integer prazoMeses;

    @Column(nullable = false, precision = 10, scale = 6)
    private BigDecimal rentabilidadeAplicada;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valorFinal;

    @Column(nullable = false)
    private OffsetDateTime dataSimulacao;

    public Simulacao() {
    }

    public Simulacao(
            Long id,
            Long clienteId,
            String produtoNome,
            TipoProduto tipoProduto,
            BigDecimal valorInvestido,
            Integer prazoMeses,
            BigDecimal rentabilidadeAplicada,
            BigDecimal valorFinal,
            OffsetDateTime dataSimulacao
    ) {
        this.id = id;
        this.clienteId = clienteId;
        this.produtoNome = produtoNome;
        this.tipoProduto = tipoProduto;
        this.valorInvestido = valorInvestido;
        this.prazoMeses = prazoMeses;
        this.rentabilidadeAplicada = rentabilidadeAplicada;
        this.valorFinal = valorFinal;
        this.dataSimulacao = dataSimulacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public BigDecimal getValorInvestido() {
        return valorInvestido;
    }

    public void setValorInvestido(BigDecimal valorInvestido) {
        this.valorInvestido = valorInvestido;
    }

    public Integer getPrazoMeses() {
        return prazoMeses;
    }

    public void setPrazoMeses(Integer prazoMeses) {
        this.prazoMeses = prazoMeses;
    }

    public BigDecimal getRentabilidadeAplicada() {
        return rentabilidadeAplicada;
    }

    public void setRentabilidadeAplicada(BigDecimal rentabilidadeAplicada) {
        this.rentabilidadeAplicada = rentabilidadeAplicada;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public OffsetDateTime getDataSimulacao() {
        return dataSimulacao;
    }

    public void setDataSimulacao(OffsetDateTime dataSimulacao) {
        this.dataSimulacao = dataSimulacao;
    }
}
