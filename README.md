# HelpSync - Plataforma de Gestão de Doações

**HelpSync** é uma API REST desenvolvida para conectar Fundos Municipais, Instituições Filantrópicas e Doadores, promovendo transparência e engajamento social através de um sistema centralizado de doações e acompanhamento de campanhas.

Este projeto foi desenvolvido como parte da Atividade Multidisciplinar do curso de **Tecnologia em Análise e Desenvolvimento de Sistemas**.

---

## 🚀 Tecnologias Utilizadas

O projeto foi construído utilizando as tecnologias mais modernas do ecossistema Java:

* **Java 21** - Linguagem base.
* **Spring Boot 3** - Framework principal.
* **Spring Security + JWT** - Autenticação e Autorização via Tokens (Stateless).
* **Spring Data JPA** - Persistência de dados.
* **PostgreSQL (NeonDB)** - Banco de Dados em Nuvem.
* **Swagger / OpenAPI** - Documentação viva da API.
* **Lombok** - Redução de código boilerplate.
* **Maven** - Gerenciamento de dependências.

---

## ⚙️ Funcionalidades Principais

### 🔒 Segurança & Acesso
* **Login Seguro:** Autenticação via e-mail e senha retornando Token JWT (Bearer).
* **Proteção de Rotas:** Endpoints críticos protegidos (apenas usuários logados).
* **Cadastro Aberto:** Permite que doadores se cadastrem livremente.

### 🏢 Gestão Institucional
* **Fundos Municipais:** Cadastro e administração de fundos ligados a administradores.
* **Instituições:** Registro de ONGs e instituições beneficentes vinculadas aos fundos.
* **Campanhas:** Criação de campanhas de arrecadação com metas financeiras, datas de vigência (Início/Fim) e status automático.

### 💰 Doações & Financeiro
* **Processamento de Doações:** Registro de doações com atualização automática do valor arrecadado na campanha.
* **Validações de Negócio:** Bloqueio de doações para campanhas expiradas ou inativas.
* **Estatísticas (BI):** Geração de relatórios em tempo real com total arrecadado, número de doações e campanhas por instituição.

---

## 🛠️ Arquitetura e Modelagem

O sistema segue a arquitetura em camadas (Controller, Service, Repository, Entity) e utiliza DTOs (Records) para transferência de dados.

### Diagrama de Classes (Resumo)
* **Administrador** (1) <---> (*) **FundoMunicipal**
* **FundoMunicipal** (1) <---> (*) **Instituicao**
* **Instituicao** (1) <---> (*) **Campanha**
* **Campanha** (1) <---> (*) **Doacao**
* **Doador** (1) <---> (*) **Doacao**

---

## 🏃‍♂️ Como Rodar o Projeto

### Pré-requisitos
* Java JDK 21 instalado.
* Maven instalado (ou usar o wrapper `mvnw` incluso).
* Acesso à internet (para conectar ao banco NeonDB na nuvem).

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/Jpsantista365/helpsync.git](https://github.com/Jpsantista365/helpsync.git)
    cd helpsync
    ```

2.  **Configure o Banco de Dados:**
    O projeto já está configurado para conectar ao NeonDB via `application.properties`. Certifique-se de que as credenciais estão corretas.

3.  **Execute a aplicação:**
    ```bash
    ./mvnw spring-boot:run
    ```
    *No Windows:*
    ```cmd
    mvnw.cmd spring-boot:run
    ```

4.  **Acesse a Documentação (Swagger):**
    Após iniciar, abra o navegador em:
    👉 **http://localhost:8080/swagger-ui.html**

---

## 🧪 Testando a API (Guia Rápido)

1.  **Criar Conta (Doador):** `POST /api/doadores` (Público).
2.  **Fazer Login:** `POST /api/auth/login` (Receba o Token JWT).
3.  **Autorizar no Swagger:** Clique no cadeado 🔒 e cole o Token (`Bearer seu_token`).
4.  **Usar o Sistema:** Agora você pode criar campanhas, fazer doações e gerar estatísticas.

---

## 👥 Autores

* **João Paulo Lopes Pinho** - ([GitHub](https://github.com/Jpsantista365))
* **Tiago Yukio Simões Kuramoto**
* **Nickollas Matheus Amorim De Souza**

---

<p align="center">
  Desenvolvido com 💙 para o IFMS - Campus Naviraí
</p>