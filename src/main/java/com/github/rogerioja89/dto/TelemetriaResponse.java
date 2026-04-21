package com.github.rogerioja89.dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class TelemetriaResponse {

    private OffsetDateTime generatedAt;
    private List<TelemetriaEndpointResponse> services;
}

