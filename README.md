# Desafio Técnico iPaaS - Backend Java

Este projeto é uma API RESTful para gerenciamento de tarefas internas, desenvolvida em Java com Spring Boot. A API permite criar e gerenciar usuários, tarefas e subtarefas, com validações e lógica de negócio bem definidas.

---

## 🚀 Como Rodar o Projeto

Você pode executar o projeto usando Maven diretamente ou via Docker.

### Com Maven (sem Docker)

1.  **Pré-requisitos**: Certifique-se de ter o Java 17 e o Maven instalados.
2.  **Compilar e Empacotar**: Navegue até o diretório raiz do projeto e execute o comando:
    ```bash
    ./mvnw clean package
    ```
3.  **Executar a Aplicação**: Inicie o servidor com o comando:
    ```bash
    java -jar target/DesafioTecicoIpass-0.0.1-SNAPSHOT.jar
    ```
    A aplicação será iniciada na porta 8080.

### Com Docker

1.  **Pré-requisitos**: Tenha o Docker e o Docker Compose instalados.
2.  **Build e Execução**: Navegue até o diretório raiz do projeto e execute o seguinte comando para construir a imagem e iniciar o container:
    ```bash
    docker-compose up --build
    ```
    A aplicação estará acessível na porta 8080 do seu host.

---

## 📋 Endpoints da API

A documentação completa dos endpoints pode ser acessada via Swagger UI, que estará disponível em `http://localhost:8080/swagger-ui.html` após a execução da aplicação.

### Usuários
* `POST /usuarios` → Cria um novo usuário.
* `GET /usuarios/{id}` → Busca um usuário específico por ID.

### Tarefas
* `POST /tarefas` → Cria uma nova tarefa.
* `GET /tarefas?status={status}` → Lista tarefas, com opção de filtrar por status (`PENDENTE`, `EM_ANDAMENTO`, `CONCLUIDA`).
* `PATCH /tarefas/{id}/status` → Atualiza o status de uma tarefa.

### Subtarefas
* `POST /tarefas/{tarefaId}/subtarefas` → Cria uma nova subtarefa para uma tarefa específica.
* `GET /tarefas/{tarefaId}/subtarefas` → Lista todas as subtarefas de uma tarefa.
* `PATCH /subtarefas/{id}/status` → Atualiza o status de uma subtarefa.

---

## 🛠️ Tecnologias Utilizadas

* **Java**: Versão 17.
* **Spring Boot**: Framework principal para construção da API.
* **Maven**: Ferramenta de build e gerenciamento de dependências.
* **Spring Data JPA & Hibernate**: Para persistência e mapeamento de objetos.
* **H2 Database**: Banco de dados em memória para desenvolvimento e testes.
* **Springdoc OpenAPI & Swagger UI**: Para documentação automática da API.
* **Jakarta Bean Validation**: Para validações de entrada de dados.
* **JUnit & Mockito**: Para a criação dos testes unitários.
* **Docker & Docker Compose**: Para empacotamento e orquestração da aplicação em containers.
