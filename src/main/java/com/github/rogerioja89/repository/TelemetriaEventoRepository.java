package com.github.rogerioja89.repository;

import com.github.rogerioja89.dto.TelemetriaEndpointResponse;
import com.github.rogerioja89.entity.TelemetriaEvento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TelemetriaEventoRepository implements PanacheRepository<TelemetriaEvento> {

    @PersistenceContext
    EntityManager em;

    public List<TelemetriaEndpointResponse> buscarAgregadoPorEndpoint() {
        String sql = """
                SELECT
                    endpoint,
                    COUNT(*) AS totalRequests,
                    AVG(duracao_ms) AS avgResponseMs,
                    MIN(duracao_ms) AS minResponseMs,
                    MAX(duracao_ms) AS maxResponseMs,
                    SUM(CASE WHEN status_code BETWEEN 200 AND 299 THEN 1 ELSE 0 END) AS status2xx,
                    SUM(CASE WHEN status_code BETWEEN 400 AND 499 THEN 1 ELSE 0 END) AS status4xx,
                    SUM(CASE WHEN status_code BETWEEN 500 AND 599 THEN 1 ELSE 0 END) AS status5xx
                FROM telemetria_eventos
                GROUP BY endpoint
                ORDER BY endpoint
                """;

        List<Object[]> rows = em.createNativeQuery(sql).getResultList();
        List<TelemetriaEndpointResponse> resultado = new ArrayList<>();

        for (Object[] row : rows) {
            TelemetriaEndpointResponse dto = new TelemetriaEndpointResponse();
            dto.setEndpoint((String) row[0]);
            dto.setTotalRequests(((Number) row[1]).longValue());
            dto.setAvgResponseMs(row[2] == null ? 0.0 : ((Number) row[2]).doubleValue());
            dto.setMinResponseMs(row[3] == null ? 0L : ((Number) row[3]).longValue());
            dto.setMaxResponseMs(row[4] == null ? 0L : ((Number) row[4]).longValue());
            dto.setStatus2xx(row[5] == null ? 0L : ((Number) row[5]).longValue());
            dto.setStatus4xx(row[6] == null ? 0L : ((Number) row[6]).longValue());
            dto.setStatus5xx(row[7] == null ? 0L : ((Number) row[7]).longValue());
            resultado.add(dto);
        }

        return resultado;
    }
}

