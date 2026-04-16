package com.github.rogerioja89.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.rogerioja89.entity.TipoProduto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class SimulacaoRequest {

    @NotNull
    @Positive
    @JsonProperty("clienteId")
    private Long clienteId;

    @NotNull
    @Positive
    @JsonProperty("valor")
    private BigDecimal valor;

    @NotNull
    @Positive
    @JsonProperty("prazoMeses")
    private Integer prazoMeses;

    @NotNull
    @JsonProperty("tipoProduto")
    private TipoProduto tipoProduto;

    public SimulacaoRequest() {
    }

    public SimulacaoRequest(Long clienteId, BigDecimal valor, Integer prazoMeses, TipoProduto tipoProduto) {
        this.clienteId = clienteId;
        this.valor = valor;
        this.prazoMeses = prazoMeses;
        this.tipoProduto = tipoProduto;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getPrazoMeses() {
        return prazoMeses;
    }

    public void setPrazoMeses(Integer prazoMeses) {
        this.prazoMeses = prazoMeses;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
}
