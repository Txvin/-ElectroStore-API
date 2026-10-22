# ⚡ EletricStore API

API REST de uma loja de eletrônicos, desenvolvida com **Spring Boot** para gerenciar **categorias** e **produtos**.

Projeto acadêmico com arquitetura em camadas: Model, Repository, DTO, Service e Controller.

---

## 🧰 Tecnologias

- Java 21
- Spring Boot 4.0.5
- Spring Web
- Spring Data JPA
- Spring Security
- Bean Validation
- H2 Database (em memória)
- springdoc-openapi (Swagger UI)
- Maven

---

## 🗂️ Estrutura do projeto

```
src/main/java/com/eletricstore/eletricstore
├── controller    # recebe as requisições HTTP
├── service       # regras de negócio
├── repository    # acesso ao banco (Spring Data JPA)
├── dto           # objetos de entrada e saída da API
├── model         # entidades (tabelas)
└── exception     # exceções de negócio
```

---

## ▶️ Como executar

**Pré-requisito:** Java 21 instalado.

```bash
git clone https://github.com/Txvin/-ElectroStore-API.git
cd -ElectroStore-API
./mvnw spring-boot:run
```

No Windows, use `mvnw.cmd spring-boot:run`.

A aplicação sobe em `http://localhost:8080`.

---

## 📖 Documentação (Swagger)

Com a aplicação rodando, abra:

👉 http://localhost:8080/swagger-ui.html

Faça login quando solicitado:

| Usuário | Senha |
|---|---|
| `admin` | `admin123` |

---

## 🗄️ Console do banco (H2)

👉 http://localhost:8080/h2-console

| Campo | Valor |
|---|---|
| JDBC URL | `jdbc:h2:mem:eletricstoredb` |
| User | `sa` |
| Password | *(vazio)* |

O banco é em memória: os dados são apagados quando a aplicação para.

---

## 🔌 Endpoints

### Categorias — `/api/categorias`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/api/categorias` | Lista todas |
| GET | `/api/categorias/{id}` | Busca por id |
| POST | `/api/categorias` | Cria (retorna 201) |
| PUT | `/api/categorias/{id}` | Atualiza |
| DELETE | `/api/categorias/{id}` | Exclui (retorna 204) |

### Produtos — `/api/produtos`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/api/produtos` | Lista todos |
| GET | `/api/produtos?categoriaId=1` | Lista os de uma categoria |
| GET | `/api/produtos/{id}` | Busca por id |
| POST | `/api/produtos` | Cria (retorna 201) |
| PUT | `/api/produtos/{id}` | Atualiza |
| DELETE | `/api/produtos/{id}` | Exclui (retorna 204) |

### Exemplos de corpo (JSON)

**Categoria**
```json
{ "nome": "Celulares" }
```

**Produto**
```json
{
  "nome": "Smartphone X",
  "preco": 2999.90,
  "categoriaId": 1
}
```

---

## 📏 Regras de negócio

- O nome da categoria deve ser único (sem diferenciar maiúsculas de minúsculas).
- Uma categoria que possui produtos não pode ser excluída.
- Todo produto precisa pertencer a uma categoria existente.
- O preço do produto deve ser maior que zero.
- Nome é obrigatório em categorias e produtos.

---

## ✅ Status do projeto

- [x] Configuração (Spring Initializr, Swagger, H2)
- [x] Model, Repository, DTO, Service e Controller
- [x] Validação dos dados de entrada
- [ ] Configuração do Spring Security
- [ ] Tratamento global de erros
- [ ] Testes de integração dos Controllers

---

## 📄 Licença

Distribuído sob a licença MIT. Veja o arquivo `LICENSE`.
