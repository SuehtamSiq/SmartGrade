# Requisitos Funcionais e Regras de Negócio — SmartGrade

---

## 1. Gestão de Alunos

| ID | Requisito Funcional | Prioridade | RN Relacionada |
|---|---|---|---|
| RF01 | O sistema deve permitir cadastrar um aluno, informando seus dados cadastrais obrigatórios. | Alta | N/A |
| RF02 | O sistema deve permitir gerar uma matrícula única para cada aluno no momento do cadastro. | Alta | RN01 |
| RF03 | O sistema deve permitir consultar a listagem geral de alunos cadastrados. | Alta | N/A |
| RF04 | O sistema deve permitir pesquisar alunos filtrando por nome ou matrícula. | Média | N/A |
| RF05 | O sistema deve permitir visualizar a tela com os dados cadastrais completos de um aluno. | Alta | N/A |
| RF06 | O sistema deve permitir editar os dados cadastrais de um aluno. | Alta | N/A |
| RF07 | O sistema deve permitir excluir o cadastro de um aluno do sistema. | Baixa | RN02 |
| RF08 | O sistema deve permitir emitir o histórico acadêmico completo de um aluno. | Média | N/A |

---

## 2. Gestão de Turmas

| ID | Requisito Funcional | Prioridade | RN Relacionada |
|---|---|---|---|
| RF09 | O sistema deve permitir cadastrar uma nova turma. | Alta | N/A |
| RF10 | O sistema deve permitir informar o período letivo vigente da turma. | Alta | N/A |
| RF11 | O sistema deve permitir consultar a listagem de turmas cadastradas. | Alta | N/A |
| RF12 | O sistema deve permitir editar as informações de uma turma. | Média | N/A |
| RF13 | O sistema deve permitir excluir uma turma do sistema. | Baixa | RN02 |
| RF14 | O sistema deve permitir vincular (matricular) alunos em uma turma específica. | Alta | N/A |
| RF15 | O sistema deve permitir remover a matrícula de um aluno de uma turma. | Média | RN02 |
| RF16 | O sistema deve permitir consultar e listar os alunos matriculados em uma turma. | Alta | N/A |

---

## 3. Gestão de Disciplinas

| ID | Requisito Funcional | Prioridade | RN Relacionada |
|---|---|---|---|
| RF17 | O sistema deve permitir cadastrar novas disciplinas. | Alta | N/A |
| RF18 | O sistema deve permitir associar nome, código identificador e carga horária à disciplina. | Alta | N/A |
| RF19 | O sistema deve permitir consultar as disciplinas cadastradas. | Alta | N/A |
| RF20 | O sistema deve permitir editar os dados de uma disciplina. | Média | N/A |
| RF21 | O sistema deve permitir excluir uma disciplina do sistema. | Baixa | RN02 |
| RF22 | O sistema deve permitir vincular disciplinas às turmas existentes. | Alta | N/A |
| RF23 | O sistema deve permitir consultar quais disciplinas estão associadas a uma determinada turma. | Alta | N/A |

---

## 4. Avaliações e Notas

| ID | Requisito Funcional | Prioridade | RN Relacionada |
|---|---|---|---|
| RF24 | O sistema deve permitir cadastrar avaliações vinculadas a uma disciplina. | Alta | N/A |
| RF25 | O sistema deve permitir categorizar o tipo da avaliação (prova, trabalho ou atividade). | Média | N/A |
| RF26 | O sistema deve permitir definir o peso numérico de cada avaliação cadastrada. | Alta | RN03 |
| RF27 | O sistema deve permitir lançar as notas obtidas pelos alunos em cada avaliação. | Alta | RN04 |
| RF28 | O sistema deve permitir alterar uma nota que já tenha sido lançada previamente. | Média | RN04 |
| RF29 | O sistema deve permitir visualizar a listagem de notas de um aluno específico. | Alta | N/A |
| RF30 | O sistema deve permitir ao professor consultar o boletim/notas de todos os alunos de sua turma. | Alta | N/A |
| RF31 | O sistema deve exibir a média ponderada do aluno atualizada em tempo real na tela. | Alta | RN03 |
| RF32 | O sistema deve disparar a rotina de atualização de médias assim que uma alteração de nota for salva. | Alta | RN03 |
| RF33 | O sistema deve validar e bloquear o salvamento de notas fora da escala permitida. | Alta | RN04 |

---

## 5. Frequência

| ID | Requisito Funcional | Prioridade | RN Relacionada |
|---|---|---|---|
| RF34 | O sistema deve permitir registrar a presença diária ou por aula dos alunos. | Alta | N/A |
| RF35 | O sistema deve permitir registrar as faltas dos alunos associadas a uma data ou aula específica. | Alta | N/A |
| RF36 | O sistema deve permitir consultar o histórico detalhado de chamadas e faltas de um aluno. | Média | N/A |
| RF37 | O sistema deve exibir em tela o percentual consolidado de frequência do aluno. | Alta | RN05 |
| RF38 | O sistema deve exibir em tela o percentual consolidado de faltas do aluno. | Alta | RN05 |
| RF39 | O sistema deve permitir consultar o mapa de frequência consolidado de todos os alunos de uma turma. | Média | N/A |
| RF40 | O sistema deve sinalizar visualmente na tela os alunos que se aproximam ou ultrapassam o limite de faltas. | Média | RN05, RN06 |

---

## 6. Situação Acadêmica

