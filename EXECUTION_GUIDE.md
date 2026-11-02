# Configuração de Execução da Aplicação

## Opção 1: Desenvolvimento Local (Recomendado - Sem Docker)

### Pré-requisitos:
- MySQL Server instalado e rodando na porta 3306
- Banco de dados criado (execute `init-db.sql`)

### Executar:
```bash
./gradlew bootRun
```

**Configuração utilizada:**
- URL: `jdbc:mysql://localhost:3306/budgeting_db`
- Usuário: `root`
- Senha: `root`
- Docker Compose: Desabilitado

---

## Opção 2: Com Docker Compose (Produção)

### Pré-requisitos:
- Docker Desktop instalado e rodando
- Docker Compose disponível

### Passos:

#### 1. Atualize a configuração do banco de dados em `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://database:3306/transaction
spring.datasource.username=app
spring.datasource.password=app
spring.docker.compose.enabled=true
```

#### 2. Execute com Docker Compose:
```bash
./gradlew bootRun
```

A aplicação iniciará o MySQL automaticamente via `compose.yml`.

---

## Opção 3: Usar Perfil de Desenvolvimento

### Edite `application-dev.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/budgeting_db
spring.datasource.username=root
spring.datasource.password=root
spring.docker.compose.enabled=false
```

### Execute com o perfil dev:
```bash
./gradlew bootRun --args='--spring.profiles.active=dev'
```

---

## Status Atual

✅ **Configurado para Desenvolvimento Local (Opção 1)**

**Para iniciar a aplicação:**

1. Certifique-se de que o MySQL está rodando:
   ```bash
   mysql -u root -p -e "SELECT 1"
   ```

2. Crie o banco de dados (se ainda não criou):
   ```bash
   mysql -u root -p < init-db.sql
   ```

3. Inicie a aplicação:
   ```bash
   ./gradlew bootRun
   ```

A aplicação estará disponível em: `http://localhost:8080`

---

## Endpoints Disponíveis

- **POST** `/transactions` - Criar transação
- **GET** `/transactions/{category}` - Listar transações por categoria
- **POST** `/transactions/ai` - Transcrever áudio e processar com IA

## Troubleshooting

### Erro: "Unable to start docker process"
✅ **Solução**: Docker Compose agora está desabilitado por padrão.

### Erro: "Access denied for user 'root'@'localhost'"
1. Verifique se o MySQL está rodando
2. Verifique as credenciais em `application.properties`
3. Verifique se o banco `budgeting_db` existe

### Erro: "Table 'budgeting_db.transaction_entity' doesn't exist"
A tabela será criada automaticamente pelo Hibernate (ddl-auto=update)

