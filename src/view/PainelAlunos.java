package view;

import controller.AcademicoController;
import model.Aluno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PainelAlunos extends JPanel {

    private AcademicoController controller;
    private JTextField txtRa;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTable tabelaAlunos;
    private DefaultTableModel tableModel;

    public PainelAlunos() {
        this.controller = new AcademicoController();
        setLayout(new BorderLayout(10, 10));

        // Formulário de Cadastro (Norte)
        JPanel painelForm = new JPanel(new GridLayout(4, 2, 8, 8));
        painelForm.setBorder(BorderFactory.createTitledBorder("Cadastro de Alunos"));

        painelForm.add(new JLabel("RA:"));
        txtRa = new JTextField();
        painelForm.add(txtRa);

        painelForm.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelForm.add(txtNome);

        painelForm.add(new JLabel("E-mail:"));
        txtEmail = new JTextField();
        painelForm.add(txtEmail);

        JButton btnSalvar = new JButton("Salvar Aluno");
        btnSalvar.addActionListener(e -> salvarAluno());
        painelForm.add(btnSalvar);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(e -> limparCampos());
        painelForm.add(btnLimpar);

        add(painelForm, BorderLayout.NORTH);

        // Tabela de Alunos (Centro)
        tableModel = new DefaultTableModel(new Object[]{"ID", "RA", "Nome", "E-mail"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaAlunos = new JTable(tableModel);
        add(new JScrollPane(tabelaAlunos), BorderLayout.CENTER);
        
     // Painel para o botão de exclusão na parte inferior
        javax.swing.JPanel painelAcoes = new javax.swing.JPanel();
        javax.swing.JButton btnExcluir = new javax.swing.JButton("Excluir Aluno Selecionado");

        btnExcluir.addActionListener(e -> {
            int linhaSelecionada = tabelaAlunos.getSelectedRow();
            
            if (linhaSelecionada >= 0) {
                int confirmacao = javax.swing.JOptionPane.showConfirmDialog(this, 
                    "Tem a certeza que deseja inativar este aluno? O histórico será preservado.", 
                    "Confirmar Exclusão", 
                    javax.swing.JOptionPane.YES_NO_OPTION);

                if (confirmacao == javax.swing.JOptionPane.YES_OPTION) {
                    try {
                        // Captura o ID na coluna 0 da linha selecionada
                        int idAluno = (int) tableModel.getValueAt(linhaSelecionada, 0);

                        // Executa a exclusão lógica
                        dao.AlunoDAO dao = new dao.AlunoDAO();
                        dao.excluir(idAluno);

                        javax.swing.JOptionPane.showMessageDialog(this, "Aluno excluído com sucesso!");
                        
                        // Recarrega a tabela instantaneamente para remover o registo do ecrã
                        carregarTabelaAlunos(); 

                    } catch (Exception ex) {
                        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao excluir aluno: " + ex.getMessage());
                    }
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecione um aluno na tabela primeiro.");
            }
        });

        painelAcoes.add(btnExcluir);
        add(painelAcoes, java.awt.BorderLayout.SOUTH);

        carregarTabelaAlunos();
    }

    public void carregarTabelaAlunos() {
        tableModel.setRowCount(0);
        List<Aluno> lista = controller.listarAlunos();
        for (Aluno a : lista) {
            tableModel.addRow(new Object[]{a.getIdAluno(), a.getRa(), a.getNome(), a.getEmail()});
        }
    }

    public void salvarAluno() {
        String resp = controller.salvarAluno(txtNome.getText(), txtRa.getText(), txtEmail.getText());
        JOptionPane.showMessageDialog(this, resp);
        limparCampos();
        carregarTabelaAlunos();
    }

    public void limparCampos() {
        txtRa.setText("");
        txtNome.setText("");
        txtEmail.setText("");
    }
}