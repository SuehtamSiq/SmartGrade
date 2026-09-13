package view;

import controller.AcademicoController;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TelaPrincipal extends JFrame {

    private AcademicoController controllerGeral;
    private Map<String, JPanel> paineis;
    private JPanel painelContainer;
    private CardLayout cardLayout;

    private JButton btnAlunos;
    private JButton btnLancamento;
    private JButton btnDashboard;

    public TelaPrincipal() {
        this.controllerGeral = new AcademicoController();
        this.paineis = new HashMap<>();

        setTitle("SmartGrade - Gestão Acadêmica Inteligente");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Barra de Navegação (Norte)
        JPanel painelNav = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        btnAlunos = new JButton("Alunos");
        btnLancamento = new JButton("Lançamento de Notas/Faltas");
        btnDashboard = new JButton("Dashboard & Risco");

        painelNav.add(btnAlunos);
        painelNav.add(btnLancamento);
        painelNav.add(btnDashboard);

        add(painelNav, BorderLayout.NORTH);

        // Container com CardLayout para alternar telas
        cardLayout = new CardLayout();
        painelContainer = new JPanel(cardLayout);

        inicializarTelas();

        add(painelContainer, BorderLayout.CENTER);

        // Eventos dos botões
        btnAlunos.addActionListener(e -> alternarPainel("ALUNOS"));
        btnLancamento.addActionListener(e -> alternarPainel("LANCAMENTO"));
        btnDashboard.addActionListener(e -> alternarPainel("DASHBOARD"));
    }

    public void inicializarTelas() {
        paineis.put("ALUNOS", new PainelAlunos());
        paineis.put("LANCAMENTO", new PainelLancamento());
        paineis.put("DASHBOARD", new PainelDashboard());

        for (Map.Entry<String, JPanel> entry : paineis.entrySet()) {
            painelContainer.add(entry.getValue(), entry.getKey());
        }
    }

    public void alternarPainel(String nomePainel) {
        cardLayout.show(painelContainer, nomePainel);
    }
}