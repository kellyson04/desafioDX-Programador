## Como executar e testar

### Requisitos

- Java 8
- Maven 3.9.x instalado
- PostgreSQL em execução

Execute os comandos na pasta que contém o `pom.xml`. Confira com `mvn -version` se o Maven está usando Java 8.

### Configurar o banco

Crie o banco no PostgreSQL:

```sql
CREATE DATABASE duxus_desafio;
```

Configure as variáveis no PowerShell, substituindo a senha:

```powershell
$env:DB_URL = "jdbc:postgresql://localhost:5432/duxus_desafio"
$env:DB_USERNAME = "postgres"
$env:DB_PASSWORD = "SUA_SENHA"
```

### Iniciar a aplicação

No mesmo terminal, execute:

```powershell
mvn spring-boot:run
```

A aplicação ficará disponível em `http://localhost:8080`. O Hibernate criará as tabelas.

Pelo IntelliJ, selecione Java 8 e configure as mesmas variáveis em **Run → Edit Configurations → Environment variables**.

### Testar pelo Postman

Nos cadastros, selecione **Body → raw → JSON**.

#### 1. Cadastrar integrante

```text
POST http://localhost:8080/integrantes
```

```json
{
  "nome": "Michael Jordan",
  "funcao": "ala"
}
```

Retorno esperado: `201 Created`, com os dados do integrante e seu ID.

#### 2. Cadastrar time

```text
POST http://localhost:8080/times
```

Substitua `1` pelo ID retornado no cadastro anterior.

```json
{
  "nomeDoClube": "Chicago Bulls",
  "data": "1995-01-01",
  "integrantesIds": [1]
}
```

Retorno esperado: `201 Created`, sem corpo.

#### 3. Consultar relatórios

Use `GET` com o endereço `http://localhost:8080/processamentos` seguido de uma das rotas:

| Rota | Parâmetros |
|---|---|
| `/time-da-data` | `data`, obrigatório |
| `/integrante-mais-usado` | `dataInicial` e `dataFinal`, opcionais |
| `/integrantes-do-time-mais-recorrente` | `dataInicial` e `dataFinal`, opcionais |
| `/funcao-mais-recorrente` | `dataInicial` e `dataFinal`, opcionais |
| `/clube-mais-recorrente` | `dataInicial` e `dataFinal`, opcionais |
| `/contagem-de-clubes` | `dataInicial` e `dataFinal`, opcionais |
| `/contagem-por-funcao` | `dataInicial` e `dataFinal`, opcionais |

Informe as datas no formato `yyyy-MM-dd`, pela aba **Params** ou na URL. Consultas `GET` não precisam de corpo JSON.

Exemplos:

```text
http://localhost:8080/processamentos/time-da-data?data=1995-01-01
```

```text
http://localhost:8080/processamentos/clube-mais-recorrente?dataInicial=1993-01-01&dataFinal=1995-01-01
```

```text
http://localhost:8080/processamentos/contagem-de-clubes
```

Sem as datas opcionais, a consulta considera todos os períodos. Os dados dos testes unitários não são cadastrados automaticamente no banco.

### Executar testes

Somente os testes do `ApiService`, sem precisar de banco:

```powershell
mvn -Dtest=TesteApiService test
```

Toda a suíte, com PostgreSQL disponível e variáveis configuradas:

```powershell
mvn test
```

Os relatórios ficam em `target/surefire-reports`.

### Escopo

A entrega contém o backend. As telas de cadastro e montagem de times não foram implementadas.
