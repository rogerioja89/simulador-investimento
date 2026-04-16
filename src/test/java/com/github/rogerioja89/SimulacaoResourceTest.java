package com.github.rogerioja89;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
class SimulacaoResourceTest {

    @Test
    void deveCriarSimulacaoComSucesso() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("clienteId", 123);
        payload.put("valor", new BigDecimal("10000.00"));
        payload.put("prazoMeses", 12);
        payload.put("tipoProduto", "CDB");

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/simulacoes")
                .then()
                .statusCode(201)
                .body("produtoValidado.id", notNullValue())
                .body("resultadoSimulacao.valorFinal", notNullValue())
                .body("dataSimulacao", notNullValue());
    }

    @Test
    void deveRetornarHistoricoPorClienteId() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("clienteId", 456);
        payload.put("valor", new BigDecimal("12000.00"));
        payload.put("prazoMeses", 12);
        payload.put("tipoProduto", "CDB");

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/simulacoes")
                .then()
                .statusCode(201);

        given()
                .queryParam("clienteId", 456)
                .when()
                .get("/simulacoes")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    void deveRetornar422QuandoNaoHouverProdutoElegivel() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("clienteId", 999);
        payload.put("valor", new BigDecimal("100.00"));
        payload.put("prazoMeses", 6);
        payload.put("tipoProduto", "LCI");

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/simulacoes")
                .then()
                .statusCode(422);
    }
}

