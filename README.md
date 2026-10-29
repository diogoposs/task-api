# Task API

API REST de gerenciamento de tarefas, desenvolvida como aplicação de apoio a estudos de testes de integração e automação de API.

**Tecnologias:** Java 17, Spring Boot 3.5.10, Spring Web, Bean Validation, Spring Data JPA, MySQL e Maven.

A suíte de testes de API fica no repositório [Task API Tests](https://github.com/diogoposs/task-api-tests).

## Funcionalidades

- Criação, listagem, consulta, atualização e exclusão de tarefas.
- Validação do título: obrigatório, não composto apenas por espaços e com pelo menos três caracteres.
- Persistência em MySQL por meio do Spring Data JPA.

## Executar localmente

Requisitos: JDK 17, Maven e uma instância MySQL disponível. O repositório não inclui Maven Wrapper.

1. Baixe o projeto:

   ```bash
   git clone https://github.com/diogoposs/task-api.git
   cd task-api
   ```

2. Crie um banco vazio para desenvolvimento:

   ```sql
   CREATE DATABASE taskdb CHARACTER SET utf8mb4;
   ```

3. Configure a conexão pelas variáveis reconhecidas pelo Spring Boot:

   | Variável                     | Exemplo                              |
   | ---------------------------- | ------------------------------------ |
   | `SPRING_DATASOURCE_URL`      | `jdbc:mysql://localhost:3306/taskdb` |
   | `SPRING_DATASOURCE_USERNAME` | Usuário local do MySQL               |
   | `SPRING_DATASOURCE_PASSWORD` | Senha local do MySQL                 |

   Essas variáveis substituem os valores de desenvolvimento de [application.properties](src/main/resources/application.properties). O arquivo atual contém valores locais fixos; utilize credenciais próprias.

4. Inicie a aplicação:

   ```bash
   mvn spring-boot:run
   ```

A API estará disponível em `http://localhost:8080/tasks`. A configuração atual usa `ddl-auto=update` para criar ou atualizar a tabela no banco de desenvolvimento.

## Endpoints

| Método | Caminho       | Operação             | Resposta de sucesso |
| ------ | ------------- | -------------------- | ------------------- |
| GET    | `/tasks`      | Listar tarefas       | 200                 |
| GET    | `/tasks/{id}` | Consultar uma tarefa | 200                 |
| POST   | `/tasks`      | Criar tarefa         | 201                 |
| PUT    | `/tasks/{id}` | Atualizar título     | 200                 |
| DELETE | `/tasks/{id}` | Excluir tarefa       | 204                 |

Exemplo de criação:

```bash
curl -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Estudar testes de API"}'
```

Exemplo de corpo retornado, com ID ilustrativo:

```json
{
  "id": 1,
  "title": "Estudar testes de API"
}
```

## Organização

| Pacote       | Responsabilidade                      |
| ------------ | ------------------------------------- |
| `controller` | Rotas HTTP e validação de entrada     |
| `service`    | Operações de gerenciamento de tarefas |
| `repository` | Acesso ao banco por JPA               |
| `model`      | Entidade e restrições do título       |

## Testes

Com o MySQL e a API em execução, siga as instruções do [projeto de testes](https://github.com/diogoposs/task-api-tests). A suíte inclui verificações HTTP e consulta direta à tabela `tasks`.

Este repositório não contém testes próprios implementados em `src/test`; a dependência de testes do Spring Boot está declarada no Maven.

## Escopo atual

Projeto educacional com uma entidade e operações CRUD. Não possui autenticação, paginação ou tratamento específico para recurso inexistente: a consulta por ID lança uma exceção genérica quando a tarefa não é encontrada. Esses pontos são oportunidades de evolução do contrato da API.
