# 🚀 Deploy Manager API

API REST desenvolvida para gerenciar implantações de sistemas, controle de versões, conexões remotas e usuários em um ambiente centralizado.

Este projeto nasceu da necessidade real do setor de TI em cartórios e serventias, visando substituir controles manuais em planilhas por uma plataforma estruturada, segura e escalável para gerenciar diferentes ambientes de cliente.

---

## 🎯 Objetivo Principal

Centralizar o gerenciamento de:

- 👥 **Clientes** (Cartórios/Serventias) - com dados de acesso ao servidor e banco
- 📦 **Versões do Sistema** - com histórico e changelog detalhado
- 🔐 **Conexões Remotas** - TeamViewer, AnyDesk, AnyViewer
- 👤 **Usuários e Autenticação** - com sistema de roles e JWT
- 📊 **Rastreabilidade** - auditoria com timestamps de criação

---

## 🏗️ Arquitetura

O projeto segue uma **arquitetura em camadas bem definida** com separação clara de responsabilidades:

```
HTTP Request
    ↓
[Controller Layer] - Recebe requisições, valida entrada
    ↓
[Service Layer] - Implementa lógica de negócio
    ↓
[Repository Layer] - Acessa dados via JPA/Hibernate
    ↓
[Database Layer] - PostgreSQL com versionamento Flyway
```

**Organização dos pacotes por domínio:**
```
com.hugojanuario.deploy_manager/
├── domain/
│   ├── client/          # Entidade Cliente
│   │   └── dto/         # DTOs de request/response
│   ├── version/         # Entidade Versão
│   │   └── dto/
│   ├── connection/      # Entidade Conexão Remota
│   │   └── dto/
│   └── user/            # Entidade Usuário
│       └── dto/
├── controller/          # REST Controllers
├── service/             # Lógica de negócio
├── repository/          # Data Access (Spring Data JPA)
├── infra/
│   └── security/        # JWT, autenticação, filtros
└── DeployManagerApplication.java
```

## 📖 Documentação da API - Swagger/OpenAPI

Após iniciar a aplicação, acesse a documentação interativa em:

👉 **http://localhost:8080/swagger-ui/index.html**

Aqui você pode visualizar todos os endpoints, modelos de dados e testar requisições em tempo real.

---

## ▶️ Como Rodar o Projeto

### Opção 1: Rodando Localmente (sem Docker)

#### Pré-requisitos
- JDK 21 instalado
- Maven instalado (ou usar o wrapper `mvnw` do projeto)
- PostgreSQL rodando localmente

1. **Configurar o banco de dados**
   - Certifique-se que o PostgreSQL está rodando em:
     - `jdbc:postgresql://localhost:5432/postgres`
     - Usuário: `user`
     - Senha: `secret`
   - Esses valores podem ser ajustados em `src/main/resources/application.properties`.

2. **Rodar as migrations e subir a aplicação**
   No diretório raiz do projeto (`deploy-manager`), execute:

   ```bash
   ./mvnw spring-boot:run
   ```

   Ou, se tiver Maven instalado globalmente:

   ```bash
   mvn spring-boot:run
   ```

3. **Rodar pelo IntelliJ IDEA**
   - Importe o projeto como **Maven Project**
   - Aguarde o download das dependências
   - Abra a classe `DeployManagerApplication`
   - Clique em **Run** na função `main`

A API ficará disponível em: **http://localhost:8080**

---

## 🧱 Modelo de Dados

### 👥 Cliente (Serventia/Cartório)
```
clients
├── id (UUID) - Chave primária
├── name (VARCHAR) - Nome da serventia
├── city (VARCHAR) - Cidade
├── state (VARCHAR) - UF
├── contact (VARCHAR) - Telefone/contato
├── version_id (FK) - Versão atual instalada
├── user_machine_server (VARCHAR) - Usuário acesso servidor
├── password_machine_server (VARCHAR) - Senha servidor (criptografada)
├── user_db (VARCHAR) - Usuário banco de dados
├── password_db (VARCHAR) - Senha banco (criptografada)
├── active (BOOLEAN) - Status ativo/inativo
└── created_at (TIMESTAMP) - Data de criação (auditoria)
```

### 📦 Versão
```
versions
├── id (UUID) - Chave primária
├── number_version (VARCHAR) - Número da versão (ex: 1.0.0)
├── date_release (DATE) - Data de lançamento
├── changelog (TEXT) - Detalhes das mudanças
├── active (BOOLEAN) - Versão ativa/arquivada
└── created_at (TIMESTAMP) - Data de criação (auditoria)
```

### 🔐 Conexão Remota
```
connections
├── id (UUID) - Chave primária
├── client_id (FK) - Cliente associado
├── connection_type (ENUM) - Tipo: TEAMVIEWER | ANYDESK | ANYVIEWER
├── id_remote_connection (VARCHAR) - ID/Código da conexão
├── password_remote_connection (VARCHAR) - Senha (criptografada)
└── created_at (TIMESTAMP) - Data de criação (auditoria)
```

