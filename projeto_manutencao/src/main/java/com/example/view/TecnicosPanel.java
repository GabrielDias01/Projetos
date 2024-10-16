package com.example.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.example.controllers.TecnicoController;
import com.example.models.Tecnico;

public class TecnicosPanel extends JPanel {
    // Atributos
    private TecnicoController tecnicoController;
    private JTable tecnicoTable;
    private DefaultTableModel tableModel;
    private JButton btnSalvarAlteracoes;
    private JButton btnCadastrarTecnico;
    private JButton btnDeletarTecnico;

    public TecnicosPanel() {
        super(new BorderLayout());

        // Inicializando o controlador
        tecnicoController = new TecnicoController();

        // Inicializando o model da tabela com as colunas
        tableModel = new DefaultTableModel(new Object[] {
                "ID", "Nome", "Especialidade", "Disponibilidade"
        }, 0);

        // Criar JTable com o model
        tecnicoTable = new JTable(tableModel);

        // Preenchendo a tabela com os técnicos do controlador
        List<Tecnico> tecnicos = tecnicoController.readTecnicos();
        for (Tecnico tecnico : tecnicos) {
            tableModel.addRow(new Object[] {
                    tecnico.getId(),
                    tecnico.getNome(),
                    tecnico.getEspecialidade(),
                    tecnico.getDisponibilidade()
            });
        }

        // Adicionando a JTable a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(tecnicoTable);
        this.add(scrollPane, BorderLayout.CENTER);

        // Criando painel inferior com botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarTecnico = new JButton("Cadastrar");
        btnSalvarAlteracoes = new JButton("Atualizar");
        btnDeletarTecnico = new JButton("Deletar");
        painelInferior.add(btnCadastrarTecnico);
        painelInferior.add(btnSalvarAlteracoes);
        painelInferior.add(btnDeletarTecnico);
        this.add(painelInferior, BorderLayout.SOUTH);

        // Adicionando ActionListeners para os botões
        addActionListeners();
    }

    private void addActionListeners() {
        btnCadastrarTecnico.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog(this, "Nome:");
            if (nome == null) return; // Cancelado

            String especialidade = JOptionPane.showInputDialog(this, "Especialidade:");
            if (especialidade == null) return; // Cancelado

            String disponibilidade = JOptionPane.showInputDialog(this, "Disponibilidade:");
            if (disponibilidade == null) return; // Cancelado

            try {
                Tecnico novoTecnico = new Tecnico(null, nome, especialidade, disponibilidade);
                Tecnico tecnicoCriado = tecnicoController.createTecnico(novoTecnico);

                if (tecnicoCriado != null) {
                    tableModel.addRow(new Object[] {
                            tecnicoCriado.getId(),
                            nome,
                            especialidade,
                            disponibilidade
                    });
                    JOptionPane.showMessageDialog(this, "Técnico cadastrado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao cadastrar técnico.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao preencher os dados: " + ex.getMessage());
            }
        });

        btnSalvarAlteracoes.addActionListener(e -> {
            int selectedRow = tecnicoTable.getSelectedRow();
            if (selectedRow != -1) {
                String id = String.valueOf(tableModel.getValueAt(selectedRow, 0));
                String nome = JOptionPane.showInputDialog(this, "Nome:", tableModel.getValueAt(selectedRow, 1));
                if (nome == null) return; // Cancelado

                String especialidade = JOptionPane.showInputDialog(this, "Especialidade:", tableModel.getValueAt(selectedRow, 2));
                if (especialidade == null) return; // Cancelado

                String disponibilidade = JOptionPane.showInputDialog(this, "Disponibilidade:", tableModel.getValueAt(selectedRow, 3));
                if (disponibilidade == null) return; // Cancelado

                try {
                    Tecnico tecnicoAtualizado = new Tecnico(id, nome, especialidade, disponibilidade);
                    tecnicoController.updateTecnico(tecnicoAtualizado);

                    tableModel.setValueAt(nome, selectedRow, 1);
                    tableModel.setValueAt(especialidade, selectedRow, 2);
                    tableModel.setValueAt(disponibilidade, selectedRow, 3);

                    JOptionPane.showMessageDialog(this, "Alterações salvas com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar alterações: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um técnico para salvar alterações.");
            }
        });

        btnDeletarTecnico.addActionListener(e -> {
            int selectedRow = tecnicoTable.getSelectedRow();
            if (selectedRow != -1) {
                String[] options = { "Sim", "Não" };
                int confirm = JOptionPane.showOptionDialog(
                        this,
                        "Tem certeza que deseja deletar este técnico?",
                        "Confirmar Exclusão",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        options[1]);

                if (confirm == 0) {
                    String id = String.valueOf(tableModel.getValueAt(selectedRow, 0));
                    tecnicoController.deleteTecnico(id);
                    tableModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(this, "Técnico deletado com sucesso!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um técnico para deletar.");
            }
        });
    }

    // Método para obter os dados dos técnicos
    public String getDados() {
        StringBuilder dados = new StringBuilder();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            dados.append("ID: ").append(tableModel.getValueAt(i, 0)).append(", ");
            dados.append("Nome: ").append(tableModel.getValueAt(i, 1)).append(", ");
            dados.append("Especialidade: ").append(tableModel.getValueAt(i, 2)).append(", ");
            dados.append("Disponibilidade: ").append(tableModel.getValueAt(i, 3)).append("\n");
        }
        return dados.toString();
    }
}
