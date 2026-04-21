package com.github.rogerioja89.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(name = "telemetria_eventos")
@Data
public class TelemetriaEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "endpoint", nullable = false, length = 130)
    private String endpoint;

    @Column(name = "metodo_http", nullable = false, length = 10)
    private String metodoHttp;

    @Column(name = "path", nullable = false, length = 120)
    private String path;

    @Column(name = "status_code", nullable = false)
    private Integer statusCode;

    @Column(name = "duracao_ms", nullable = false)
    private Long duracaoMs;

    @Column(name = "data_hora", nullable = false)
    private OffsetDateTime dataHora;
}