### 👤 Usuário
```
users
├── id (UUID) - Chave primária
├── email (VARCHAR) - Email único
├── password (VARCHAR) - Senha (criptografada com BCrypt)
├── role_type (ENUM) - Perfil: ADMIN | IMPLANTACAO | VISUALIZADOR
├── active (BOOLEAN) - Usuário ativo/inativo
└── created_at (TIMESTAMP) - Data de criação (auditoria)
```

### 📊 Relacionamentos
```
Client (Muitos) ──→ (Um) Version
Connection (Muitos) ──→ (Um) Client
```

## 🔐 Segurança

### Autenticação
- ✅ **JWT (JSON Web Token)** - Autenticação stateless
- ✅ **Token expira em 2 horas** - Renovação automática
- ✅ **BCrypt** - Senhas de usuários criptografadas
- ✅ **Spring Security** - Framework de segurança integrado

### Autorização
- ✅ **Controle de Acesso por Roles** - ADMIN | IMPLANTACAO | VISUALIZADOR
- ✅ **Proteção de Endpoints** - Validação de JWT em cada requisição
- ✅ **SecurityFilter** - Filtro customizado para validar tokens

### Proteção de Dados Sensíveis
- ✅ **Credenciais criptografadas** - Senhas de servidores e bancos
- ✅ **Senhas remotas criptografadas** - Credenciais de TeamViewer/AnyDesk
- ✅ **Nenhum dado sensível em logs** - Segurança de informações
- ✅ **UUID como PK** - Sem exposição de IDs sequenciais

### Fluxo de Autenticação
```
POST /login
├── UserCreateRequest (email + password)
├── AuthenticationManager valida credenciais
├── TokenService gera JWT
└── Response: { "token": "jwt_token_aqui" }

Requisições subsequentes:
├── Header: Authorization: Bearer {token}
├── SecurityFilter extrai e valida token
├── User autenticado = acesso concedido
└── Token expirado/inválido = acesso negado (401)
```

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 21** - Linguagem principal
- **Spring Boot 3.5.11** - Framework web
- **Spring Data JPA** - Persistência de dados
- **Spring Security** - Autenticação e autorização
- **Spring Validation** - Validação de requisições

### Segurança & JWT
- **Auth0 Java JWT 4.2.1** - Geração e validação de tokens JWT
- **BCrypt** - Hash seguro de senhas (via Spring Security)

### Banco de Dados
- **PostgreSQL** - Banco de dados principal
- **Flyway 11.7.2** - Versionamento de migrations
- **Hibernate** - ORM (Object-Relational Mapping)

### Documentação & Testes
- **SpringDoc OpenAPI 2.8.15** - Geração automática de Swagger/OpenAPI
- **Spring Security Test** - Testes de segurança
- **Spring Boot Test** - Testes unitários

### Ferramentas & Utilidades
- **Lombok** - Redução de boilerplate (getters, setters, constructors)
- **Spring Boot DevTools** - Reload automático em desenvolvimento
- **Maven** - Gerenciador de dependências e build
- **Docker & Docker Compose** - Containerização e orquestração

### IDE & Ambiente
- **IntelliJ IDEA 2025.3.2** - IDE de desenvolvimento
- **macOS** - Sistema operacional
- **Zsh** - Shell padrão

## 🗄️ Banco de Dados

### PostgreSQL
- Banco de dados relacional principal
- Suporte a UUID nativo
- Transações ACID

### Flyway - Versionamento de Schema
Todas as alterações no banco são rastreadas via migrations versionadas:

```
db/migration/
├── V1__create-table-versions.sql
├── V2__create-table-clients.sql
├── V3__create-table-connections.sql
├── V4__create-table-users.sql
└── V5__remove-column-username.sql
```

**Configuração de Segurança:**
```properties
spring.jpa.hibernate.ddl-auto=validate
```
- `validate` garante que o schema do banco corresponde às entidades JPA
- Nenhuma alteração automática é aplicada (segurança em produção)
- Todas as mudanças são feitas via Flyway migrations

### Conectando ao PostgreSQL
```bash
# Variaveis de ambiente
JDBC_DATABASE_URL: jdbc:postgresql://localhost:5432/postgres
DB_USER: user
DB_PASSWORD: secret
```

## 🐳 Executando com Docker

O projeto utiliza Docker Compose para provisionar o ambiente completo de desenvolvimento.

### Pré-requisitos
- Docker e Docker Compose instalados
- Git para clonar o repositório

### 1️⃣ Subir containers

```bash
docker-compose up --build
```

Isso irá provisionar e executar:
- 🐘 PostgreSQL 16 (banco de dados)
- 🚀 API Spring Boot (aplicação)

### 2️⃣ Verificar se está rodando

