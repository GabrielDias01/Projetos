package com.example;

import java.io.BufferedReader;
import java.io.*;
import java.io.FileReader;

public class Exercicio4 {
    public Exercicio4() {
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("produtos.csv"));
            String linha = br.readLine();
            if (linha != null) {
                String colunas[] = linha.split(",");
                System.out.println("Cabeçalho");
                for (String coluna : colunas) {
                    System.out.print(coluna + " ");
                }
            }
            System.out.println("---Conteúdo---");
            while ((linha = br.readLine()) != null) {
                String colunas[] = linha.split(",");
                for (String coluna : colunas) {
                    System.out.print(coluna + " ");
                }
                System.out.println();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
