package com.github.rogerioja89.service;

import com.github.rogerioja89.dto.TelemetriaEndpointResponse;
import com.github.rogerioja89.dto.TelemetriaResponse;
import com.github.rogerioja89.repository.TelemetriaEventoRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class TelemetriaServiceImplTest {

    @Inject
    TelemetriaService telemetriaService;

    @Inject
    TelemetriaEventoRepository telemetriaEventoRepository;

    @BeforeEach
    @Transactional
    void limparTabelaTelemetria() {
        telemetriaEventoRepository.deleteAll();
    }

    @Test
    void deveConsolidarMetricasPorEndpoint() {
        telemetriaService.registrarEvento("POST", "/simulacoes", 201, 40L);
        telemetriaService.registrarEvento("POST", "/simulacoes", 422, 20L);
        telemetriaService.registrarEvento("GET", "/simulacoes", 200, 10L);

        TelemetriaResponse response = telemetriaService.consultarTelemetria();

        assertNotNull(response);
        assertNotNull(response.getGeneratedAt());
        assertEquals(2, response.getServices().size());

        TelemetriaEndpointResponse postSimulacoes = response.getServices()
                .stream()
                .filter(item -> "POST /simulacoes".equals(item.getEndpoint()))
                .findFirst()
                .orElseThrow();

        assertEquals(2L, postSimulacoes.getTotalRequests());
        assertEquals(30.0, postSimulacoes.getAvgResponseMs(), 0.0001);
        assertEquals(20L, postSimulacoes.getMinResponseMs());
        assertEquals(40L, postSimulacoes.getMaxResponseMs());
        assertEquals(1L, postSimulacoes.getStatus2xx());
        assertEquals(1L, postSimulacoes.getStatus4xx());
        assertEquals(0L, postSimulacoes.getStatus5xx());
    }
}

