package br.com.dias;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class FuncionarioController {
    private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

    public FuncionarioController() {

        funcionarios = new ArrayList<>();
    }

    public void addFuncionario() {

        String nome = JOptionPane.showInputDialog("Digite o Nome do Funcionario");
        int idade = Integer.parseInt(JOptionPane.showInputDialog("Digite o Endereço do Funcionario"));
        double salario = Double.parseDouble(JOptionPane.showInputDialog("Digite o Salario"));
        Funcionario funcionario = new Funcionario(nome, idade, salario);
        funcionarios.add(funcionario);
    }

    // Método Listar todos os funcionarios
    public void listarFuncionarios() {
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.toString());
        }

    }

    // Buscar funcionario pelo nome
    public void buscarFuncionario() {
        String nome = JOptionPane.showInputDialog("Digite o nome do funcionario a ser buscado");
        try {
            boolean encontrado = false;
            for (Funcionario funcionario : funcionarios) {
                if (funcionario.getNome().equalsIgnoreCase(nome)) {
                    System.out.println(funcionario.toString());
                    encontrado = true;
                }
            }
            if (!encontrado) {
                throw new Exception("Funcionario nao encontrado");

            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    public void removerFuncionario() {
        String nome = JOptionPane.showInputDialog("Digite o nome do funcionario a ser buscado pra remover");
        try {
            boolean encontrado = false;
            for (Funcionario funcionario : funcionarios) {
                if (funcionario.getNome().equalsIgnoreCase(nome)) {
                    funcionarios.remove(funcionario);
                    encontrado = true;
                    System.out.println("Funcionario removido");
                    break;
                }
            }
            if (!encontrado) {
                throw new Exception("Funcionario nao encontrado");

            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    // Calculo de média salarial

    public void calculoMediaSalario(){
        double mediaSalarios = 0;
        if (funcionarios.size()==0) {
            System.out.println("Lista Vazia");
        }else{
            for (Funcionario funcionario : funcionarios) {
                mediaSalarios += funcionario.getSalario();
            }
            mediaSalarios /= funcionarios.size();
            System.out.println("Media dos salarios dos funcionarios: " + mediaSalarios);
        }
    }

}


