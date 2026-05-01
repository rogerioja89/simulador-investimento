package com.github.rogerioja89;

import com.github.rogerioja89.repository.TelemetriaEventoRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
        String token = obterToken();

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/simulacoes")
                .then()
                .statusCode(400);

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/telemetria")
                .then()
                .statusCode(200)
                .body("generatedAt", notNullValue())
                .body("services.size()", greaterThanOrEqualTo(1))
                .body("services.find { it.endpoint == 'GET /simulacoes' }.totalRequests", equalTo(1))
                .body("services.find { it.endpoint == 'GET /simulacoes' }.status4xx", equalTo(1));
    }

    private String obterToken() {
        Map<String, Object> loginPayload = new HashMap<>();
        loginPayload.put("username", "rogerio");
        loginPayload.put("password", "123456");

        return given()
                .contentType(ContentType.JSON)
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }
}
