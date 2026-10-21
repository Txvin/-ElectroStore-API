# 🛒 TechStore API - Loja de Eletrônicos

API backend desenvolvida em **Java** com **Spring Boot** para gerenciar uma loja de eletrônicos, focando em segurança de rotas, controle de acesso e operações de e-commerce.

---

## 🚀 Tecnologias Utilizadas

* **Java 21**
* **Spring Boot**
* **Spring Security** (Autenticação e Controle de Acesso)
* **Spring Data JPA** (Persistência de Dados)
* **H2 Database** (Banco de Dados)
* **Maven** (Gerenciador de Dependências)

---

## 🔒 Segurança (Spring Security)

O projeto implementa regras de segurança para proteger os endpoints da loja:
* **Rotas Públicas:** Consulta de produtos disponíveis (`GET /produtos`).
* **Rotas Protegidas (Admin):** Cadastro, atualização e remoção de produtos (`POST`, `PUT`, `DELETE /produtos`), exigindo autenticação e perfil de administrador (`ADMIN`).
* **Rotas Protegidas (Cliente):** Acesso ao carrinho e histórico de compras (`USER`).

---

## ⚙️ Como Executar o Projeto

### Pré-requisitos
* Ter o **Java JDK 21** instalado.
* Ter o **Maven** configurado (ou usar a IDE IntelliJ IDEA / Eclipse).

### Passo a passo
