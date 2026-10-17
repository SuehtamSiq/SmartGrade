# Documentação de Arquitetura e Estrutura - Projeto SmartGrade

## 1. Visão Geral
O projeto **SmartGrade** é desenvolvido em Java e utiliza o padrão arquitetural MVC (Model-View-Controller) combinado com o padrão DAO (Data Access Object) para gerenciar a persistência de dados.

## 2. Mapeamento da Estrutura Atual
A base do projeto está organizada da seguinte forma:

* **`.settings/`, `bin/`, `.classpath`, `.project`**: Arquivos estruturais e de configuração da IDE.
* **`database/`**: Contém os scripts de banco de dados, como o `schema.sql` para criação das tabelas.
* **`docs/`**: Centraliza a documentação técnica do projeto, dividida em subdiretórios:
  * `diagramas/`: Modelagens e fluxos do sistema.
  * `regras_de_negocio/`: Arquivos como `regras-negocio.md` detalhando lógicas de cálculo (médias, risco, faltas, etc).
  * `requisitos/`: Levantamento de requisitos funcionais e não funcionais.
* **`lib/`**: Diretório destinado a armazenar as dependências e bibliotecas externas (.jar).
* **`src/`**: Diretório principal contendo o código-fonte da aplicação.

## 3. Responsabilidades dos Pacotes (`src/`)

A camada de código-fonte está dividida por responsabilidades claras para facilitar a manutenção e escalabilidade:

* **`conexao`**: Gerencia a configuração, abertura e fechamento de conexões com o banco de dados.
* **`controller`**: Atua como intermediário entre a interface do usuário (View) e a camada de dados/regras (Model/DAO). Recebe requisições, processa fluxos básicos e retorna respostas.
* **`dao` (Data Access Object)**: Concentra exclusivamente as operações de acesso a dados (CRUD - Create, Read, Update, Delete). É quem executa as queries SQL.
* **`model`**: Representa o domínio de negócios. Contém as entidades principais do sistema, atualmente mapeadas como:
  * `Aluno.java`
  * `AnalisePreditiva.java`
  * `Avaliacao.java`
  * `Disciplina.java`
  * `RegistroAcademico.java`
  * `Turma.java`
* **`view`**: Camada responsável pela interface com o usuário, exibindo os dados e capturando interações.
