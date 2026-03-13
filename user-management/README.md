# User Management

API REST de gestão de utilizadores construída com Java 17, Spring Boot 3 e PostgreSQL.

## Tecnologias

- **Java 17** — Records, sealed classes, text blocks
- **Spring Boot 3.2** — Web, Data JPA, Validation
- **PostgreSQL 16** — Base de dados relacional
- **Docker + Docker Compose** — Containerização e orquestração
- **Maven** — Gestão de dependências e build

## Como executar

### Com Docker Compose (recomendado)

```bash
# Clonar o repositório
git clone https://github.com/eduardojpanzo/training-java.git
cd training-java/user-management

# Subir a aplicação e o banco de dados
docker compose up --build

# A API estará disponível em http://localhost:8080
```

### Localmente (requer PostgreSQL instalado)

```bash
# Criar a base de dados
createdb user_management

# Executar a aplicação
./mvnw spring-boot:run
```

## Endpoints

| Método | Endpoint      | Descrição                    | Status de sucesso |
| ------ | ------------- | ---------------------------- | ----------------- |
| POST   | `/users`      | Criar utilizador             | 201 Created       |
| GET    | `/users`      | Listar todos os utilizadores | 200 OK            |
| GET    | `/users/{id}` | Buscar utilizador por ID     | 200 OK            |
| DELETE | `/users/{id}` | Eliminar utilizador          | 204 No Content    |

## Exemplos de uso

### Criar utilizador

```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"name": "Maria Silva", "email": "maria@exemplo.ao"}'
```

**Resposta (201 Created):**

```json
{
  "id": 1,
  "name": "Maria Silva",
  "email": "maria@exemplo.ao",
  "createdAt": "2024-01-15T10:30:00Z"
}
```

### Listar utilizadores

```bash
curl http://localhost:8080/users
```

### Buscar por ID

```bash
curl http://localhost:8080/users/1
```

### Eliminar utilizador

```bash
curl -X DELETE http://localhost:8080/users/1
```

## Tratamento de erros

| Situação                  | Status HTTP | Mensagem                       |
| ------------------------- | ----------- | ------------------------------ |
| Utilizador não encontrado | 404         | "Utilizador não encontrado..." |
| Email duplicado           | 409         | "Email já registado: ..."      |
| Campos inválidos          | 400         | Mapa campo → mensagem de erro  |

### Exemplo de erro de validação (400)

```json
{
  "name": "Nome é obrigatório",
  "email": "Email inválido"
}
```

## Executar testes

```bash
./mvnw test
```

Os testes usam H2 in-memory — não é necessário PostgreSQL para testar.

## Decisões de arquitectura

**DTOs em vez de Entities na API** — a `UserResponse` isola o contrato da API do modelo de dados interno, permitindo evoluir o schema sem quebrar clientes.

**`@Transactional(readOnly = true)` por padrão no Service** — optimiza operações de leitura e torna explícito quais métodos escrevem na base de dados.

**`@ControllerAdvice` centralizado** — erros de negócio são capturados num único local, mantendo os Controllers limpos.

**Dockerfile multi-stage** — a imagem final contém apenas o JRE e o JAR, sem ferramentas de build — menor e mais segura.
