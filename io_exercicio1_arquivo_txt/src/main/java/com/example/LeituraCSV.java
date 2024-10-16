package com.example;

import java.io.BufferedReader;
import java.io.FileReader;

public class LeituraCSV {
    public void exemplo() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("dados.csv"));
            String linha = br.readLine();
            if (linha != null) {
                System.out.println("Cabeçalho");
                String colunas[] = linha.split(",");
                for (String coluna : colunas) {
                    System.out.println(" ");
                }
            }
            System.out.println("--- Conteúdo ---");
            while ((linha = br.readLine()) != null) {
                String colunas[] = linha.split(",");
                for (String coluna : colunas) {
                    System.out.println(coluna + " ");
                }
                System.out.println();
                System.out.println("-------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
