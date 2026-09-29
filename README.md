# Sistema de Gestão de Ninjas e Missões

Aplicação web desenvolvida com Spring Boot para o gerenciamento de cadastros de ninjas e atribuição de missões. O sistema disponibiliza uma API RESTful completa e documentada, além de uma interface visual administrativa integrada via Spring MVC e Thymeleaf.

---

## Status do Projeto

O projeto encontra-se em **desenvolvimento ativo**. A estrutura principal da API REST, persistência de dados, interface visual e documentação OpenAPI/Swagger já foram concluídas. Funcionalidades adicionais e melhorias contínuas estão sendo acompanhadas via *Issues* no repositório.

---

## Tecnologias Utilizadas

* **Linguagem:** Java (versão 17+)
* **Framework:** Spring Boot 3
* **Módulos Spring:**
  * Spring Web (API REST e MVC)
  * Spring Data JPA (Persistência)
  * Spring Boot DevTools
* **Interface Web (SSR):** Thymeleaf, HTML5, CSS3
* **Banco de Dados:** H2 Database (Banco em memória para ambiente de desenvolvimento)
* **Documentação da API:** Springdoc OpenAPI / Swagger UI
* **Gerenciador de Dependências:** Maven
* **Controle de Versão:** Git e GitHub

---

## Arquitetura e Boas Práticas

O projeto foi construído seguindo padrões de mercado para garantir alta manutenibilidade, testabilidade e separação clara de responsabilidades:

* **Arquitetura em Camadas:** Divisão rigorosa entre Controllers, Services, Repositories e DTOs.
* **Padrão DTO (Data Transfer Object):** Isolamento entre as entidades de domínio (JPA) e a camada de apresentação/transferência de dados.
* **Injeção de Dependências por Construtor:** Garantia de imutabilidade dos serviços e facilidade para a escrita de testes unitários.
* **Padronização de Respostas HTTP:** Uso do `ResponseEntity` nos controladores REST para tratamento de status (200, 201, 404).
* **Princípios SOLID e Código Limpo:** Baixo acoplamento e alta coesão nas regras de negócio.

---

## Documentação da API (Swagger)

A API possui todas as suas rotas devidamente anotadas com `@Operation`, `@ApiResponses` e `@Parameter`, facilitando o entendimento dos contratos por outros desenvolvedores.

Com a aplicação em execução, a interface interativa do Swagger pode ser acessada através do endereço:
