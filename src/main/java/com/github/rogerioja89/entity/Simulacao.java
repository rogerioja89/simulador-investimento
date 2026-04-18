package com.github.rogerioja89.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "simulacoes")
@Data
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
}
