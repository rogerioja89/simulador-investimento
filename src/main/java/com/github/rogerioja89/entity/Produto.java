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
	public Long id;

	@Column(nullable = false, length = 120)
	public String nome;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	public TipoProduto tipoProduto;

	@Column(nullable = false, precision = 10, scale = 6)
	public BigDecimal rentabilidadeAnual;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	public Risco risco;

	@Column(nullable = false)
	public Integer prazoMinMeses;

	@Column(nullable = false)
	public Integer prazoMaxMeses;

	@Column(nullable = false, precision = 19, scale = 2)
	public BigDecimal valorMin;

	@Column(nullable = false, precision = 19, scale = 2)
	public BigDecimal valorMax;
}
