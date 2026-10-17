# Regras de Negócio — SmartGrade

## 1. Objetivo

Este documento define as regras de negócio que determinam o comportamento
e as decisões acadêmicas do sistema SmartGrade.

As regras estabelecem os critérios para cadastro e relacionamento dos
dados acadêmicos, lançamento e cálculo de notas, controle de frequência,
determinação da situação acadêmica e análise preditiva de risco.

As regras de negócio são independentes da tecnologia utilizada na
implementação do sistema.

---

# 2. Cadastro e Identificação

### RN01 — Identificação única do aluno

Cada aluno deve possuir um Registro Acadêmico (RA) único no sistema.

### RN02 — E-mail único

Cada aluno deve possuir um endereço de e-mail único no sistema.

### RN03 — Identificação da turma

Cada turma deve possuir um código único e estar associada a um período
letivo.

### RN04 — Identificação da disciplina

Cada disciplina deve possuir um código único no sistema.

### RN05 — Dados obrigatórios

Os dados obrigatórios definidos para alunos, turmas e disciplinas devem
ser preenchidos antes da conclusão do cadastro.

### RN06 — Integridade dos cadastros

Um registro cadastral não deve ser excluído quando sua remoção resultar
em inconsistência nos registros acadêmicos relacionados.

---

# 3. Matrícula e Relacionamentos Acadêmicos

### RN07 — Associação de aluno à turma

Um aluno pode ser associado a uma ou mais turmas, desde que respeitadas
as regras acadêmicas do período letivo.

### RN08 — Matrícula única na turma

Um aluno não pode possuir mais de uma associação com a mesma turma.

### RN09 — Associação de disciplina à turma

Uma disciplina pode ser oferecida em uma ou mais turmas.

### RN10 — Registro acadêmico

Um registro acadêmico deve estar obrigatoriamente relacionado a um aluno,
uma disciplina e uma turma existentes no sistema.

### RN11 — Unicidade do registro acadêmico

Um aluno não pode possuir mais de um registro acadêmico para a mesma
disciplina dentro da mesma turma.

---

# 4. Avaliações

### RN12 — Associação da avaliação

Toda avaliação deve estar associada a uma disciplina cadastrada.

### RN13 — Identificação da avaliação

Toda avaliação deve possuir um tipo e uma descrição que permitam
identificá-la no contexto da disciplina.

### RN14 — Peso da avaliação

Toda avaliação deve possuir um peso utilizado no cálculo da média.

### RN15 — Soma dos pesos

A soma dos pesos das avaliações que compõem a média final deve totalizar
100% (1,00).

### RN16 — Escala de notas

As notas devem utilizar uma escala de 0,00 a 10,00.

### RN17 — Validação da nota

O sistema não deve permitir o lançamento de notas inferiores a 0,00 ou
superiores a 10,00.

### RN18 — Alteração de nota

Uma nota já lançada pode ser alterada por meio das funcionalidades
autorizadas do sistema.

### RN19 — Recalculo da média

A média final deve ser recalculada sempre que uma nota utilizada em seu
cálculo for incluída ou alterada.

---

# 5. Cálculo da Média

### RN20 — Média ponderada

A média final deve ser calculada utilizando as notas das avaliações e
seus respectivos pesos.

A fórmula geral será:

Média Final = Σ (Nota × Peso)

### RN21 — Pesos definidos para o projeto

Para o modelo acadêmico adotado pelo SmartGrade, serão consideradas três
avaliações principais:

- P1: peso de 30%;
- P2: peso de 40%;
- Trabalho: peso de 30%.

A fórmula adotada será:

Média Final = (P1 × 0,30) + (P2 × 0,40) + (Trabalho × 0,30)

### RN22 — Arredondamento

A média final deve ser apresentada com duas casas decimais.

O arredondamento deve ocorrer somente para apresentação e armazenamento
do resultado final, sem alterar os valores das notas individuais.

---

# 6. Frequência

