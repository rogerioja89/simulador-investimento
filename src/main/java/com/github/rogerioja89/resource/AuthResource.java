package com.github.rogerioja89.resource;

import com.github.rogerioja89.dto.LoginRequest;
import com.github.rogerioja89.dto.TokenResponse;
import com.github.rogerioja89.service.AuthService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    AuthService authService;

    @POST
    @Path("/login")
    public TokenResponse login(@Valid LoginRequest request) {
        return authService.autenticar(request.getUsername(), request.getPassword());
    }
}

