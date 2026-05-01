package com.github.rogerioja89.service;

import com.github.rogerioja89.dto.TelemetriaEndpointResponse;
import com.github.rogerioja89.dto.TelemetriaResponse;
import com.github.rogerioja89.entity.TelemetriaEvento;
import com.github.rogerioja89.repository.TelemetriaEventoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@ApplicationScoped
public class TelemetriaServiceImpl implements TelemetriaService {

    @Inject
    TelemetriaEventoRepository repository;

    @Override
    @Transactional
    public void registrarEvento(String metodoHttp, String path, Integer statusCode, Long duracaoMs) {
        TelemetriaEvento evento = new TelemetriaEvento();
        evento.setMetodoHttp(metodoHttp);
        evento.setPath(path);
        evento.setEndpoint(metodoHttp + " " + path);
        evento.setStatusCode(statusCode);
        evento.setDuracaoMs(duracaoMs);
        evento.setDataHora(OffsetDateTime.now(ZoneOffset.UTC));

        repository.persist(evento);
    }

    @Override
    public TelemetriaResponse consultarTelemetria() {
        List<TelemetriaEndpointResponse> agregado = repository.buscarAgregadoPorEndpoint();
        TelemetriaResponse response = new TelemetriaResponse();
        response.setGeneratedAt(OffsetDateTime.now(ZoneOffset.UTC));
        response.setServices(agregado);
        return response;
    }
}