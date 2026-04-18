package com.github.rogerioja89.resource;

import com.github.rogerioja89.dto.ApiErrorResponse;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.USER + 100)
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(WebApplicationException exception) {
        int status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        if (exception.getResponse() != null) {
            status = exception.getResponse().getStatus();
        }

        String message = exception.getMessage();
        if (message == null || message.isBlank()) {
            Response.StatusType statusType = Response.Status.fromStatusCode(status);
            message = statusType != null ? statusType.getReasonPhrase() : "Erro na requisicao";
        }

        ApiErrorResponse body = new ApiErrorResponse(message);
        return Response.status(status)
                .type(MediaType.APPLICATION_JSON)
                .entity(body)
                .build();
    }
}

