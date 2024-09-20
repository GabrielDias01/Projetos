package com.example;

import java.util.Scanner;

public class Exercicio3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean aberto = true;
        try {

            while (aberto) {

                System.out.println("Deseja usar qual Operação ?");
                System.out.println("1 - Soma \n2 - Subtração \n3 - Multiplicação \n4 - Divisão \n5 - Raiz Quadrada");

                int operacao = scanner.nextInt();
                System.out.println("Digite o primeiro número:");
                double valor1 = scanner.nextDouble();
                System.out.println("Digite o segundo número:");
                double valor2 = scanner.nextDouble();

                double resultado = 0;

                switch (operacao) {
                    case 1:
                        resultado = valor1 + valor2;
                        break;
                    case 2:
                        resultado = valor1 - valor2;
                        break;
                    case 3:
                        resultado = valor1 * valor2;
                        break;
                    case 4:
                        if (valor2 == 0) {
                            System.out.println("Não é possível realizar a divisão por zero.");
                            aberto = false;
                            break;
                        }
                        resultado = valor1 / valor2;
                        break;
                    case 5:
                        resultado = Math.sqrt(valor1);
                        break;
                    default:
                        System.out.println("Operação inválida.");
                        aberto = false;
                        break;
                }
                System.out.println("Resultado: " + resultado);
            }

        } catch (Exception e) {

        }

    }

}
