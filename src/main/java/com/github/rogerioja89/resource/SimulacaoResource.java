package com.github.rogerioja89.resource;

import com.github.rogerioja89.dto.HistoricoSimulacaoResponse;
import com.github.rogerioja89.dto.SimulacaoRequest;
import com.github.rogerioja89.dto.SimulacaoResponse;
import com.github.rogerioja89.service.SimulacaoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/simulacoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SimulacaoResource {

    @Inject
    SimulacaoService simulacaoService;

    @POST
    public Response criarSimulacao(@Valid SimulacaoRequest request) {
        SimulacaoResponse response = simulacaoService.criarSimulacao(request);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @GET
    public List<HistoricoSimulacaoResponse> buscarHistorico(@QueryParam("clienteId") Long clienteId) {
        if (clienteId == null || clienteId <= 0) {
            throw new BadRequestException("O parametro clienteId deve ser informado e positivo.");
        }
        return simulacaoService.buscarHistorico(clienteId);
    }
}

