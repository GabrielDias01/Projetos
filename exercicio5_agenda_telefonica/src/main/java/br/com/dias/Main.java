package br.com.dias;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContatoController agenda = new ContatoController(5);
        int operacao = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\n----- Agenda de Contatos -----\n");
            System.out.println("1 - Adicionar Contato");
            System.out.println("2 - Listar Contato");
            System.out.println("3 - Buscar Contato");
            System.out.println("4 - Sair");
            try {
                operacao = sc.nextInt();
                switch (operacao) {
                    case 1:
                        System.out.println("Nome");
                        String nome = sc.next();
                        System.out.println("Endereço");
                        String endereco = sc.next();
                        System.out.println("Email");
                        String email = sc.next();
                        System.out.println("Telefone");
                        String telefone = sc.next();
                        Contato contato = new Contato(nome, email, endereco, telefone);
                        agenda.addContato(contato);
                        break;
                    case 2:
                        agenda.listarContato();
                        break;
                    case 3:
                        System.out.println("Digite o nome a ser Buscado");
                        String nomeBuscado = sc.next();
                        agenda.buscarNome(nomeBuscado);
                        break;
                    case 4:
                        System.out.println("Saindo...");
                        break;
                    default:
                    System.out.println("Digite um nº Valido");
                        break;
                }
            } catch (Exception e) {

            }
        } while (operacao != 4);
    }

  
}