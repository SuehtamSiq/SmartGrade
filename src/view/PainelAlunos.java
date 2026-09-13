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