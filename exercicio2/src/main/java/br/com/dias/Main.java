package br.com.dias;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        FuncionarioController gerenciaFuncionario = new FuncionarioController();
        int operacao = 0;
        do {
            operacao = Integer.parseInt(JOptionPane.showInputDialog(
                    "Menu de Gerenciamento de Funcionários\n" +
                            "1 - Adicionar Funcionário\n" +
                            "2 - Listar Funcionários\n" +
                            "3 - Buscar Funcionário\n" +
                            "4 - Remover Funcionário\n" +
                            "5 - Calcular Média Salario\n" +
                            "6 - Sair\n" +
                            "Digite a opção desejada: "));
            switch (operacao) {
                case 1:
                    gerenciaFuncionario.addFuncionario();
                    break;
                case 2:
                    gerenciaFuncionario.listarFuncionarios();
                    break;
                case 3:
                    gerenciaFuncionario.buscarFuncionario();
                    break;
                case 4:
                    gerenciaFuncionario.removerFuncionario();
                    break;
                case 5:
                    gerenciaFuncionario.calculoMediaSalario();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                System.err.println("Digite um nº valido");
                    break;
            }
        } while (operacao != 6);
    }
}