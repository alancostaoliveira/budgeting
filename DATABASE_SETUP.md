# Configuração do Banco de Dados MySQL

## Pré-requisitos

- MySQL Server instalado e rodando
- MySQL CLI instalado (opcional, pode usar ferramentas gráficas como MySQL Workbench)

## Configuração

### 1. Criar o Banco de Dados

Execute um dos seguintes comandos:

#### Usando MySQL CLI:
```bash
mysql -u root -p < init-db.sql
```

#### Usando MySQL Workbench:
1. Abra o MySQL Workbench
2. Conecte-se ao servidor MySQL
3. Copie e execute o conteúdo do arquivo `init-db.sql`

#### Manualmente no MySQL:
```sql
CREATE DATABASE IF NOT EXISTS budgeting_db;
USE budgeting_db;

CREATE TABLE IF NOT EXISTS transaction_entity (
    id CHAR(36) PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    amount BIGINT NOT NULL,
    category VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_category ON transaction_entity(category);
CREATE INDEX idx_created_at ON transaction_entity(created_at);
```

### 2. Configurar Credenciais

O arquivo `application.properties` está configurado com:
- **URL**: `jdbc:mysql://localhost:3306/budgeting_db`
- **Usuário**: `root`
- **Senha**: `root`

Se suas credenciais forem diferentes, atualize o arquivo:
```properties
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 3. Verificar Configuração (Opcional)

Para testar a conexão, você pode executar a aplicação:
```bash
./gradlew bootRun
```

O Hibernate criará automaticamente as tabelas se não existirem (ddl-auto=update).

## Estrutura das Tabelas

### transaction_entity
| Coluna | Tipo | Descrição |
|--------|------|-----------|
| id | CHAR(36) | UUID da transação |
| description | VARCHAR(255) | Descrição da transação |
| amount | BIGINT | Valor em centavos |
| category | VARCHAR(50) | Categoria (FOOD, TRANSPORT, etc) |
| created_at | TIMESTAMP | Data de criação |
| updated_at | TIMESTAMP | Data de atualização |

## Próximos Passos

A aplicação está configurada para:
- ✅ Conectar ao MySQL automáticamente
- ✅ Criar/atualizar tabelas automaticamente (Hibernate DDL)
- ✅ Usar Spring Data JPA para persistência
- ✅ Integrar com OpenAI para transcrição de áudio e chat

