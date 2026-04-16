package com.github.rogerioja89.dto;

import com.github.rogerioja89.entity.TipoProduto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class HistoricoSimulacaoResponse {

    private Long id;
    private Long clienteId;
    private String produto;
    private TipoProduto tipoProduto;
    private BigDecimal valorInvestido;
    private BigDecimal valorFinal;
    private Integer prazoMeses;
    private OffsetDateTime dataSimulacao;

    public HistoricoSimulacaoResponse() {
    }

    public HistoricoSimulacaoResponse(
            Long id,
            Long clienteId,
            String produto,
            TipoProduto tipoProduto,
            BigDecimal valorInvestido,
            BigDecimal valorFinal,
            Integer prazoMeses,
            OffsetDateTime dataSimulacao
    ) {
        this.id = id;
        this.clienteId = clienteId;
        this.produto = produto;
        this.tipoProduto = tipoProduto;
        this.valorInvestido = valorInvestido;
        this.valorFinal = valorFinal;
        this.prazoMeses = prazoMeses;
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

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
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

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Integer getPrazoMeses() {
        return prazoMeses;
    }

    public void setPrazoMeses(Integer prazoMeses) {
        this.prazoMeses = prazoMeses;
    }

    public OffsetDateTime getDataSimulacao() {
        return dataSimulacao;
    }

    public void setDataSimulacao(OffsetDateTime dataSimulacao) {
        this.dataSimulacao = dataSimulacao;
    }
}
