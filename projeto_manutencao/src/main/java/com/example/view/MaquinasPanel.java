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
import javax.swing.table.DefaultTableModel;

import com.example.controllers.MaquinaController;
import com.example.models.Maquina;

public class MaquinasPanel extends JPanel {
    private MaquinaController maquinaController;
    private JTable maquinasTable;
    private DefaultTableModel tableModel;
    private JButton btnSalvarAlteracoes;
    private JButton btnCadastrarMaquina;

    public MaquinasPanel() {
        super(new BorderLayout());
        maquinaController = new MaquinaController();
        tableModel = new DefaultTableModel(new Object[]{
                "ID", "Código", "Nome", "Modelo", "Fabricante", "Data de Aquisição", "Tempo de Vida Estimado", "Localização", "Detalhes", "Manual"
        }, 0);
        maquinasTable = new JTable(tableModel);
        List<Maquina> maquinas = maquinaController.readMaquinas();
        for (Maquina maquina : maquinas) {
            tableModel.addRow(new Object[]{
                    maquina.getId(),
                    maquina.getCodigo(),
                    maquina.getNome(),
                    maquina.getModelo(),
                    maquina.getFabricante(),
                    maquina.getDataAquisicao().toString(),
                    maquina.getTempoVidaEstimado(),
                    maquina.getLocalizacao(),
                    maquina.getDetalhes(),
                    maquina.getManual()
            });
        }
        JScrollPane scrollPane = new JScrollPane(maquinasTable);
        this.add(scrollPane, BorderLayout.CENTER);
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarMaquina = new JButton("Cadastrar");
        btnSalvarAlteracoes = new JButton("Atualizar");
        painelInferior.add(btnCadastrarMaquina);
        painelInferior.add(btnSalvarAlteracoes);
        this.add(painelInferior, BorderLayout.SOUTH);
        addActionListeners();
    }

