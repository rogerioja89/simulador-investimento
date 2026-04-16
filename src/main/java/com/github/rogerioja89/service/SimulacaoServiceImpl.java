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
                request.tipoProduto,
                request.valor,
                request.prazoMeses
        );

        BigDecimal valorFinal = calculoFinanceiroService.calcularValorFinal(
                request.valor,
                produtoElegivel.rentabilidadeAnual,
                request.prazoMeses
        );

        OffsetDateTime dataSimulacao = OffsetDateTime.now(ZoneOffset.UTC);
        Simulacao entidade = new Simulacao();
        entidade.clienteId = request.clienteId;
        entidade.produtoNome = produtoElegivel.nome;
        entidade.tipoProduto = produtoElegivel.tipoProduto;
        entidade.valorInvestido = request.valor;
        entidade.prazoMeses = request.prazoMeses;
        entidade.rentabilidadeAplicada = produtoElegivel.rentabilidadeAnual;
        entidade.valorFinal = valorFinal;
        entidade.dataSimulacao = dataSimulacao;
        simulacaoRepository.persist(entidade);

        return mapearSimulacaoResponse(produtoElegivel, valorFinal, request.prazoMeses, dataSimulacao);
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
        produtoValidado.id = produto.id;
        produtoValidado.nome = produto.nome;
        produtoValidado.tipo = produto.tipoProduto;
        produtoValidado.rentabilidade = produto.rentabilidadeAnual;
        produtoValidado.risco = produto.risco;

        ResultadoSimulacaoResponse resultado = new ResultadoSimulacaoResponse();
        resultado.valorFinal = valorFinal;
        resultado.prazoMeses = prazoMeses;

        SimulacaoResponse response = new SimulacaoResponse();
        response.produtoValidado = produtoValidado;
        response.resultadoSimulacao = resultado;
        response.dataSimulacao = dataSimulacao;
        return response;
    }

    private HistoricoSimulacaoResponse mapearHistorico(Simulacao simulacao) {
        HistoricoSimulacaoResponse response = new HistoricoSimulacaoResponse();
        response.id = simulacao.id;
        response.clienteId = simulacao.clienteId;
        response.produto = simulacao.produtoNome;
        response.tipoProduto = simulacao.tipoProduto;
        response.valorInvestido = simulacao.valorInvestido;
        response.valorFinal = simulacao.valorFinal;
        response.prazoMeses = simulacao.prazoMeses;
        response.dataSimulacao = simulacao.dataSimulacao;
        return response;
    }
}

