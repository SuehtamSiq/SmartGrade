# Requisitos Não Funcionais — SmartGrade

---

## 1. Desempenho

| ID | Requisito Não Funcional | Prioridade | Critério de Aceitação / Métrica | RF Relacionado |
|---|---|---|---|---|
| RNF01 | O tempo de resposta para operações transacionais simples de cadastro, consulta e atualização deve ser baixo. | Alta | O processamento e retorno na tela não devem ultrapassar **2,0 segundos** sob carga normal. | RF01, RF03, RF06 |
| RNF02 | A atualização dos dados derivados em tela após salvar alterações deve ser síncrona. | Alta | A renderização da tela atualizada deve ocorrer em até **500 milissegundos** após a confirmação do banco de dados. | RF31, RF32, RF46 |
| RNF03 | O carregamento inicial dos indicadores visuais do Dashboard deve ser otimizado. | Média | Os gráficos e componentes visuais do painel principal devem carregar completamente em até **3,0 segundos**. | Módulo 8 (Dashboards) |
| RNF04 | O processamento da inferência do modelo preditivo de Machine Learning deve rodar sem travar a interface. | Média | O tempo de resposta do algoritmo para classificar o risco de um aluno individual deve ser inferior a **1,5 segundo**. | RF52, RF53 |

---

## 2. Segurança e Controle de Acesso

| ID | Requisito Não Funcional | Prioridade | Critério de Aceitação / Métrica | RF Relacionado |
|---|---|---|---|---|
| RNF05 | O sistema deve restringir a visibilidade de telas e componentes usando o modelo de Controle de Acesso Baseado em Funções (RBAC). | Alta | Usuários logados como "Aluno" não devem conseguir instanciar views restritas a "Professor" ou "Coordenador". | Todos os módulos |
| RNF06 | O sistema deve criptografar senhas de usuários antes do armazenamento no banco de dados. | Alta | Uso obrigatório do algoritmo de hash **BCrypt** ou **PBKDF2**; senhas nunca devem ser salvas em texto limpo. | Módulo 1 |
| RNF07 | A aplicação deve interceptar chamadas inválidas na camada de persistência para evitar SQL Injection. | Alta | Uso obrigatório de **PreparedStatement** em todas as queries e instruções construídas via JDBC. | Todos os módulos |

---

## 3. Integridade e Persistência dos Dados

| ID | Requisito Não Funcional | Prioridade | Critério de Aceitação / Métrica | RF Relacionado |
|---|---|---|---|---|
| RNF08 | O banco de dados deve impor restrições de integridade referencial diretamente no esquema. | Alta | Configuração explícita de chaves estrangeiras (`FOREIGN KEY`) com diretivas `ON DELETE RESTRICT` nas tabelas principais. | RN02 |
| RNF09 | O banco de dados deve impor a unicidade de registros de identificação de forma nativa. | Alta | Aplicação da restrição `UNIQUE KEY` na coluna de matrícula da tabela de alunos do MySQL. | RN01 |
| RNF10 | Operações de escrita que envolvam múltiplas tabelas dependentes devem ser atômicas. | Alta | Uso explícito de transações (`Connection.setAutoCommit(false)`, `commit()` e `rollback()`) para garantir que se um passo falhar, nada seja salvo. | RF27, RF34, RF46 |

---

## 4. Usabilidade (Interface Java Swing)

| ID | Requisito Não Funcional | Prioridade | Critério de Aceitação / Métrica | RF Relacionado |
|---|---|---|---|---|
| RNF11 | A interface gráfica baseada em Java Swing deve seguir um padrão visual padronizado e limpo. | Alta | Uso de uma biblioteca de Look and Feel moderna (ex: **FlatLaf** ou **Nimbus**) para garantir consistência estética. | Todos os dashboards e telas |
| RNF12 | A navegação do sistema deve ser operável via teclado para acelerar o fluxo de trabalho do usuário. | Média | Mapeamento de atalhos de teclado (Mnemonic Keys via `setMnemonic`) para os botões de ações principais (Salvar, Cancelar, Excluir). | Telas de cadastro |
| RNF13 | Os componentes de formulários devem guiar o usuário visualmente quanto à obrigatoriedade e limites de dados. | Média | Campos obrigatórios devem conter um marcador visual (`*`) e máscaras de entrada (`JFormattedTextField`) onde couber. | RF01, RF10, RF18, RF26 |

---

## 5. Arquitetura e Manutenibilidade

| ID | Requisito Não Funcional | Prioridade | Critério de Aceitação / Métrica | RF Relacionado |
|---|---|---|---|---|
| RNF14 | O projeto deve seguir estritamente o padrão de arquitetura **Model-View-Controller (MVC)**. | Alta | Divisão clara em pacotes separados: `com.smartgrade.model`, `com.smartgrade.view` e `com.smartgrade.controller`. | Estrutura Geral |
| RNF15 | O isolamento do acesso ao banco de dados deve utilizar o padrão **Data Access Object (DAO)**. | Alta | Nenhuma classe contida nas camadas de View ou Controller pode executar código SQL ou abrir conexões JDBC diretamente. | Camada de Dados |
| RNF16 | O código-fonte deve adotar as convenções oficiais de nomenclatura da linguagem Java. | Média | Classes em `PascalCase`, métodos/variáveis em `camelCase`, constantes em `UPPER_SNAKE_CASE` e pacotes em letras minúsculas. | Código-fonte |

