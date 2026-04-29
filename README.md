# address-api

> Uma API para consulta e rastreio de endereços, otimizada para desenvolvimento local com ambiente conteinerizado.

---

## 🏗️ Arquitetura do Sistema

O fluxo de dados foi desenhado para isolar as dependências externas em containers, garantindo que o seu ambiente de desenvolvimento esteja sempre limpo.

```
       [ Cliente (Postman/Browser) ]
                    |
          (HTTP Request: 8080)
                    |
     +------------------------------+
     |   address-api (Spring)     |
     +------------------------------+
         /                  \
   (HTTP: 3000)         (JDBC: 5432)
       /                      \
+--------------+        +--------------+
|   Mockoon    |        |  PostgreSQL  |
+--------------+        +--------------+
(Container)             (Container)
```

### Fluxo de Dados:
1. **Cliente:** Realiza uma requisição HTTP via porta `8080`.
2. **Spring Boot App:** Processa a lógica de negócio.
3. **Mockoon (API Externa):** Atua como o provedor de dados de CEP (porta `3000`), mimetizando uma integração real.
4. **PostgreSQL:** Persiste logs detalhados de cada consulta.

---

## 🛠️ Tecnologias e Versões

- **Java 17:**
- **Spring Boot:** Core da aplicação para gerenciamento de dependências e API REST.
- **PostgreSQL:** Base de dados relacional para persistência de logs.
- **Mockoon CLI:** Para simular a API de CEP externa.
- **Docker:** Orquestração de todo o ambiente local.

---

## 🚀 Como Executar

### Pré-requisitos
- Docker instalado.
- Maven (opcional, caso prefira rodar fora do container).

---

### Passo a Passo
1. **Subir a Infraestrutura:**
   Na raiz do projeto, execute:
   ```bash
   docker-compose up -d
   ```
2. **Rodar a Aplicação**

3. **Testar a Integração:**
   acesse via navegador ou Postman:
GET http://localhost:8080/cep/01001000

---

## Estrutura dos logs
Cada consulta gera um registro no banco de dados com:

- id: Identificador único.

- consult_date: Horário exato da requisição.

- localidade: O dado (local) obtido pelo cliente.

- logradouro: Endereço completo retornado pela API externa (Mockoon).

---

## Princípios SOLID Aplicados
O código foi estruturado com foco em:

- SRP (Single Responsibility): Divisão clara entre Controllers, Services e Clients de integração.

- DIP (Dependency Inversion): O serviço de endereço consome uma interface AddressProvider, permitindo trocar o Mockoon por uma API real (como ViaCEP) sem alterar a lógica interna.

- OCP (Open/Closed): Facilidade em estender as funcionalidades de busca e log sem modificar o comportamento base já testado.