| ID | Requisito Funcional | Prioridade | RN Relacionada |
|---|---|---|---|
| RF41 | O sistema deve atribuir e exibir o status da situação acadêmica do aluno com base nos critérios estabelecidos. | Alta | RN06 |
| RF42 | O sistema deve exibir o indicador visual de status "Aprovado" para alunos que cumpram os requisitos. | Alta | RN06 |
| RF43 | O sistema deve exibir o indicador visual de status "Reprovado por Nota" para alunos elegíveis. | Alta | RN06 |
| RF44 | O sistema deve exibir o indicador visual de status "Reprovado por Falta" para alunos elegíveis. | Alta | RN06 |
| RF45 | O sistema deve exibir o indicador visual de status "Recuperação" caso o aluno atinja a faixa exigida. | Média | RN06 |
| RF46 | O sistema deve reavaliar e atualizar o status da situação acadêmica imediatamente após alterações de notas ou faltas. | Alta | RN06 |
| RF47 | O sistema deve permitir exportar ou consultar a lista de situações acadêmicas finais dos alunos de uma turma. | Média | N/A |

---

## 7. Módulo Preditivo — Machine Learning

| ID | Requisito Funcional | Prioridade | RN Relacionada |
|---|---|---|---|
| RF48 | O sistema deve exportar internamente os dados acadêmicos vigentes para alimentar a pipeline de análise preditiva. | Alta | RN07 |
| RF49 | O sistema deve processar a base de dados históricos armazenada para servir de treinamento à IA. | Média | RN07 |
| RF50 | O sistema deve extrair o histórico de notas como variável de entrada para os modelos de risco. | Alta | RN03, RN07 |
| RF51 | O sistema deve extrair as taxas de faltas acumuladas como variável de entrada para os modelos de risco. | Alta | RN05, RN07 |
| RF52 | O sistema deve executar o algoritmo de IA para gerar o score final de classificação de risco do estudante. | Alta | RN07 |
| RF53 | O sistema deve rotular a classificação final do aluno em três faixas de criticidade. | Alta | RN07 |
| RF54 | O sistema deve exibir o nível de risco gerado em áreas restritas aos perfis pedagógicos autorizados. | Média | N/A |
| RF55 | O sistema deve recalcular e atualizar o score de IA periodicamente de acordo com a entrada de novos dados acadêmicos. | Média | RN07 |
| RF56 | O sistema deve listar de forma isolada os alunos identificados com tendências latentes de reprovação ou evasão. | Alta | RN07 |
| RF57 | O sistema deve fornecer painéis explicativos dos fatores de risco para embasamento do acompanhamento pedagógico. | Média | N/A |
| RF58 | O sistema deve gravar em banco de dados o log com o histórico de previsões de risco anteriores para auditoria. | Baixa | N/A |

---

## 8. Dashboard

| ID | Requisito Funcional | Prioridade | RN Relacionada |
|---|---|---|---|
| RF59 | O sistema deve consolidar os indicadores acadêmicos macros em uma interface gráfica de Dashboard. | Alta | N/A |
| RF60 | O sistema deve renderizar gráficos informando a volumetria quantitativa de alunos por status situacional. | Média | RN06 |
| RF61 | O sistema deve exibir gráficos comparativos sobre a assiduidade e médias gerais das turmas. | Média | RN05 |
| RF62 | O sistema deve plotar a curva de evolução de desempenho de notas médias gerais do ecossistema escolar. | Média | RN03 |
| RF63 | O sistema deve apresentar a volumetria absoluta de alunos ativos por segmento de criticidade de risco. | Alta | RN07 |
| RF64 | O sistema deve fixar alertas visuais em topo de página destacando os alunos que constam na faixa crítica de alto risco. | Alta | RN07 |
| RF65 | O sistema deve oferecer um botão de ação rápida para acessar a ficha de acompanhamento dos alunos sinalizados em risco. | Média | N/A |
| RF66 | O sistema deve invalidar o cache e renderizar os dados atualizados do dashboard em janelas de tempo agendadas. | Média | N/A |

---

## 9. Alertas

| ID | Requisito Funcional | Prioridade | RN Relacionada |
|---|---|---|---|
| RF67 | O sistema deve emitir notificações automáticas em sistema para a equipe pedagógica quando um aluno entrar em risco. | Alta | RN07 |
| RF68 | O sistema deve disparar alerta preventivo quando o percentual de faltas de um aluno ultrapassar o limiar crítico secundário. | Alta | RN05, RN06 |
| RF69 | O sistema deve disparar alerta preventivo quando a média parcial de um aluno cair abaixo do limiar aceitável. | Alta | RN03, RN06 |
| RF70 | O sistema deve destacar o perfil do aluno de maneira diferenciada se o módulo preditivo prever risco iminente de evasão. | Alta | RN07 |

---

## 10. Relatórios e Consultas

| ID | Requisito Funcional | Prioridade | RN Relacionada |
|---|---|---|---|
| RF71 | O sistema deve permitir gerar relatórios consolidados em formato legível sobre a performance geral de uma turma. | Média | N/A |
| RF72 | O sistema deve permitir exportar em PDF o histórico escolar e boletim individual do estudante solicitado. | Média | N/A |
| RF73 | O sistema deve permitir gerar relatórios analíticos focados exclusivamente no mapa de faltas recorrentes. | Baixa | RN05 |
| RF74 | O sistema deve permitir gerar relatórios comparativos unindo curvas de notas e médias por período letivo. | Baixa | RN03 |
| RF75 | O sistema deve emitir um relatório customizado listando especificamente os alunos contidos no grupo de alto risco. | Média | RN07 |

---
