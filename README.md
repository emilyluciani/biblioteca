# 📚 Sistema de Gerenciamento de Biblioteca em Java

Projeto de backend desenvolvido em Java com foco em **Programação Orientada a Objetos (POO)**, modelagem de sistemas e implementação de regras de negócio em um cenário de gerenciamento de biblioteca. Projeto desenvolvido para a disciplina de Programação Orientada a Objetos, do curso de Sistemas de Informação.

📅 **Desenvolvido em:** Novembro de 2026

---

## 🚀 Sobre o projeto

Este sistema simula o funcionamento de uma biblioteca, permitindo o gerenciamento de livros, usuários, bibliotecários e empréstimos.

O projeto contempla diferentes tipos de materiais, como **livros físicos, ebooks e revistas**, cada um com regras específicas de empréstimo, prazos de devolução e cálculo de multas.

O desenvolvimento foi baseado em conceitos de **Programação Orientada a Objetos**, com foco na organização, reutilização de código, tratamento de exceções e implementação de regras de negócio.

---

## 💼 Competências demonstradas

- Desenvolvimento backend com Java
- Programação Orientada a Objetos (POO)
- Encapsulamento, herança e polimorfismo
- Abstração por meio de classes abstratas
- Implementação e utilização de interfaces
- Modelagem de classes e entidades
- Implementação de regras de negócio
- Tratamento de exceções personalizadas
- Manipulação de datas com `LocalDate`
- Persistência de dados com serialização de objetos
- Integração com API externa (Open Library)
- Organização e estruturação de código

---

## ⚙️ Funcionalidades

- Cadastro e gerenciamento de livros e usuários
- Representação de diferentes tipos de materiais (livros físicos, ebooks e revistas)
- Busca de livros pelo título
- Controle de empréstimos e devoluções
- Verificação de disponibilidade dos materiais
- Definição de limites e prazos de empréstimos
- Cálculo de datas previstas para devolução
- Identificação de empréstimos atrasados
- Cálculo de multas por atraso
- Tratamento de situações excepcionais, como livros indisponíveis e limites de empréstimos atingidos
- Consulta de informações bibliográficas por meio da API Open Library
- Salvamento e carregamento de dados por meio de serialização

---

## 🧱 Estrutura do projeto

O projeto está organizado no pacote `model`, responsável pelas classes que representam as entidades e funcionalidades do sistema.

```text
sistema-biblioteca-java/
├── src/
│   ├── Main.java
│   └── model/
│       ├── ApiLivros.java
│       ├── Biblioteca.java
│       ├── Bibliotecario.java
│       ├── Ebook.java
│       ├── Emprestimo.java
│       ├── LimiteEmprestimosException.java
│       ├── Livro.java
│       ├── LivroFisico.java
│       ├── LivroIndisponivelException.java
│       ├── Multavel.java
│       ├── Persistencia.java
│       ├── Pessoa.java
│       ├── RegistroNaoEncontradoException.java
│       ├── Revista.java
│       └── Usuario.java
└── README.md
```

---

## ▶️ Como executar o projeto

### ✅ Pré-requisitos

- Ter o **Java JDK 11 ou superior** instalado
- Ter uma IDE de desenvolvimento, como IntelliJ IDEA, Eclipse ou VS Code
- Ter acesso à internet para utilizar a integração com a Open Library API

---

### 🔹 Passo a passo

1. **Baixe ou clone o repositório:**

```bash
git clone https://github.com/emilyluciani/biblioteca.git
```

2. **Acesse a pasta do projeto:**

```bash
cd biblioteca
```

3. **Abra o projeto na IDE de sua preferência.**

4. **Configure e execute a classe principal (`main`) da aplicação.**

---

## 📖 API utilizada

O projeto utiliza a **Open Library API** para realizar consultas de livros por título, permitindo obter informações como:

- Título da obra
- Nome do autor
- Número de páginas
- Idioma

Documentação: [https://openlibrary.org/developers/api](https://openlibrary.org/developers/api)

---

## 🎯 Objetivo

Consolidar conhecimentos em desenvolvimento backend com Java, aplicando conceitos de Programação Orientada a Objetos, modelagem de sistemas e regras de negócio por meio da construção de um sistema de gerenciamento de biblioteca.

---

## 👤 Autores

Emily Maria Luciani, Erika Kanashiro, Giovana Resnauer e Nathaly Abreu.

Projeto acadêmico — Sistemas de Informação | PUCPR.
