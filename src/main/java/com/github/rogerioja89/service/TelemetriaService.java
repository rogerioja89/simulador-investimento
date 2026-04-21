package com.github.rogerioja89.service;

import com.github.rogerioja89.dto.TelemetriaResponse;

public interface TelemetriaService {

    void registrarEvento(String metodoHttp, String path, Integer statusCode, Long duracaoMs);

    TelemetriaResponse consultarTelemetria();
}

