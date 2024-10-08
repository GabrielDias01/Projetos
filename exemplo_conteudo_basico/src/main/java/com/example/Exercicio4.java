package com.example;

import java.util.Scanner;

public class Exercicio4 {

    int numero = -1;

    Scanner sc = new Scanner(System.in);

    public long fatorial(int numero) {
        if (numero == 0 || numero == 1) {
            return 1;
        } else {
            return numero * fatorial(numero - 1);
        }
    }

    public void calculadora() throws Exception {

        while (true) {
            System.out.println("Digite um nº");
            numero = sc.nextInt();
            if (numero < 0) {
                throw new Exception("Não é permitido nº Negativo");
            }
            try {
                long resultado = fatorial(numero);
                System.out.println("O Fatorial é" + resultado);
            } catch (Exception e) {
                System.err.println(e);
            }
        }

    }
}