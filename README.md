# Cadastro de Pessoas integrado ao VIACEP

## :page_facing_up: Sobre o projeto          
Aplicação desenvolvida para permitir um cadastro de pessoas com as operações de consulta, criação, edição e deleção de registros. 
Possui validação de CPF para pessoas físicas.
Ao cadastrar o endereço da pessoa, o CEP busca automaticamente os dados de localização, caso estiverem corretos.
Possui relatório em CSV das pessoas cadastradas.

## :paperclips: DER
Diagrama de Entidade e Relacionamento

![image](https://github.com/user-attachments/assets/04b9292b-660f-482b-a464-90a91986f892)

## :page_facing_up: Informações técnics <img align="center" alt="karen-java" height="35" width="45" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg"><img align="center" alt="karen-spring" height="35" width="45" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg">             <img align="center" alt="karen-spring" height="35" width="45" img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/angularjs/angularjs-original.svg" />
Este projeto foi desenvolvido utilizando Java com Spring Boot para o backend e Angular para o frontend. A aplicação é containerizada usando Docker e orquestrada com Docker Compose.

## :hammer: Stacks
* Angular
* Bootstrap
* Java JDK 17
* Spring
* H2 in memory (para testes)
* PostgreSQL (PROD)
* Swagger Open API Documentation
* Postman
* Flyway
* Docker 
* Docker Compose
* Docker Hub
* GitHub Projects

## :gear: Executando o backend

#### :framed_picture: Imagens Docker
- **Frontend**: [alvescamila87/frontend:1.0.0](https://hub.docker.com/r/alvescamila87/frontend)
- **Backend**: [alvescamila87/backend:1.0.0](https://hub.docker.com/r/alvescamila87/backend)

#### :pushpin: Requisitos
- Docker
- Docker Compose

#### :gear: Configuração do Projeto

1. **Clone o repositório**

   ```bash
   git clone https://github.com/alvescamila87/api-cadastro-pessoa.git
   cd api-cadastro-pessoa

2. **Executar via Docker compose**

   ```bash
   docker-compose up

#### Acesso à aplicação  
- **Frontend**: Abra seu navegador e acesse [http://localhost:3000](http://localhost:3000).
- **Backend**: O backend estará disponível em [http://localhost:9000](http://localhost:9000).
- **Documentação da API (Swagger)**: Acesse [http://localhost:9000/swagger-ui.html](http://localhost:9000/swagger-ui.html) para ver a documentação da API.

## :construction: Para testes de Backend

#### Método POST (Salvar)
- **URL**: [http://localhost:9000/pessoas](http://localhost:9000/pessoas)
- **Descrição**: Cria uma nova pessoa. Caso o `enderecoId` não exista no banco de dados, a aplicação busca o endereço no ViaCEP e o salva.
- **Estrutura JSON do Body**:
  ```json
  {
      "nomeCompleto": "",
      "cpf": "",
      "telefone": "",
      "endereco": {
          "cep": "",
          "logradouro": "",
          "numero": "",
          "complemento": "",
          "bairro": "",
          "localidade": "",
          "uf": "",
          "ibge": "",
          "gia": "",
          "ddd": "",
          "siafi": ""
      }
  }

#### Método GET (Busca por ID)
- **URL**: [http://localhost:9000/pessoas/{id}](http://localhost:9000/pessoas{id})
- **Descrição**: Retorna a pesssoa do ID fornecido.
- **Estrutura JSON retornada**:
  ```json
  {
      "id": 1,
      "nomeCompleto": "Zebedeu Abraão",
      "cpf": "02100000000",
      "telefone": "47-9999-5555",
      "endereco": {
          "id": 1,
          "cep": "00000-000",
          "logradouro": "Rua Israel",
          "numero": "7",
          "complemento": "Próximo à igreja",
          "bairro": "Europa",
          "localidade": "Blumenau",
          "uf": "SC",
          "ibge": "",
          "gia": "",
          "ddd": "",
          "siafi": ""
      }
  }
#### Método GET (Listar)
- **URL**: [http://localhost:9000/pessoas/](http://localhost:9000/pessoas)
- **Descrição**: Retorna a lista de todas as pessoas cadastradas.
- **Estrutura JSON retornada**:
  ```json
  [
       {
           "id": 1,
           "nomeCompleto": "Zebedeu Abraão",
           "cpf": "02100000000",
           "telefone": "47-9999-5555",
           "endereco": {
               "id": 1,
               "cep": "00000-000",
               "logradouro": "Rua Israel",
               "numero": "7",
               "complemento": "Próximo à igreja",
               "bairro": "Europa",
               "localidade": "Blumenau",
               "uf": "SC",
               "ibge": "",
               "gia": "",
               "ddd": "",
               "siafi": ""
           }
       }
   ]

#### Método DELETE (Excluir)
- **URL**: [http://localhost:9000/pessoas/{id}](http://localhost:9000/pessoas{id})
- **Descrição**: Deleta uma pessoa existente pelo ID informado.
  
#### Método PUT (Atualizar)
- **URL**: [http://localhost:9000/pessoas/{id}](http://localhost:9000/pessoas{id})
- **Descrição**: Atualiza uma pessoa existente. Caso o enderecoId não exista no banco de dados, a aplicação busca o endereço no ViaCEP e o salva.
- **Estrutura JSON do Body**:
  ```json
  {
      "id": 1,
      "nomeCompleto": "",
      "cpf": "",
      "telefone": "",
      "endereco": {
          "cep": "",
          "logradouro": "",
          "numero": "",
          "complemento": "",
          "bairro": "",
          "localidade": "",
          "uf": "",
          "ibge": "",
          "gia": "",
          "ddd": "",
          "siafi": ""
      }
  }

## :books: Documentação

#### JavaDoc
![image](https://github.com/user-attachments/assets/48981ff8-d8bb-47d5-8b9d-e424638ab199)

#### Swagger
![image](https://github.com/user-attachments/assets/f65f5fab-aec2-46b6-a1d6-83d14dfa5f97)

#### Docker compose 
![image](https://github.com/user-attachments/assets/e0d3b477-0a9b-492d-b88f-c8fecedb1fc6)
![image](https://github.com/user-attachments/assets/fb1a7ba2-5f0a-408b-89fd-7b9a63e3a55b)

#### DockerHub
![image](https://github.com/user-attachments/assets/e8ffc4ed-ee4e-4ba5-8b5e-64fe7505f038)

#### Kanban board
![image](https://github.com/user-attachments/assets/05641bfc-b84b-48b7-a58b-8f765534079a)

## Imagens da aplicação:
#### Lista 
![image](https://github.com/user-attachments/assets/bcddc1e6-ca19-4c21-9ec4-43eafa978d30)

#### Detalhes e edição
![image](https://github.com/user-attachments/assets/f9390618-a25a-4b2b-9972-4432fd350e99)
![image](https://github.com/user-attachments/assets/e817316d-6d0c-4db0-b3b2-054c287ff112)

#### Cadastrar pessoa
![image](https://github.com/user-attachments/assets/b09b106e-7649-47d5-87ed-b0d088f52062)

#### Relatorio CSV
![image](https://github.com/user-attachments/assets/bd9e5d17-d6f3-48ed-87e8-f2a845aff9d6)

## :raising_hand_woman: Autor
* Camila Alves

