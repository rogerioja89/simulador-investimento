package com.github.rogerioja89;

import com.github.rogerioja89.repository.TelemetriaEventoRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
class TelemetriaResourceTest {

    @Inject
    TelemetriaEventoRepository telemetriaEventoRepository;

    @BeforeEach
    @Transactional
    void limparTabelaTelemetria() {
        telemetriaEventoRepository.deleteAll();
    }

    @Test
    void deveRetornarDadosDeTelemetriaAgregados() {
        given()
                .when()
                .get("/simulacoes")
                .then()
                .statusCode(400);

        given()
                .when()
                .get("/telemetria")
                .then()
                .statusCode(200)
                .body("generatedAt", notNullValue())
                .body("services.size()", greaterThanOrEqualTo(1))
                .body("services.find { it.endpoint == 'GET /simulacoes' }.totalRequests", equalTo(1))
                .body("services.find { it.endpoint == 'GET /simulacoes' }.status4xx", equalTo(1));
    }
}