---

## 6. Compatibilidade e Ambiente de Execução

| ID | Requisito Não Funcional | Prioridade | Critério de Aceitação / Métrica | RF Relacionado |
|---|---|---|---|---|
| RNF17 | A aplicação deve ser compilada e executada utilizando a especificação estável do ecossistema Java. | Alta | Compatibilidade nativa assegurada com o **Java JDK 21 (LTS)**. | Ambiente |
| RNF18 | O sistema deve rodar de forma multiplataforma em desktops sem necessidade de emuladores adicionais. | Alta | O arquivo final compilado (`.jar` executável) deve rodar de forma idêntica em **Windows 10/11, macOS e Linux** com a JRE 21 instalada. | Execução |
| RNF19 | A versão do Sistema Gerenciador de Banco de Dados (SGBD) deve ser fixa e homologada. | Alta | Compatibilidade com o **MySQL 8.0** ou superior, utilizando o driver oficial `mysql-connector-j`. | Persistência |

---

## 7. Confiabilidade e Tolerância a Falhas

| ID | Requisito Não Funcional | Prioridade | Critério de Aceitação / Métrica | RF Relacionado |
|---|---|---|---|---|
| RNF20 | Falhas de infraestrutura externa (rede ou banco de dados fora do ar) não podem corromper a execução da memória do app. | Alta | Captura obrigatória de exceções do tipo `SQLException` com tratamento em blocos `try-catch-finally`. | Comunicação com SGBD |
| RNF21 | O fechamento inesperado do programa não deve deixar dados parciais corrompidos nas tabelas acadêmicas. | Alta | Garantido nativamente pela Engine de armazenamento **InnoDB** do MySQL através de suas propriedades ACID. | Persistência |

---

## 8. Escalabilidade e Extensibilidade

| ID | Requisito Não Funcional | Prioridade | Critério de Aceitação / Métrica | RF Relacionado |
|---|---|---|---|---|
| RNF22 | A modelagem das tabelas do banco de dados deve suportar o crescimento volumétrico linear do colégio. | Média | O esquema deve responder com a mesma velocidade em consultas paginadas contendo até **100.000 registros** de notas/frequências. | Módulo 10 |
| RNF23 | O design do módulo preditivo deve aplicar conceitos de baixo acoplamento para substituição de algoritmos. | Média | Uso de Interfaces Java para que o modelo preditivo baseado em Machine Learning possa ser trocado sem alterar os Controllers ou as Views. | Módulo 7 |

---

## 9. Versionamento e Gerência de Configuração

| ID | Requisito Não Funcional | Prioridade | Critério de Aceitação / Métrica | RF Relacionado |
|---|---|---|---|---|
| RNF24 | O ciclo de desenvolvimento do software deve ser auditável e controlado centralizadamente. | Alta | Uso obrigatório do **Git** para controle de versão local e **GitHub** para hospedagem do repositório remoto. | Governança |
| RNF25 | O fluxo de trabalho de código na árvore de branches deve seguir boas práticas de Engenharia de Software. | Média | Proibição de commits diretos na branch principal (`main`). Uso obrigatório de feature branches e abertura de Pull Requests. | Repositório |

---

## 10. Requisitos de Engenharia do Módulo de Machine Learning

| ID | Requisito Não Funcional | Prioridade | Critério de Aceitação / Métrica | RF Relacionado |
|---|---|---|---|---|
| RNF26 | O pipeline de dados que alimenta a inteligência artificial deve rodar em segundo plano (background thread). | Média | O processamento dos dados históricos para a IA não deve congelar a tela principal do usuário (uso de `SwingWorker` ou threads paralelas). | RF48, RF52 |
| RNF27 | O modelo preditivo deve ser exportado em um formato padrão interoperável para ser lido facilmente pelo Java. | Média | O modelo treinado em ferramentas externas (como Python/Scikit-Learn) deve ser exportado em formatos como **PMML** ou **ONNX** para consumo no Java. | RF52 |

---

## 11. Documentação Técnica do Projeto

| ID | Requisito Não Funcional | Prioridade | Critério de Aceitação / Métrica | RF Relacionado |
|---|---|---|---|---|
| RNF28 | O repositório deve instruir perfeitamente qualquer desenvolvedor a subir o ambiente do zero através de documentação. | Alta | Inclusão de um arquivo `README.md` na raiz do projeto com o passo a passo de configuração do JDK, importação de dependências e execução. | DevOps |
| RNF29 | O esquema físico do banco de dados deve ser versionado junto ao código. | Alta | Disponibilização de um arquivo de script `.sql` (contendo as instruções estruturais de `CREATE TABLE`, constraints e dados iniciais de semente). | Banco de Dados |
