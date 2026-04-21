package com.github.rogerioja89.dto;

import lombok.Data;

@Data
public class TelemetriaEndpointResponse {

    private String endpoint;
    private Long totalRequests;
    private Double avgResponseMs;
    private Long minResponseMs;
    private Long maxResponseMs;
    private Long status2xx;
    private Long status4xx;
    private Long status5xx;
}

