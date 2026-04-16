package com.github.rogerioja89.service;

import com.github.rogerioja89.dto.HistoricoSimulacaoResponse;
import com.github.rogerioja89.dto.ProdutoValidadoResponse;
import com.github.rogerioja89.dto.ResultadoSimulacaoResponse;
import com.github.rogerioja89.dto.SimulacaoRequest;
import com.github.rogerioja89.dto.SimulacaoResponse;
import com.github.rogerioja89.entity.Produto;
import com.github.rogerioja89.entity.Simulacao;
import com.github.rogerioja89.repository.SimulacaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@ApplicationScoped
public class SimulacaoServiceImpl implements SimulacaoService {

    @Inject
    ProdutoElegibilidadeService produtoElegibilidadeService;

    @Inject
    CalculoFinanceiroService calculoFinanceiroService;

    @Inject
    SimulacaoRepository simulacaoRepository;

    @Override
    @Transactional
    public SimulacaoResponse criarSimulacao(SimulacaoRequest request) {
        Produto produtoElegivel = produtoElegibilidadeService.selecionarProdutoElegivel(
                request.getTipoProduto(),
                request.getValor(),
                request.getPrazoMeses()
        );

        BigDecimal valorFinal = calculoFinanceiroService.calcularValorFinal(
                request.getValor(),
                produtoElegivel.getRentabilidadeAnual(),
                request.getPrazoMeses()
        );

        OffsetDateTime dataSimulacao = OffsetDateTime.now(ZoneOffset.UTC);
        Simulacao entidade = new Simulacao();
        entidade.setClienteId(request.getClienteId());
        entidade.setProdutoNome(produtoElegivel.getNome());
        entidade.setTipoProduto(produtoElegivel.getTipoProduto());
        entidade.setValorInvestido(request.getValor());
        entidade.setPrazoMeses(request.getPrazoMeses());
        entidade.setRentabilidadeAplicada(produtoElegivel.getRentabilidadeAnual());
        entidade.setValorFinal(valorFinal);
        entidade.setDataSimulacao(dataSimulacao);
        simulacaoRepository.persist(entidade);

        return mapearSimulacaoResponse(produtoElegivel, valorFinal, request.getPrazoMeses(), dataSimulacao);
    }

    @Override
    public List<HistoricoSimulacaoResponse> buscarHistorico(Long clienteId) {
        return simulacaoRepository.listarPorClienteId(clienteId)
                .stream()
                .map(this::mapearHistorico)
                .toList();
    }

    private SimulacaoResponse mapearSimulacaoResponse(Produto produto, BigDecimal valorFinal, Integer prazoMeses, OffsetDateTime dataSimulacao) {
        ProdutoValidadoResponse produtoValidado = new ProdutoValidadoResponse();
        produtoValidado.setId(produto.getId());
        produtoValidado.setNome(produto.getNome());
        produtoValidado.setTipo(produto.getTipoProduto());
        produtoValidado.setRentabilidade(produto.getRentabilidadeAnual());
        produtoValidado.setRisco(produto.getRisco());

        ResultadoSimulacaoResponse resultado = new ResultadoSimulacaoResponse();
        resultado.setValorFinal(valorFinal);
        resultado.setPrazoMeses(prazoMeses);

        SimulacaoResponse response = new SimulacaoResponse();
        response.setProdutoValidado(produtoValidado);
        response.setResultadoSimulacao(resultado);
        response.setDataSimulacao(dataSimulacao);
        return response;
    }

    private HistoricoSimulacaoResponse mapearHistorico(Simulacao simulacao) {
        HistoricoSimulacaoResponse response = new HistoricoSimulacaoResponse();
        response.setId(simulacao.getId());
        response.setClienteId(simulacao.getClienteId());
        response.setProduto(simulacao.getProdutoNome());
        response.setTipoProduto(simulacao.getTipoProduto());
        response.setValorInvestido(simulacao.getValorInvestido());
        response.setValorFinal(simulacao.getValorFinal());
        response.setPrazoMeses(simulacao.getPrazoMeses());
        response.setDataSimulacao(simulacao.getDataSimulacao());
        return response;
    }
}
