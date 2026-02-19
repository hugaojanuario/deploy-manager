Esse é um projeto fantástico! A estrutura está muito bem pensada, resolvendo um problema real e aplicando excelentes práticas de mercado, como segurança com AES/BCrypt e o uso profissional de Flyway e Docker que você destacou.

Aqui está o código Markdown consolidado, formatado e corrigido (ajustei alguns blocos de código que estavam abertos na sua versão original).

Você pode copiar o conteúdo abaixo e colar diretamente no seu arquivo README.md:

Markdown
# 🚀 Deploy Manager API

API REST desenvolvida para gerenciar implantações de sistemas, controle de versões e conexões remotas de clientes (serventias).

Este projeto nasceu de uma necessidade real do setor de implantação, com o objetivo de substituir controles manuais (planilhas) por uma plataforma estruturada, segura e centralizada.

---

## 🎯 Objetivo

Gerenciar de forma segura e organizada:

- 📌 **Serventias** (clientes/cartórios)
- 📦 **Versões do sistema**
- 📝 **ChangeLog** de cada versão
- 🔐 **Conexões remotas** (TeamViewer, AnyDesk, AnyViewer)
- 👤 **Usuários** com autenticação JWT e controle de acesso

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas e organização orientada a domínio:

`controller` → `service` → `repository` → `database`

**Organização dos pacotes:**
```text
domain
├── serventia
├── version
├── conexao
├── usuario
security
config
dto
exception
```

🧱 Modelo de Dados
📌 Serventia
id (UUID)

nome

cidade

estado

contato

versão atual

data de criação

📦 Version
id (UUID)

número da versão

data de lançamento

changelog (texto embutido)

🔐 Conexao
id (UUID)

serventia

tipo (TEAMVIEWER | ANYDESK | ANYVIEWER)

identificador remoto

senha remota (criptografada com AES)

usuário Windows

senha Windows (criptografada com AES)

usuário banco

senha banco (criptografada com AES)

👤 Usuario
id (UUID)

nome

email

senha (BCrypt)

role (ADMIN | IMPLANTACAO | VISUALIZADOR)

🔐 Segurança
Autenticação stateless com JWT

Spring Security para controle de acesso baseado em roles

Senhas de usuários protegidas com BCrypt

Credenciais e senhas de conexões criptografadas de forma bidirecional com AES

Endpoints protegidos por autorização rigorosa

🛠️ Tecnologias Utilizadas
Java 21

Spring Boot 3

Spring Security & JWT

Lombok

Bean Validation

JPA / Hibernate

PostgreSQL

Flyway

Docker & Docker Compose

Maven

🗄️ Banco de Dados
PostgreSQL como banco principal

Uso profissional de Flyway para versionamento de schema

Migrations organizadas rigorosamente por versão

spring.jpa.hibernate.ddl-auto=validate configurado para garantir integridade e segurança em produção

🐳 Executando com Docker
O projeto faz uso profissional de Docker para padronizar o ambiente de desenvolvimento e deploy.

1️⃣ Subir containers
Bash
docker-compose up --build
Isso irá provisionar e executar:

O banco de dados PostgreSQL

A aplicação API

A API ficará disponível em: http://localhost:8080

🔑 Autenticação e Endpoints
Login
HTTP
POST /auth/login
Retorno de sucesso:

JSON
{
  "token": "jwt_token_gerado_aqui"
}
Nota: O token retornado deve ser enviado no header de todas as requisições subsequentes:
Authorization: Bearer {token}

📡 Principais Endpoints
Serventias

POST /serventias

GET /serventias

GET /serventias/{id}

PUT /serventias/{id}

Versions

POST /versions

GET /versions

Conexões

POST /serventias/{id}/conexoes

GET /serventias/{id}/conexoes

📌 Boas Práticas Aplicadas
Uso profissional de Flyway e Docker para orquestração e versionamento de banco de dados

UUID como chave primária para maior segurança e escalabilidade distribuída

Separação clara e rigorosa de camadas

Uso de DTOs para controle limpo de entrada e saída de dados

Tratamento global de exceções (Global Exception Handler)

Uso de Enums para padronização de tipos de conexão e roles de usuários

Segurança aplicada diretamente nos dados sensíveis do banco (Criptografia AES)

Projeto integralmente orientado a um domínio real

🚀 Diferencial do Projeto
Este projeto foi idealizado para resolver um problema operacional real no setor de implantação, substituindo controles manuais por uma API estruturada, segura e escalável.

Além de servir como ferramenta prática, o projeto demonstra:

Modelagem de domínio coerente e realista

Arquitetura limpa e sustentável

Segurança fortemente aplicada (tanto no acesso quanto nos dados)

Controle e armazenamento seguro de credenciais de terceiros sensíveis


---

Gostaria que eu te ajudasse a criar uma documentação no formato **Swagger / OpenAPI** para você adicionar a esta API e gerar uma interface visual para os testes dos endpoints?