    private void addActionListeners() {
        btnCadastrarMaquina.addActionListener(e -> cadastrarMaquina());
        btnSalvarAlteracoes.addActionListener(e -> {
            int selectedRow = maquinasTable.getSelectedRow();
            if (selectedRow != -1) {
                abrirDialogoEdicao(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma máquina para salvar alterações.");
            }
        });
    }

    private void cadastrarMaquina() {
        String codigo = JOptionPane.showInputDialog(this, "Digite o Código:");
        if (codigo == null) return; // Cancelado

        String nome = JOptionPane.showInputDialog(this, "Digite o Nome:");
        if (nome == null) return; // Cancelado

        String modelo = JOptionPane.showInputDialog(this, "Digite o Modelo:");
        if (modelo == null) return; // Cancelado

        String fabricante = JOptionPane.showInputDialog(this, "Digite o Fabricante:");
        if (fabricante == null) return; // Cancelado

        String dataAquisicaoStr = JOptionPane.showInputDialog(this, "Digite a Data de Aquisição (yyyy-MM-dd):");
        if (dataAquisicaoStr == null) return; // Cancelado

        String tempoVidaEstimadoStr = JOptionPane.showInputDialog(this, "Digite o Tempo de Vida Estimado:");
        if (tempoVidaEstimadoStr == null) return; // Cancelado

        String localizacao = JOptionPane.showInputDialog(this, "Digite a Localização:");
        if (localizacao == null) return; // Cancelado

        String detalhes = JOptionPane.showInputDialog(this, "Digite os Detalhes:");
        if (detalhes == null) return; // Cancelado

        String manual = JOptionPane.showInputDialog(this, "Digite o Manual (link ou descrição):");
        if (manual == null) return; // Cancelado

        try {
            LocalDate dataAquisicao = LocalDate.parse(dataAquisicaoStr);
            int tempoVidaEstimado = Integer.parseInt(tempoVidaEstimadoStr);

            Maquina novaMaquina = new Maquina(null, codigo, nome, modelo, fabricante, dataAquisicao, tempoVidaEstimado,
                    localizacao, detalhes, manual);

            Maquina maquinaCriada = maquinaController.createMaquina(novaMaquina);
            if (maquinaCriada != null) {
                tableModel.addRow(new Object[]{
                        maquinaCriada.getId(), codigo, nome, modelo, fabricante, dataAquisicao.toString(),
                        tempoVidaEstimado, localizacao, detalhes, manual
                });
                JOptionPane.showMessageDialog(this, "Máquina cadastrada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar máquina.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao preencher os dados: " + ex.getMessage());
        }
    }

    private void abrirDialogoEdicao(int selectedRow) {
        // Pega os dados atuais da linha selecionada
        String codigo = (String) tableModel.getValueAt(selectedRow, 1);
        String nome = (String) tableModel.getValueAt(selectedRow, 2);
        String modelo = (String) tableModel.getValueAt(selectedRow, 3);
        String fabricante = (String) tableModel.getValueAt(selectedRow, 4);
        String dataAquisicaoStr = (String) tableModel.getValueAt(selectedRow, 5);
        String tempoVidaEstimadoStr = String.valueOf(tableModel.getValueAt(selectedRow, 6));
        String localizacao = (String) tableModel.getValueAt(selectedRow, 7);
        String detalhes = (String) tableModel.getValueAt(selectedRow, 8);
        String manual = (String) tableModel.getValueAt(selectedRow, 9);

        // Abre um dialogo para edição
        String newCodigo = JOptionPane.showInputDialog(this, "Digite o Código:", codigo);
        if (newCodigo == null) return; // Cancelado

        String newNome = JOptionPane.showInputDialog(this, "Digite o Nome:", nome);
        if (newNome == null) return; // Cancelado

        String newModelo = JOptionPane.showInputDialog(this, "Digite o Modelo:", modelo);
        if (newModelo == null) return; // Cancelado

        String newFabricante = JOptionPane.showInputDialog(this, "Digite o Fabricante:", fabricante);
        if (newFabricante == null) return; // Cancelado

        String newDataAquisicaoStr = JOptionPane.showInputDialog(this, "Digite a Data de Aquisição (yyyy-MM-dd):", dataAquisicaoStr);
        if (newDataAquisicaoStr == null) return; // Cancelado

        String newTempoVidaEstimadoStr = JOptionPane.showInputDialog(this, "Digite o Tempo de Vida Estimado:", tempoVidaEstimadoStr);
        if (newTempoVidaEstimadoStr == null) return; // Cancelado

        String newLocalizacao = JOptionPane.showInputDialog(this, "Digite a Localização:", localizacao);
        if (newLocalizacao == null) return; // Cancelado

        String newDetalhes = JOptionPane.showInputDialog(this, "Digite os Detalhes:", detalhes);
        if (newDetalhes == null) return; // Cancelado

        String newManual = JOptionPane.showInputDialog(this, "Digite o Manual (link ou descrição):", manual);
        if (newManual == null) return; // Cancelado

        try {
            LocalDate newDataAquisicao = LocalDate.parse(newDataAquisicaoStr);
            int newTempoVidaEstimado = Integer.parseInt(newTempoVidaEstimadoStr);
            
            // Cria um objeto de máquina para atualização
            Maquina maquinaAtualizada = new Maquina(
                (String) tableModel.getValueAt(selectedRow, 0), // ID
                newCodigo, newNome, newModelo, newFabricante, newDataAquisicao, newTempoVidaEstimado,
                newLocalizacao, newDetalhes, newManual
            );

            // Atualiza a máquina no controlador
            maquinaController.updateMaquina(maquinaAtualizada);

            // Atualiza a tabela
            updateTableRow(selectedRow, maquinaAtualizada);
            JOptionPane.showMessageDialog(this, "Máquina atualizada com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar os dados: " + ex.getMessage());
        }
    }

    private void updateTableRow(int rowIndex, Maquina maquina) {
        tableModel.setValueAt(maquina.getCodigo(), rowIndex, 1);
        tableModel.setValueAt(maquina.getNome(), rowIndex, 2);
        tableModel.setValueAt(maquina.getModelo(), rowIndex, 3);
        tableModel.setValueAt(maquina.getFabricante(), rowIndex, 4);
        tableModel.setValueAt(maquina.getDataAquisicao().toString(), rowIndex, 5);
        tableModel.setValueAt(maquina.getTempoVidaEstimado(), rowIndex, 6);
        tableModel.setValueAt(maquina.getLocalizacao(), rowIndex, 7);
        tableModel.setValueAt(maquina.getDetalhes(), rowIndex, 8);
        tableModel.setValueAt(maquina.getManual(), rowIndex, 9);
    }

    // Método para obter os dados das máquinas
    public String getDados() {
        StringBuilder dados = new StringBuilder();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            dados.append("ID: ").append(tableModel.getValueAt(i, 0)).append(", ")
                 .append("Código: ").append(tableModel.getValueAt(i, 1)).append(", ")
                 .append("Nome: ").append(tableModel.getValueAt(i, 2)).append(", ")
                 .append("Modelo: ").append(tableModel.getValueAt(i, 3)).append(", ")
                 .append("Fabricante: ").append(tableModel.getValueAt(i, 4)).append(", ")
                 .append("Data de Aquisição: ").append(tableModel.getValueAt(i, 5)).append(", ")
                 .append("Tempo de Vida Estimado: ").append(tableModel.getValueAt(i, 6)).append(", ")
                 .append("Localização: ").append(tableModel.getValueAt(i, 7)).append(", ")
                 .append("Detalhes: ").append(tableModel.getValueAt(i, 8)).append(", ")
                 .append("Manual: ").append(tableModel.getValueAt(i, 9)).append("\n");
        }
        return dados.toString();
    }
}

