package com.example;

import java.util.Scanner;

public class Exercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] notas = new double[4];
        double media = 0;
        

        for (int i = 0; i < 4; i++) {
            System.out.print("Digite a nota da disciplina " + (i + 1) + ": ");
            notas[i] = scanner.nextDouble();
            media += notas[i];
        }

        media = media / 4;

        if (notas[0] >= 9 && notas[1] >= 9 && notas[2] >= 9 && notas[3] >= 9) {
            media = (media * 1.1 > 10 ? media = 10 : media * 1.1);
        }

        if (media >= 7) {
            System.out.println("Status: Aprovado");
        } else if (media >= 5) {
            System.out.println("Status: Recuperação");
        } else {
            System.out.println("Status: Reprovado");
        }
        System.out.printf("A média final é: %.2f%n", media);

        scanner.close();
    }
}
