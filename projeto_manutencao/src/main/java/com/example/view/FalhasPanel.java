package com.example.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.example.controllers.FalhaController;
import com.example.models.Falha;

public class FalhasPanel extends JPanel {
    // Atributos
    private FalhaController falhaController;
    private JTable falhasTable;
    private DefaultTableModel tableModel;
    private JButton btnSalvarAlteracoes;
    private JButton btnCadastrarFalha;

    public FalhasPanel() {
        super(new BorderLayout());

        // Inicializando o controlador
        falhaController = new FalhaController();

        // Inicializando o model da tabela com as colunas
        tableModel = new DefaultTableModel(new Object[]{
                "ID", "Máquina ID", "Data", "Problema", "Prioridade", "Operador"
        }, 0);

        // Criar JTable com o model
        falhasTable = new JTable(tableModel);

        // Preenchendo a tabela com as falhas do controlador
        carregarFalhas();

        // Adicionando a JTable a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(falhasTable);
        this.add(scrollPane, BorderLayout.CENTER);

        // Criando painel inferior com botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarFalha = new JButton("Cadastrar");
        btnSalvarAlteracoes = new JButton("Atualizar");
        painelInferior.add(btnCadastrarFalha);
        painelInferior.add(btnSalvarAlteracoes);
        this.add(painelInferior, BorderLayout.SOUTH);

        // Adicionando ActionListeners para os botões
        addActionListeners();
    }

    private void carregarFalhas() {
        List<Falha> falhas = falhaController.readFalhas();
        for (Falha falha : falhas) {
            tableModel.addRow(new Object[]{
                    falha.getId(),
                    falha.getMaquinaID(),
                    falha.getData().toString(),
                    falha.getProblema(),
                    falha.getPrioridade(),
                    falha.getOperador()
            });
        }
    }

    private void addActionListeners() {
        // ActionListener para o botão "Cadastrar"
        btnCadastrarFalha.addActionListener(e -> cadastrarFalha());

        // ActionListener para o botão "Salvar"
        btnSalvarAlteracoes.addActionListener(e -> editarFalha());
    }

    private void cadastrarFalha() {
        try {
            String maquinaId = JOptionPane.showInputDialog("Máquina ID:");
            if (maquinaId == null) return; // Cancelar

            String dataStr = JOptionPane.showInputDialog("Data (yyyy-MM-dd):");
            if (dataStr == null) return; // Cancelar
            LocalDate data = LocalDate.parse(dataStr);

            String problema = JOptionPane.showInputDialog("Problema:");
            if (problema == null) return; // Cancelar

            String prioridade = JOptionPane.showInputDialog("Prioridade:");
            if (prioridade == null) return; // Cancelar

            String operador = JOptionPane.showInputDialog("Operador:");
            if (operador == null) return; // Cancelar

            // Cria um novo objeto Falha
            Falha novaFalha = new Falha(null, maquinaId, data, problema, prioridade, operador);

            // Envia para a API
            Falha falhaCriada = falhaController.createFalha(novaFalha);

            // Se a falha criada não for nula, atualiza a tabela
            if (falhaCriada != null) {
                tableModel.addRow(new Object[]{
                        falhaCriada.getId(),
                        maquinaId,
                        data.toString(),
                        problema,
                        prioridade,
                        operador
                });
                JOptionPane.showMessageDialog(this, "Falha cadastrada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar falha.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao preencher os dados: " + ex.getMessage());
        }
    }

    private void editarFalha() {
        int selectedRow = falhasTable.getSelectedRow();
        if (selectedRow != -1) {
            String id = String.valueOf(tableModel.getValueAt(selectedRow, 0));
            String maquinaId = (String) tableModel.getValueAt(selectedRow, 1);
            LocalDate data = LocalDate.parse((String) tableModel.getValueAt(selectedRow, 2));
            String problema = (String) tableModel.getValueAt(selectedRow, 3);
            String prioridade = (String) tableModel.getValueAt(selectedRow, 4);
            String operador = (String) tableModel.getValueAt(selectedRow, 5);

            JTextField[] campos = {
                    new JTextField(maquinaId),
                    new JTextField(data.toString()),
                    new JTextField(problema),
                    new JTextField(prioridade),
                    new JTextField(operador)
            };

            String[] perguntas = {
                    "Máquina ID:",
                    "Data (yyyy-MM-dd):",
                    "Problema:",
                    "Prioridade:",
                    "Operador:"
            };

            for (int i = 0; i < perguntas.length; i++) {
                int resposta = JOptionPane.showConfirmDialog(null, campos[i], perguntas[i], JOptionPane.OK_CANCEL_OPTION);
                if (resposta != JOptionPane.OK_OPTION) {
                    return;
                }
            }

            JButton btnSubmit = new JButton("Salvar");
            btnSubmit.addActionListener(ev -> {
                try {
                    String newMaquinaId = campos[0].getText();
                    LocalDate newData = LocalDate.parse(campos[1].getText());
                    String newProblema = campos[2].getText();
                    String newPrioridade = campos[3].getText();
                    String newOperador = campos[4].getText();

                    Falha falhaAtualizada = new Falha(id, newMaquinaId, newData, newProblema, newPrioridade, newOperador);
                    falhaController.updateFalha(falhaAtualizada);
                    JOptionPane.showMessageDialog(null, "Alterações salvas com sucesso!");

                    tableModel.setValueAt(newMaquinaId, selectedRow, 1);
                    tableModel.setValueAt(newData.toString(), selectedRow, 2);
                    tableModel.setValueAt(newProblema, selectedRow, 3);
                    tableModel.setValueAt(newPrioridade, selectedRow, 4);
                    tableModel.setValueAt(newOperador, selectedRow, 5);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao preencher os dados: " + ex.getMessage());
                }
            });

            btnSubmit.doClick(); // Aciona o botão para salvar
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma falha para salvar alterações.");
        }
    }

    // Método para obter os dados das falhas
    public String getDados() {
        StringBuilder dados = new StringBuilder();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            dados.append("ID: ").append(tableModel.getValueAt(i, 0)).append(", ");
            dados.append("Máquina ID: ").append(tableModel.getValueAt(i, 1)).append(", ");
            dados.append("Data: ").append(tableModel.getValueAt(i, 2)).append(", ");
            dados.append("Problema: ").append(tableModel.getValueAt(i, 3)).append(", ");
            dados.append("Prioridade: ").append(tableModel.getValueAt(i, 4)).append(", ");
            dados.append("Operador: ").append(tableModel.getValueAt(i, 5)).append("\n");
        }
        return dados.toString();
    }
}
