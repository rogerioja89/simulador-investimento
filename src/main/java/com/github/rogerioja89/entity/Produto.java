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

@Entity
@Table(name = "produtos")
@Data
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
}
