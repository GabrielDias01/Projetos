package com.example.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.controllers.ManutencaoController;
import com.example.models.Manutencao;

public class ManutencaoPanel extends JPanel {
    // Atributos
    private ManutencaoController manutencaoController;
    private JTable manutencoesTable;
    private DefaultTableModel tableModel;
    private JButton btnSalvarAlteracoes;
    private JButton btnCadastrarManutencao;

    public ManutencaoPanel() {
        super(new BorderLayout());

        // Inicializando o controlador
        manutencaoController = new ManutencaoController();

        // Inicializando o model da tabela com as colunas
        tableModel = new DefaultTableModel(new Object[] {
                "ID", "Máquina ID", "Data", "Tipo", "Peças Trocadas", "Tempo de Parada", "Técnico ID", "Observações"
        }, 0); // Número de linhas inicial: 0

        // Criar JTable com o model
        manutencoesTable = new JTable(tableModel);

        // Preenchendo a tabela com as manutenções do controlador
        List<Manutencao> manutencoes = manutencaoController.readManutencoes();
        for (Manutencao manutencao : manutencoes) {
            tableModel.addRow(new Object[] {
                    manutencao.getId(),
                    manutencao.getMaquinaID(),
                    manutencao.getData(),
                    manutencao.getTipo(),
                    manutencao.getPecasTrocadas(),
                    manutencao.getTempoDeParada(),
                    manutencao.getTecnicoID(),
                    manutencao.getObservacoes()
            });
        }

        // Adicionando a JTable a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(manutencoesTable);
        this.add(scrollPane, BorderLayout.CENTER);

        // Criando painel inferior com botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarManutencao = new JButton("Cadastrar");
        btnSalvarAlteracoes = new JButton("Atualizar");
        painelInferior.add(btnCadastrarManutencao);
        painelInferior.add(btnSalvarAlteracoes);
        this.add(painelInferior, BorderLayout.SOUTH);

        // Adicionando ActionListeners para os botões
        addActionListeners();
    }

    private void addActionListeners() {
        // ActionListener para o botão "Cadastrar"
        btnCadastrarManutencao.addActionListener(e -> openManutencaoDialog(null));

        // ActionListener para o botão "Salvar Manutenção"
        btnSalvarAlteracoes.addActionListener(e -> {
            int selectedRow = manutencoesTable.getSelectedRow();
            if (selectedRow != -1) {
                openManutencaoDialog(selectedRow);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecione uma linha para editar.");
            }
        });
    }

    private void openManutencaoDialog(Integer selectedRow) {
        String maquinaId = promptForInput("Máquina ID:", selectedRow, 1);
        String dataString = promptForInput("Data (yyyy-MM-dd):", selectedRow, 2);
        String tipo = promptForInput("Tipo:", selectedRow, 3);
        String pecasTrocadas = promptForInput("Peças Trocadas:", selectedRow, 4);
        String tempoDeParadaString = promptForInput("Tempo de Parada:", selectedRow, 5);
        String tecnicoId = promptForInput("Técnico ID:", selectedRow, 6);
        String observacoes = promptForInput("Observações:", selectedRow, 7);

        if (maquinaId != null && dataString != null && tipo != null && pecasTrocadas != null &&
                tempoDeParadaString != null && tecnicoId != null && observacoes != null) {
            try {
                LocalDate data = LocalDate.parse(dataString);
                int tempoDeParada = Integer.parseInt(tempoDeParadaString);

                Manutencao manutencao;
                if (selectedRow == null) {
                    manutencao = new Manutencao(null, maquinaId, data, tipo, pecasTrocadas, tempoDeParada, tecnicoId,
                            observacoes);
                    Manutencao manutencaoCriada = manutencaoController.createManutencao(manutencao);

                    if (manutencaoCriada != null) {
                        tableModel.addRow(new Object[] {
                                manutencaoCriada.getId(),
                                maquinaId, data, tipo, pecasTrocadas, tempoDeParada, tecnicoId, observacoes
                        });
                        JOptionPane.showMessageDialog(this, "Manutenção cadastrada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao cadastrar manutenção.");
                    }
                } else {
                    String id = String.valueOf(tableModel.getValueAt(selectedRow, 0)); // ID da manutenção
                    manutencao = new Manutencao(id, maquinaId, data, tipo, pecasTrocadas, tempoDeParada, tecnicoId,
                            observacoes);
                    manutencaoController.updateManutencao(manutencao); // Atualiza a manutenção na API

                    // Atualiza a tabela
                    updateTableRow(selectedRow, manutencao);
                    JOptionPane.showMessageDialog(this, "Alterações salvas com sucesso!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao preencher os dados: " + ex.getMessage());
            }
        }
    }

    private String promptForInput(String message, Integer selectedRow, int column) {
        String currentValue = null;
        if (selectedRow != null) {
            currentValue = String.valueOf(tableModel.getValueAt(selectedRow, column));
        }
        return JOptionPane.showInputDialog(this, message, currentValue);
    }

    private void updateTableRow(int rowIndex, Manutencao manutencao) {
        tableModel.setValueAt(manutencao.getMaquinaID(), rowIndex, 1);
        tableModel.setValueAt(manutencao.getData().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), rowIndex, 2);
        tableModel.setValueAt(manutencao.getTipo(), rowIndex, 3);
        tableModel.setValueAt(manutencao.getPecasTrocadas(), rowIndex, 4);
        tableModel.setValueAt(manutencao.getTempoDeParada(), rowIndex, 5);
        tableModel.setValueAt(manutencao.getTecnicoID(), rowIndex, 6);
        tableModel.setValueAt(manutencao.getObservacoes(), rowIndex, 7);
    }

    // Método para obter os dados das manutenções
    public String getDados() {
        StringBuilder dados = new StringBuilder();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            dados.append("ID: ").append(tableModel.getValueAt(i, 0)).append(", ");
            dados.append("Máquina ID: ").append(tableModel.getValueAt(i, 1)).append(", ");
            dados.append("Data: ").append(tableModel.getValueAt(i, 2)).append(", ");
            dados.append("Tipo: ").append(tableModel.getValueAt(i, 3)).append(", ");
            dados.append("Peças Trocadas: ").append(tableModel.getValueAt(i, 4)).append(", ");
            dados.append("Tempo de Parada: ").append(tableModel.getValueAt(i, 5)).append(", ");
            dados.append("Técnico ID: ").append(tableModel.getValueAt(i, 6)).append(", ");
            dados.append("Observações: ").append(tableModel.getValueAt(i, 7)).append("\n");
        }
        return dados.toString();
    }
}
