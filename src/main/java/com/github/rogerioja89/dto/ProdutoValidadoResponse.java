package com.github.rogerioja89.dto;

import com.github.rogerioja89.entity.Risco;
import com.github.rogerioja89.entity.TipoProduto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoValidadoResponse {

    private Long id;
    private String nome;
    private TipoProduto tipo;
    private BigDecimal rentabilidade;
    private Risco risco;

}