### RN23 — Registro de faltas

As faltas devem ser registradas para cada aluno associado à disciplina.

### RN24 — Cálculo da frequência

O percentual de frequência deve ser calculado com base na quantidade de
aulas realizadas e nas faltas registradas.

### RN25 — Percentual de frequência

O percentual de frequência será calculado pela seguinte fórmula:

Frequência (%) = ((Total de aulas - Total de faltas) / Total de aulas) × 100

### RN26 — Limite mínimo de frequência

O percentual mínimo de frequência para aprovação será de 75%.

### RN27 — Reprovação por falta

O aluno que apresentar frequência inferior a 75% será considerado
reprovado por falta, independentemente de sua média final.

### RN28 — Alerta de frequência

O sistema deverá considerar como situação de atenção os alunos que
apresentarem frequência entre 75% e 80%.

### RN29 — Atualização da frequência

O percentual de frequência deve ser atualizado após o registro ou
alteração das faltas.

---

# 7. Situação Acadêmica

### RN30 — Critérios de situação acadêmica

A situação acadêmica deve considerar simultaneamente a média final e o
percentual de frequência do aluno.

### RN31 — Aprovação direta

O aluno será considerado "Aprovado" quando:

- Média final maior ou igual a 7,00; e
- Frequência maior ou igual a 75%.

### RN32 — Situação de exame

O aluno será considerado "Exame" quando:

- Média final maior ou igual a 5,00 e inferior a 7,00; e
- Frequência maior ou igual a 75%.

### RN33 — Reprovação por nota

O aluno será considerado "Reprovado por Nota" quando:

- Média final inferior a 5,00; e
- Frequência maior ou igual a 75%.

### RN34 — Reprovação por falta

O aluno será considerado "Reprovado por Falta" quando sua frequência
for inferior a 75%, independentemente de sua média final.

### RN35 — Prioridade da frequência

Quando um aluno não atingir o percentual mínimo de frequência, a
situação "Reprovado por Falta" terá prioridade sobre as situações
determinadas pela média.

### RN36 — Situação inicial

Enquanto os dados necessários para determinar a situação acadêmica ainda
não estiverem disponíveis, o aluno poderá permanecer na situação
"Cursando".

### RN37 — Atualização da situação

A situação acadêmica deve ser recalculada automaticamente após alterações
nas notas ou na frequência.

### RN38 — Exame fora do escopo inicial

A identificação da situação "Exame" faz parte do escopo do sistema,
porém o cálculo de uma eventual nota pós-exame não faz parte da primeira
versão do projeto.

---

# 8. Análise Preditiva

### RN39 — Objetivo da análise preditiva

O módulo preditivo tem como objetivo identificar precocemente alunos que
apresentem maior risco acadêmico, auxiliando no acompanhamento
pedagógico.

### RN40 — Dados utilizados na análise

A análise preditiva deve utilizar dados acadêmicos disponíveis no
sistema, podendo considerar:

- notas;
- médias;
- percentual de frequência;
- quantidade de faltas;
- evolução do desempenho acadêmico;
- quantidade de avaliações realizadas.

### RN41 — Score de risco

A análise preditiva deve produzir um score de risco representado por um
valor entre 0,00 e 1,00.

Quanto maior o score, maior será o risco acadêmico estimado.

### RN42 — Classificação do risco

O score de risco será classificado nas seguintes categorias:

| Score | Classificação |
|---|---|
| 0,00 a 0,39 | Baixo Risco |
| 0,40 a 0,69 | Médio Risco |
| 0,70 a 1,00 | Alto Risco |

### RN43 — Baixo risco

Alunos com score entre 0,00 e 0,39 serão classificados como "Baixo
Risco".

### RN44 — Médio risco

Alunos com score entre 0,40 e 0,69 serão classificados como "Médio
Risco".

Esses alunos deverão ser considerados como casos que merecem atenção
pedagógica.

### RN45 — Alto risco

