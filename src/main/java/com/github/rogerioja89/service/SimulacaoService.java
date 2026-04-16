package com.github.rogerioja89.service;

import com.github.rogerioja89.dto.HistoricoSimulacaoResponse;
import com.github.rogerioja89.dto.SimulacaoRequest;
import com.github.rogerioja89.dto.SimulacaoResponse;

import java.util.List;

public interface SimulacaoService {

    SimulacaoResponse criarSimulacao(SimulacaoRequest request);

    List<HistoricoSimulacaoResponse> buscarHistorico(Long clienteId);
}

