# Contact Manager API

## Descrição

A **Contact Management API** é uma aplicação para gerenciamento de contatos e suas informações pertinentes. A API
oferece endpoints para criar, listar, atualizar e desativar contatos, permitindo que os usuários acessem e manipulem as
informações.

Esta API é construída com o framework **Spring Boot** e utiliza o **Spring Data JPA** para interagir com um banco de
dados PostgreSQL. A integração de migrações de banco de dados é feita com o **Flyway**.

## Tecnologias

- **Spring Boot** (Starter Web, Data JPA)
- **Flyway** (para migração de banco de dados)
- **PostgreSQL** (banco de dados)
- **Lombok** (para simplificação de código)
- **Springdoc OpenAPI** (para documentação automática da API)

## Pré-requisitos

Certifique-se de que você tenha os seguintes requisitos instalados em seu sistema:

- [Docker](https://www.docker.com/get-started) (para executar o banco de dados PostgreSQL)
- [Java 17](https://openjdk.org/projects/jdk/17/) ou superior
- [Maven](https://maven.apache.org/) (para build e gerenciamento de dependências)
- [Git](https://git-scm.com/) (para clonar o repositório)

## Configuração

### 1. Clone o Repositório

Clone o repositório para o seu ambiente local:

```bash
git clone https://github.com/RodolfoAndrade92/jcastro-desafio.git
cd contact-management
```

### 2. Executando o Banco de Dados com Docker

Para facilitar o uso do banco de dados PostgreSQL, o projeto vem com um arquivo `docker-compose.yml.` Ele cria um
container PostgreSQL para ser usado pela API.

No diretório raiz do projeto, execute o comando a seguir para iniciar o banco de dados:

```bash
docker-compose up -d
```

Isso irá iniciar o PostgreSQL em um container Docker e mapear a porta 5432 para a sua máquina local.


### 3. Configuração do Banco de Dados
As configurações do banco de dados são feitas automaticamente através do Flyway. O Flyway irá aplicar as migrações do
banco de dados quando o projeto for executado pela primeira vez.

Certifique-se de que o banco de dados está sendo executado no container Docker `postgres_container` com o usuário
`POSTGRES_USER`, a senha `POSTGRES_PASSWORD` e o database `POSTGRES_DB` configurados no arquivo `docker-compose.yml`:

* **Banco de dados**: PostgreSQL
* **Porta**: 1433
* **POSTGRES_USER**: dev_user
* **POSTGRES_PASSWORD**: d3s4f10!
* **POSTGRES_DB**: contact_manager

### 4. Executando a Aplicação
Para rodar a aplicação localmente, execute o seguinte comando:

```bash
./mvnw spring-boot:run
```

Ou se preferir, use o comando Maven tradicional:

```bash
mvn spring-boot:run
```

A aplicação estará disponível no seguinte endereço: http://localhost:8080.


### 5. Acessando a Documentação da API
A documentação da API é gerada automaticamente pelo **Springdoc OpenAPI**. Você pode acessá-la em:

* **Swagger UI**: http://localhost:8080/swagger-ui.html
* **OpenAPI JSON**: http://localhost:8080/v3/api-docs


### 6. Testando a Aplicação
Você pode rodar os testes automatizados para garantir que tudo está funcionando corretamente:

```bash
./mvnw test
```

Ou usando o comando Maven:

```bash
mvn test
```

## Dependências

O arquivo `pom.xml` contém as dependências necessárias para o funcionamento do projeto:

    <dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.flywaydb</groupId>
			<artifactId>flyway-core</artifactId>
		</dependency>

		<dependency>
			<groupId>org.flywaydb</groupId>
			<artifactId>flyway-database-postgresql</artifactId>
		</dependency>

		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.8.4</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

## Docker Compose

Aqui está o conteúdo do arquivo `docker-compose.yml` utilizado para configurar o banco de dados PostgreSQL:

    services:  
      db:  
        image: postgres:latest  
        container_name: postgres_container  
        environment:  
          POSTGRES_USER: dev_user  
          POSTGRES_PASSWORD: d3s4f10!  
          POSTGRES_DB: contact_manager  
        ports:  
          - "5432:5432"  
      volumes:  
          - postgres_data:/var/lib/postgresql/data  
        networks:  
          - postgres_network  
      
    volumes:  
      postgres_data:  
        driver: local  
      
    networks:  
      postgres_network:  
        driver: bridge
