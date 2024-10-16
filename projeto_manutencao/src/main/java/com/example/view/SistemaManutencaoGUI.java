package com.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SistemaManutencaoGUI extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel painelMaquinas;
    private JPanel painelManutencao;
    private JPanel painelFalhas;
    private JPanel painelTecnicos;
    private JButton btnGerarRelatorio;

    public SistemaManutencaoGUI() {
        super("Sistema de Manutenção");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // Inicialização dos paineis
        painelMaquinas = new MaquinasPanel();
        painelManutencao = new ManutencaoPanel();
        painelFalhas = new FalhasPanel();
        painelTecnicos = new TecnicosPanel();

        // Criar meu TabbedPane
        tabbedPane = new JTabbedPane();
        tabbedPane.add("Máquinas", painelMaquinas);
        tabbedPane.add("Manutenções", painelManutencao);
        tabbedPane.add("Falhas", painelFalhas);
        tabbedPane.add("Técnicos", painelTecnicos);

        // Criar botão de gerar relatório
        btnGerarRelatorio = new JButton("Gerar Relatório");
        btnGerarRelatorio.addActionListener(this::gerarRelatorio);

        // Adicionar o TabbedPane e o botão ao JFrame
        this.add(tabbedPane, BorderLayout.CENTER);
        this.add(btnGerarRelatorio, BorderLayout.SOUTH);
    }

    private void gerarRelatorio(ActionEvent e) {
        StringBuilder relatorio = new StringBuilder();

        // Coletar dados de cada painel
        relatorio.append("Relatório de Manutenção\n");
        relatorio.append("===================================\n");

        // Coleta de informações
        relatorio.append("Máquinas:\n");
        relatorio.append(((MaquinasPanel) painelMaquinas).getDados()).append("\n");

        relatorio.append("Manutenções:\n");
        relatorio.append(((ManutencaoPanel) painelManutencao).getDados()).append("\n");

        relatorio.append("Falhas:\n");
        relatorio.append(((FalhasPanel) painelFalhas).getDados()).append("\n");

        relatorio.append("Técnicos:\n");
        relatorio.append(((TecnicosPanel) painelTecnicos).getDados()).append("\n");

        // Exibir relatório
        JOptionPane.showMessageDialog(this, relatorio.toString(), "Relatório", JOptionPane.INFORMATION_MESSAGE);

        // Salvar relatório em arquivo
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Salvar Relatório");
            int userSelection = fileChooser.showSaveDialog(this);
            
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                    writer.write(relatorio.toString());
                    JOptionPane.showMessageDialog(this, "Relatório salvo com sucesso em: " + fileToSave.getAbsolutePath());
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar o relatório: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaManutencaoGUI frame = new SistemaManutencaoGUI();
            frame.setVisible(true);
        });
    }
}