Alunos com score entre 0,70 e 1,00 serão classificados como "Alto
Risco".

Esses alunos deverão ser destacados para acompanhamento pedagógico.

### RN46 — Atualização da análise

A análise preditiva deve ser atualizada quando novos dados acadêmicos
relevantes forem registrados ou alterados.

### RN47 — Histórico da análise

Cada análise preditiva deve possuir data de realização e poderá ser
armazenada para permitir o acompanhamento da evolução do risco do aluno.

### RN48 — Independência da previsão

A classificação de risco não deve alterar automaticamente a situação
acadêmica oficial do aluno.

A situação acadêmica oficial deve continuar sendo determinada pelas
regras de notas e frequência.

### RN49 — Interpretação da previsão

A classificação de risco deve ser utilizada como ferramenta de apoio à
decisão e acompanhamento pedagógico, não como confirmação de reprovação
ou evasão.

# Regras de Negócio (RN) — SmartGrade

## 1. Objetivo
Este documento define as regras de negócio abstratas que determinam o comportamento, as validações e as decisões acadêmicas do sistema SmartGrade. Estas regras são independentes da linguagem de programação ou do banco de dados e servem para garantir a integridade dos processos escolares.

---

## 2. Cadastro, Identificação e Integridade

| ID | Regra de Negócio | Critério Operacional / Restrição |
|---|---|---|
| **RN01** | **Identificação única do aluno (RA)** | Cada aluno deve possuir um Registro Acadêmico (RA) estritamente único. O sistema deve impedir o salvamento de homônimos com o mesmo RA. |
| **RN02** | **E-mail corporativo único** | Não é permitido o cadastro de dois alunos ou usuários utilizando o mesmo endereço de e-mail no banco de dados. |
| **RN03** | **Identificação de Turma** | Cada turma deve possuir um código alfanumérico único vinculado obrigatoriamente a um único período letivo ativo. |
| **RN04** | **Identificação de Disciplina** | Cada disciplina deve possuir um código curricular fixo e único no sistema. |
| **RN05** | **Dados obrigatórios de input** | O sistema deve recusar cadastros de alunos, turmas ou disciplinas caso os campos essenciais (Nome, Documento, Código, Período) estejam nulos ou em branco. |
| **RN06** | **Integridade de registros vinculados** | Um registro cadastral (Aluno, Turma ou Disciplina) não deve ser excluído fisicamente do sistema se houver dependências (Ex: notas ou faltas atreladas). Deve ser aplicada a deleção lógica. |

---

## 3. Matrícula e Relacionamentos Acadêmicos

| ID | Regra de Negócio | Critério Operacional / Restrição |
|---|---|---|
| **RN07** | **Associação de Aluno à Turma** | Um aluno pode ser associado a uma ou mais turmas simultaneamente, contanto que pertençam ao mesmo período letivo e não criem conflitos de horário. |
| **RN08** | **Unicidade de vínculo por turma** | É vedada a duplicação de matrícula de um mesmo aluno dentro da mesma turma. |
| **RN09** | **Oferta de Disciplinas** | Uma única disciplina estrutural pode ser ofertada em múltiplas turmas de forma independente. |
| **RN10** | **Composição do Registro Acadêmico** | Todo registro de desempenho (boletim) deve, obrigatoriamente, referenciar uma tríade válida: um aluno existente, uma disciplina existente e uma turma cadastrada. |
| **RN11** | **Unicidade do Registro Disciplinar** | Um aluno não pode possuir mais de um histórico acadêmico ou diário de classe ativo para a mesma disciplina dentro da mesma turma. |

---

## 4. Avaliações e Escala de Notas

