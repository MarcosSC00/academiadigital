## Academia digital💪

API Restful que implementa o contexto de uma academia e implementa seus principais serviços oferecidos a um aluno de forma digital.

## Principais Tecnologias 🛠️

- **Java 17**
- **PostgreSQL**
- **Spring Boot**
- **Spring DataJPA**
- **OpenAI(Swagger)**
- **Railway**

## Diagrama de classes

```mermaid

  classDiagram
    class Aluno {
        +String nome
        +Date dataDeNascimento
        +String telefone
        +String endereco
        +List~AvaliacaoFisica~ avaliacoesFisicas
        +List~Aula~ aulas
    }
    class AvaliacaoFisica {
        +Date dataDaAvaliacao
        +Double peso
        +Double altura
        +String resultado
    }
    class Aula {
        +String nome
        +Date horario
        +String instrutor
        +Double duracao
    }
    class Instrutor {
        +String nome
        +String telefone
        +String email
        +List~String~ especialidades
        +List~Aula~ aulas
    }
    Aluno *-- AvaliacaoFisica : "avaliacoesFisicas"
    Aluno *-- Aula : "aulas"
    Aula --> Instrutor : "instrutor"

```

## Diagrama ORM

```mermaid
erDiagram
	ALUNO ||--o{ AVALIACAOFISICA : "avaliacao"
	ALUNO ||--o{ AULA : "aulas"
	AULA }|--|| INSTRUTOR : "aulas-instrutor"
```

## Layout 🚧

> Em construção. Apenas a versão mobile disponível.

Acesse as UIs do projeto [aqui](https://www.figma.com/design/d7li50nQfNixh8ZALMfPxF/UI---AcademiaDigital?node-id=0-1&t=XlVe1M4QsQKwopeD-1).

## Documentação da API (Swagger)

Acesse a documentação [aqui](https://academiadigital-production.up.railway.app/swagger-ui/index.html).

> [!IMPORTANT]
> O deploy dessa aplicação foi implementado com um banco de dados sem nenhum registro, portando um serviço pode depender do pré-cadastro de outro.

## Atividades futuras ⏳

- [ ] validar campos do usuario (email, telefone, etc.)
- [ ] implementar as UIs utilizando o React JS.
- [ ] integrar back e front-end.
- [ ] Deploy da aplicação completa.
