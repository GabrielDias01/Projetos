package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Exercicio3 {

    public void exemplo() {
        double maxPrice = Double.MIN_VALUE;
        double minPrice = Double.MAX_VALUE;
        double totalPrice = 0.0;
        int count = 0;

        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nome, preco FROM produtos");

            while (rs.next()) {
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                System.out.println("Produto: " + nome + ", Preço: " + preco);

                // Atualiza o preço máximo e mínimo
                if (preco > maxPrice) {
                    maxPrice = preco;
                }
                if (preco < minPrice) {
                    minPrice = preco;
                }

                // Acumula o preço total e conta os produtos
                totalPrice += preco;
                count++;
            }

            // Exibe o produto mais caro e o mais barato
            if (count > 0) {
                System.out.println("Produto mais caro: " + maxPrice);
                System.out.println("Produto mais barato: " + minPrice);
                System.out.println("Média de preços: " + (totalPrice / count));
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