| ID | Regra de Negócio | Critério Operacional / Restrição |
|---|---|---|
| **RN12** | **Vinculação de Avaliação** | Nenhuma avaliação (prova, trabalho) pode existir solta no sistema; deve estar amarrada a uma disciplina do período. |
| **RN13** | **Identificação do Instrumento** | Toda avaliação exige uma categorização fixa (Prova, Trabalho, Atividade) e uma descrição textual identificadora. |
| **RN14** | **Peso da Avaliação** | Toda avaliação cadastrada precisa ter um peso atribuído maior do que zero, que será injetado no cálculo das médias. |
| **RN15** | **Consistência da Soma dos Pesos** | A somatória total dos pesos configurados para as avaliações regulares de uma disciplina deve fechar estritamente em **100% (ou 1,00)**. |
| **RN16** | **Escala Métrica de Notas** | O sistema adota exclusivamente a escala numérica contínua que varia de **0,00 (nota mínima)** a **10,00 (nota máxima)**. |
| **RN17** | **Validação de Entrada de Notas** | Qualquer tentativa de lançamento de notas abaixo de 0,00 ou acima de 10,00 deve disparar uma exceção de validação e interromper o salvamento. |
| **RN18** | **Auditoria de Alteração de Nota** | Notas lançadas podem ser modificadas a qualquer tempo, desde que a operação seja efetuada por um perfil autenticado com permissões de Professor ou Coordenador. |
| **RN19** | **Gatilho de Recálculo Automático** | A alteração, inclusão ou deleção de qualquer nota unitária obriga o sistema a disparar imediatamente a rotina de atualização da média final do aluno afetado. |

---

## 5. Fórmulas de Cálculo de Médias

| ID | Regra de Negócio | Expressão Matemática / Critério de Arredondamento |
|---|---|---|
| **RN20** | **Fórmula da Média Ponderada Geral** | A média final é calculada de forma ponderada através da equação estrutural:<br>$$\text{Média Final} = \sum (\text{Nota} \times \text{Peso})$$ |
| **RN21** | **Pesos Parametrizados da Versão** | O modelo acadêmico padrão do SmartGrade fixa três avaliações obrigatórias estruturadas assim:<br>• **P1**: Peso de 30% (0,30)<br>• **P2**: Peso de 40% (0,40)<br>• **Trabalho**: Peso de 30% (0,30)<br>$$\text{Média Final} = (P1 \times 0,30) + (P2 \times 0,40) + (\text{Trabalho} \times 0,30)$$ |
| **RN22** | **Precisão e Arredondamento** | O valor da média final calculado deve ser truncado ou arredondado para apresentar estritamente **duas casas decimais** para exibição e gravação em banco, preservando as notas parciais originais intactas. |

---

## 6. Frequência e Assiduidade

| ID | Regra de Negócio | Expressão Matemática / Restrição Acadêmica |
|---|---|---|
| **RN23** | **Granularidade do Registro de Faltas** | As ausências devem ser computadas individualmente por aluno e por aula ministrada na disciplina. |
| **RN24** | **Base de Cálculo da Frequência** | O cálculo do indicador de presença baseia-se na relação entre as aulas planejadas/ministradas e as ausências computadas. |
| **RN25** | **Fórmula da Taxa de Frequência** | O percentual consolidado de assiduidade é extraído usando a equação matemática:<br>$$\text{Frequência (\%)} = \left(\frac{\text{Total de Aulas} - \text{Total de Faltas}}{\text{Total de Aulas}}\right) \times 100$$ |
| **RN26** | **Piso Mínimo de Aprovação por Presença** | É fixado o percentual mínimo de **75% de frequência** como critério eliminatório para que o aluno seja aprovado de forma regular. |
| **RN27** | **Soberania da Reprovação por Faltas** | Alunos que acumularem uma taxa de frequência estritamente inferior a 75% serão classificados imediatamente como reprovados por infrequência, ignorando qualquer nota atingida. |
| **RN28** | **Zona de Atenção Preventiva** | Estudantes que apresentarem uma taxa de assiduidade situada na faixa limite **entre 75% e 80%** devem ser rotulados em estado de atenção. |
| **RN29** | **Atualização Síncrona de Assiduidade** | O indicador de frequência percentual deve ser reprocessado na mesma hora em que uma falta for inserida ou removida do diário de classe. |

