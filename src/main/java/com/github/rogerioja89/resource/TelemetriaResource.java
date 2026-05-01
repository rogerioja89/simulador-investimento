package com.github.rogerioja89.resource;

import com.github.rogerioja89.dto.TelemetriaResponse;
import com.github.rogerioja89.service.TelemetriaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/telemetria")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("USER")
public class TelemetriaResource {

    @Inject
    TelemetriaService telemetriaService;

    @GET
    public TelemetriaResponse buscarTelemetria() {
        return telemetriaService.consultarTelemetria();
    }
}