```bash
docker-compose ps
```

A API ficará disponível em: **http://localhost:8080**

### 3️⃣ Parar containers

```bash
docker-compose down
```

### 📝 Arquivo docker-compose.yaml
Contém as configurações de:
- Imagem PostgreSQL
- Variáveis de ambiente (usuário, senha, database)
- Volumes persistentes
- Porta mapeada (5432)
- Healthcheck

## 🔑 Autenticação e Endpoints

### Login
```http
POST /login
Content-Type: application/json

{
  "email": "usuario@exemplo.com",
  "password": "senha123"
}
```

**Resposta de sucesso (200):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

**Nota:** O token retornado deve ser enviado em todas as requisições subsequentes:
```
Authorization: Bearer {token}
```

### 📡 Principais Endpoints

#### 👥 Usuários
- `POST /api/user` - Criar novo usuário
- `GET /api/user` - Listar usuários (paginado)
- `GET /api/user/{id}` - Obter usuário por ID
- `PUT /api/user/{id}` - Atualizar usuário
- `DELETE /api/user/{id}` - Deletar usuário

#### 📦 Versões
- `POST /api/version` - Criar nova versão
- `GET /api/version` - Listar versões
- `GET /api/version/{id}` - Obter versão por ID
- `PUT /api/version/{id}` - Atualizar versão
- `DELETE /api/version/{id}` - Deletar versão

#### 👥 Clientes
- `POST /api/client` - Criar novo cliente
- `GET /api/client` - Listar clientes
- `GET /api/client/{id}` - Obter cliente por ID
- `PUT /api/client/{id}` - Atualizar cliente
- `DELETE /api/client/{id}` - Deletar cliente

#### 🔐 Conexões
- `POST /api/client/{clientId}/connection` - Criar conexão para cliente
- `GET /api/client/{clientId}/connection` - Listar conexões do cliente
- `GET /api/connection/{id}` - Obter conexão por ID
- `PUT /api/connection/{id}` - Atualizar conexão
- `DELETE /api/connection/{id}` - Deletar conexão

**💡 Dica:** Use o Swagger/OpenAPI em http://localhost:8080/swagger-ui/index.html para testar todos os endpoints interativamente!

## 📌 Boas Práticas Aplicadas

✅ **Versionamento de Banco** - Flyway migrations organizadas e versionadas  
✅ **Docker & Docker Compose** - Orquestração profissional do ambiente  
✅ **UUID como Chave Primária** - Maior segurança e escalabilidade distribuída  
✅ **Arquitetura em Camadas** - Separação clara e rigorosa de responsabilidades  
✅ **DTOs** - Controle limpo de entrada e saída de dados  
✅ **Tratamento Global de Exceções** - Exception handlers centralizados  
✅ **Enums Tipados** - Padronização de tipos (Roles, ConnectionType)  
✅ **Spring Security & JWT** - Autenticação e autorização robustas  
✅ **Validação com Bean Validation** - Validações em tempo de requisição  
✅ **Dependência Injection** - Uso profissional do Spring e Lombok  
✅ **Timestamps de Auditoria** - Rastreabilidade completa com CreationTimestamp  
✅ **Soft Delete** - Campo `active` para exclusão lógica de registros  
✅ **Paginação** - Endpoints com suporte a paginação automática  
✅ **Documentação OpenAPI** - Swagger gerado automaticamente  
✅ **Orientado a Domínio** - Projeto estruturado em torno de conceitos reais  

## 🚀 Diferencial do Projeto

Este projeto foi idealizado para **resolver um problema operacional real** no setor de TI em cartórios e serventias, substituindo controles manuais em planilhas por uma API estruturada, segura e escalável.

### Por que este projeto é especial?

- 🎯 **Domínio Real** - Nasceu de necessidades reais, não é apenas um projeto de aprendizado
- 🔒 **Segurança em Primeiro Lugar** - Criptografia de credenciais sensíveis e autenticação JWT robusta
- 📊 **Rastreabilidade Completa** - Auditoria de todas as ações com timestamps
- 🏗️ **Arquitetura Profissional** - Padrões de desenvolvimento adotados em grandes empresas
- 📈 **Escalabilidade** - Preparado para crescer e adicionar novas funcionalidades
- 🔄 **Versionamento Rigoroso** - Controle completo do schema com Flyway
- 📚 **Bem Documentado** - Swagger/OpenAPI integrado para fácil compreensão

### Stack Profissional

Utiliza tecnologias modernas e amplamente adotadas no mercado:
- Spring Boot 3 (framework mais usado em Java)
- PostgreSQL (banco mais confiável para produção)
- Docker (padrão de containerização da indústria)
- JWT (autenticação stateless moderna)

---

## 📞 Suporte e Contribuições

Este projeto está em desenvolvimento contínuo. Para dúvidas, sugestões ou contribuições, sinta-se livre para abrir uma issue ou pull request.
