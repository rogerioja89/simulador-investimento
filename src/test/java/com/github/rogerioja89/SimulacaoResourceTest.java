package com.github.rogerioja89;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
class SimulacaoResourceTest {

    @Test
    void deveCriarSimulacaoComSucesso() {
        String token = obterToken();

        Map<String, Object> payload = new HashMap<>();
        payload.put("clienteId", 123);
        payload.put("valor", new BigDecimal("10000.00"));
        payload.put("prazoMeses", 12);
        payload.put("tipoProduto", "CDB");

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
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
        String token = obterToken();

        Map<String, Object> payload = new HashMap<>();
        payload.put("clienteId", 456);
        payload.put("valor", new BigDecimal("12000.00"));
        payload.put("prazoMeses", 12);
        payload.put("tipoProduto", "CDB");

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .when()
                .post("/simulacoes")
                .then()
                .statusCode(201);

        given()
                .header("Authorization", "Bearer " + token)
                .queryParam("clienteId", 456)
                .when()
                .get("/simulacoes")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    void deveRetornar422QuandoNaoHouverProdutoElegivel() {
        String token = obterToken();

        Map<String, Object> payload = new HashMap<>();
        payload.put("clienteId", 999);
        payload.put("valor", new BigDecimal("100.00"));
        payload.put("prazoMeses", 6);
        payload.put("tipoProduto", "LCI");

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .when()
                .post("/simulacoes")
                .then()
                .statusCode(422)
                .body("message", equalTo("Nenhum produto elegivel para os parametros informados."));
    }

    @Test
    void deveRetornarMensagemQuandoClienteIdInvalidoNoHistorico() {
        String token = obterToken();

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/simulacoes")
                .then()
                .statusCode(400)
                .body("message", equalTo("O parametro clienteId deve ser informado e positivo."));
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
