# Sistema de Gestão de Ninjas e Missões

Aplicação web desenvolvida com Spring Boot para o gerenciamento de cadastros de ninjas e atribuição de missões. O sistema disponibiliza uma API RESTful completa e documentada, com persistência relacional robusta, suporte a migrações de banco de dados e testes de integração automatizados.

---

## Status do Projeto

O projeto encontra-se em **desenvolvimento ativo**. A API RESTful, a camada de testes de integração (Ninjas e Missões), o versionamento da base de dados via Flyway e a documentação OpenAPI/Swagger foram concluídos com sucesso.

---

## Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3.4+ / Spring Boot 4
* **Módulos Spring:**
  * Spring Web (API REST e MVC)
  * Spring Data JPA (Persistência com Hibernate)
  * Spring Boot Test (`MockMvc`, `@SpringBootTest`)
  * Spring Boot DevTools
* **Bancos de Dados & Migração:**
  * **PostgreSQL:** Banco de dados relacional principal em produção/desenvolvimento.
  * **Flyway Migration:** Gerenciamento e versionamento de scripts DDL/DML SQL.
  * **H2 Database:** Banco relacional em memória isolado para o ambiente de testes (`@ActiveProfiles("test")`).
* **Testes & Qualidade:**
  * **Testes de Integração:** JUnit 5, Spring MockMvc, Jackson (`ObjectMapper`).
  * **Testes Unitários:** JUnit 5, Mockito.
* **Documentação da API:** Springdoc OpenAPI / Swagger UI
* **Interface Web (SSR):** Thymeleaf, HTML5, CSS3
* **Gerenciador de Dependências:** Maven
* **Controle de Versão:** Git e GitHub

---

## Arquitetura e Boas Práticas

O projeto segue os padrões de mercado para garantir manutenibilidade, testabilidade e isolamento de escopos:

* **Arquitetura em Camadas:** Divisão entre Controllers, Services, Repositories e DTOs.
* **Padrão DTO (Data Transfer Object):** Isolamento entre as entidades de domínio JPA e a camada de transferência de dados.
* **Injeção de Dependências por Construtor:** Garantia de imutabilidade dos serviços e alta facilidade para escrita de testes.
* **Estratégia de Testes Automatizados:**
  * **Testes de Integração:** Cobertura de 100% das rotas de CRUD (`GET`, `POST`, `PUT`, `DELETE`) para Ninjas e Missões, simulando requisições HTTP reais via `MockMvc` e validando o estado do banco H2 em memória.
  * **Testes Unitários:** Testes isolados com Mockito para validação rigorosa das regras de negócio na camada Service.
* **Versionamento de Banco com Flyway:** Scripts SQL organizados e reproduzíveis para criação e evolução de tabelas e relacionamentos.
* **Padronização de Respostas HTTP:** Manipulação de DTOs e códigos de status HTTP (`200 OK`, `201 Created`, `404 Not Found`).

---

## Estrutura do Ambiente de Testes

Os testes da aplicação funcionam de forma independente do banco de dados principal (PostgreSQL):

* **Configuração em `src/test/resources/application-test.properties`:**
  * H2 Database configurado em modo de compatibilidade PostgreSQL.
  * Migrações do Flyway desativadas especificamente no ambiente de teste para permitir recriação limpa da base via JPA (`create-drop`).
  * Isolamento completo por teste utilizando anotações de limpeza `@BeforeEach`.

---

## Documentação da API (Swagger)

A API possui suas rotas documentadas com anotações OpenAPI (`@Operation`, `@ApiResponses`, `@Parameter`).

Com a aplicação em execução, a interface interativa do Swagger pode ser acessada em:
`http://localhost:8080/swagger-ui.html` ou `http://localhost:8080/swagger-ui/index.html`

---

## Como Executar o Projeto

### Pré-requisitos
* **Java 21** ou superior instalado.
* **Maven 3.8+** instalado.
* Instância do **PostgreSQL** ativa e configurada no `application.properties`.

### Passos
1. Clone o repositório:
   ```bash
   git clone [https://github.com/gsguga/CadastroDeNinjas.git](https://github.com/gsguga/CadastroDeNinjas.git)
