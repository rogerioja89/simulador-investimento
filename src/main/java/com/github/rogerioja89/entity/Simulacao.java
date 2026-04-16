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
    public Long id;

    @Column(nullable = false)
    public Long clienteId;

    @Column(nullable = false, length = 120)
    public String produtoNome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    public TipoProduto tipoProduto;

    @Column(nullable = false, precision = 19, scale = 2)
    public BigDecimal valorInvestido;

    @Column(nullable = false)
    public Integer prazoMeses;

    @Column(nullable = false, precision = 10, scale = 6)
    public BigDecimal rentabilidadeAplicada;

    @Column(nullable = false, precision = 19, scale = 2)
    public BigDecimal valorFinal;

    @Column(nullable = false)
    public OffsetDateTime dataSimulacao;
}

