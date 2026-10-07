# Author Management API

API REST para gerenciamento de autores, livros e administradores, desenvolvida com Java e Spring Boot.

O projeto foi criado para fins de estudo e prática de desenvolvimento backend, com foco em:

- REST APIs
- Arquitetura em camadas
- Persistência com JPA
- Validação de dados
- Tratamento global de exceções
- Autenticação com JWT
- Paginação e filtros
- Documentação com OpenAPI/Swagger

## 🚀 Tecnologias

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Spring Security
- Spring Validation
- Springdoc OpenAPI
- Swagger UI
- H2 Database
- MapStruct
- Lombok
- Auth0 Java JWT
- Gradle

## ✨ Funcionalidades

### Autores
- Cadastro de autores
- Busca por ID
- Listagem com paginação
- Filtro por nome
- Atualização de autores
- Exclusão de autores
- Bloqueio de exclusão quando há livros associados

### Administradores
- Cadastro de administradores
- Busca por ID
- Listagem com paginação
- Atualização de dados
- Exclusão de administradores
- Ativação e desativação de administradores

### Autenticação
- Login com email e senha
- Emissão de JWT
- Proteção de endpoints
- Autenticação stateless

### Documentação
- Swagger UI
- OpenAPI
- Testes de endpoints autenticados

## 🏗️ Estrutura do projeto

```text
Author-Management/
├── README.md
└── api-server/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── br/com/apiserver/
    │   │   │       ├── authentication
    │   │   │       ├── author
    │   │   │       ├── configuration
    │   │   │       ├── exception
    │   │   │       ├── security
    │   │   │       ├── work
    │   │   │       └── ApiServerApplication.java
    │   │   └── resources/
    │   └── test/
    ├── build.gradle
    ├── gradlew
    ├── gradlew.bat
    ├── settings.gradle
    └── gradle/
```

## 📋 Requisitos

Antes de iniciar o projeto, verifique se você possui:

- Java 21
- Git
- Gradle ou o wrapper do Gradle incluído no projeto

## ▶️ Como executar

Clone o repositório:

```bash
git clone https://github.com/Lucas-Lor3nzoni/Author-Management.git
```

Acesse a pasta da API:

```bash
cd Author-Management/api-server
```

Execute a aplicação:

### Linux/macOS

```bash
./gradlew bootRun
```

### Windows

```bash
gradlew.bat bootRun
```

A aplicação estará disponível em:

```text
http://localhost:8080
```

## 📚 Documentação da API

A documentação interativa pode ser acessada em:

```text
http://localhost:8080/swagger-ui/index.html
```

A especificação OpenAPI está disponível em:

```text
http://localhost:8080/v3/api-docs
```

## 🔐 Autenticação

A API utiliza autenticação JWT com Spring Security.

### Login

```http
POST /api/v1/auth/sign-in
Content-Type: application/json
```

```json
{
  "email": "admin@example.com",
  "password": "password"
}
```

Após o login, o sistema retorna um token de acesso.

Para acessar endpoints protegidos, envie o cabeçalho:

```http
Authorization: Bearer <access-token>
```

## 🧩 Endpoints principais

### Autenticação

```http
POST /api/v1/auth/sign-in
```

### Autores

```http
POST   /api/v1/authors
GET    /api/v1/authors/{id}
GET    /api/v1/authors
PUT    /api/v1/authors/{id}
DELETE /api/v1/authors/{id}
```

### Livros

```http
POST   /api/v1/works
GET    /api/v1/works/{id}
GET    /api/v1/works
PUT    /api/v1/works/{id}
DELETE /api/v1/works/{id}
```

> A documentação completa dos endpoints pode ser consultada no Swagger UI.

## 🗄️ Banco de dados

O projeto utiliza o banco H2 para desenvolvimento.

Console do H2:

```text
http://localhost:8080/h2-console
```

## 🧪 Executando testes

```bash
./gradlew test
```

Ou no Windows:

```bash
gradlew.bat test
```

## 📦 Build da aplicação

Para gerar o artefato da aplicação:

```bash
./gradlew build
```

O JAR gerado ficará em:

```text
api-server/build/libs/
```

## 🛡️ Tratamento de erros

A API utiliza tratamento global de exceções para padronizar as respostas.

Alguns status HTTP utilizados:

- 200 OK
- 201 Created
- 204 No Content
- 400 Bad Request
- 401 Unauthorized
- 404 Not Found
- 409 Conflict
- 500 Internal Server Error

## 🔎 Paginação

Endpoints de listagem suportam paginação com Spring Data:

```http
GET /api/v1/authors?page=0&size=10
```

Também é possível filtrar por nome:

```http
GET /api/v1/authors?name=Machado&page=0&size=10
```

## 🎯 Objetivo do projeto

Este projeto foi desenvolvido para praticar e demonstrar:

- Desenvolvimento de API REST
- Java e Spring Boot
- Arquitetura em camadas
- Injeção de dependências
- Spring Data JPA
- DTOs
- MapStruct
- Bean Validation
- Tratamento de exceções
- Spring Security
- Autenticação JWT
- Paginação
- Validação de regras de negócio
- Documentação OpenAPI/Swagger

## 👨‍💻 Autor

Lucas Lorenzoni

GitHub:
https://github.com/Lucas-Lor3nzoni

## ⚠️ Observação

Este projeto foi desenvolvido para fins de estudo e aprendizado, não sendo recomendado para uso em produção.
