package com.github.rogerioja89.resource;

import com.github.rogerioja89.service.TelemetriaService;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.USER)
public class TelemetriaFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final String START_TIME_NANOS = "telemetria-start-nanos";

    @Inject
    TelemetriaService telemetriaService;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        requestContext.setProperty(START_TIME_NANOS, System.nanoTime());
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        Object startNanosObj = requestContext.getProperty(START_TIME_NANOS);
        if (!(startNanosObj instanceof Long startNanos)) {
            return;
        }

        String metodo = requestContext.getMethod();
        String path = normalizarPath(requestContext.getUriInfo().getPath());

        // Evita registrar chamadas do proprio endpoint de telemetria.
        if (path.startsWith("/telemetria")) {
            return;
        }

        long duracaoMs = (System.nanoTime() - startNanos) / 1_000_000L;
        telemetriaService.registrarEvento(metodo, path, responseContext.getStatus(), duracaoMs);
    }

    private String normalizarPath(String path) {
        if (path == null || path.isBlank()) {
            return "/";
        }
        return path.startsWith("/") ? path : "/" + path;
    }
}

