# Simulador de Investimentos (Quarkus)

API REST para simular investimentos, validar produto elegivel e salvar historico.

## Stack

- Java 21
- Quarkus (REST + Hibernate ORM Panache)
- Hibernate Validator
- SQLite (execucao normal)
- H2 (testes)

## Endpoints MVP

- `POST /simulacoes`
- `GET /simulacoes?clienteId=123`

## Estrutura do projeto

- `src/main/java/com/github/rogerioja89/entity`: entidades e enums
- `src/main/java/com/github/rogerioja89/repository`: repositorios Panache
- `src/main/java/com/github/rogerioja89/service`: regras de negocio (SOLID)
- `src/main/java/com/github/rogerioja89/resource`: API REST
- `src/main/java/com/github/rogerioja89/dto`: contratos de request/response

## Seed de dados

O seed e automatico e acontece no startup em `SeedDataService`.
Se a tabela `produtos` estiver vazia, os produtos padrao sao inseridos.

## Testes

```powershell
.\mvnw.cmd test
```