---

## 7. Critérios de Definição da Situação Acadêmica Oficial

| ID | Regra de Negócio | Condições Obrigatórias de Cruzamento | Status Resultante |
|---|---|---|---|
| **RN30** | **Multicritério Situacional** | A definição do estado do aluno exige a validação lógica concorrente da Nota Final **E** da Frequência Acumulada. | N/A |
| **RN31** | **Aprovação Direta** | Média Final $\ge 7,00$ **E** Frequência $\ge 75\%$ | **Aprovado** |
| **RN32** | **Elegibilidade para Exame** | Média Final $\ge 5,00$ e $< 7,00$ **E** Frequência $\ge 75\%$ | **Exame** |
| **RN33** | **Reprovação por Desempenho** | Média Final $< 5,00$ **E** Frequência $\ge 75\%$ | **Reprovado por Nota** |
| **RN34** | **Reprovação por Absenteísmo** | Frequência $< 75\%$ (Independentemente do valor da Média Final) | **Reprovado por Falta** |
| **RN35** | **Precedência de Regra** | A regra **RN34** (Falta) possui prioridade de execução absoluta sobre as regras baseadas em nota (**RN31, RN32 e RN33**). | **Reprovado por Falta** |
| **RN36** | **Estado de Transição Inicial** | Período letivo em andamento com dados de notas ou chamadas ainda incompletos. | **Cursando** |
| **RN37** | **Gatilho de Status** | Qualquer modificação nos registros base (Notas/Faltas) força o reprocessamento imediato do status do aluno. | Dinâmico |
| **RN38** | **Delimitação de Escopo (Fase 1)** | A identificação do status "Exame" deve ser gerada pelo sistema, porém, as regras de notas de corte pós-exame não integram o escopo da primeira versão. | Retido para o Escopo Futuro |

---

## 8. Análise Preditiva e Score de Risco (Machine Learning)

| ID | Regra de Negócio | Critério Estatístico / Definição de Variáveis |
|---|---|---|
| **RN39** | **Objetivo do Módulo Preditivo** | O cálculo preditivo atua de forma preventiva e serve apenas como apoio pedagógico, sem peso de decisão oficial na aprovação do aluno. |
| **RN40** | **Vetor de Características (Features)** | O modelo de IA deve ler obrigatoriamente as seguintes variáveis como entrada: Notas parciais, Média móvel atual, Taxa percentual de faltas, Contagem absoluta de faltas e Volume de avaliações concluídas. |
| **RN41** | **Escala de Risco Numérica** | A saída estatística calculada pelo algoritmo de Machine Learning deve gerar um score contido estritamente no intervalo de **0,00 (Risco Nulo)** a **1,00 (Risco Máximo)**. |
| **RN42** | **Mapeamento de Faixas de Criticidade** | O score gerado em **RN41** deve categorizar o nível de atenção do aluno conforme a tabela de corte abaixo: |

### Classificação de Faixas de Risco (Módulo Preditivo)

| Score Calculado pela IA | Classificação de Criticidade | Ação do Sistema / Destino Pedagógico | ID da Regra |
|---|---|---|---|
| De `0,00` até `0,39` | **Baixo Risco** | Situação considerada estável; sem necessidade de intervenção pedagógica na listagem. | **RN43** |
| De `0,40` até `0,69` | **Médio Risco** | Situação de atenção; o aluno deve entrar nos painéis de monitoramento preventivo. | **RN44** |
| De `0,70` até `1,00` | **Alto Risco** | Situação crítica; o aluno deve ser destacado com prioridade máxima para acompanhamento pedagógico imediato. | **RN45** |

| ID | Regra de Negócio | Critério Operacional / Restrição |
|---|---|---|
| **RN46** | **Recálculo do Vetor Preditivo** | O score probabilístico de inteligência artificial deve ser reprocessado em lotes agendados ou logo após modificações consistentes no histórico escolar do aluno. |
