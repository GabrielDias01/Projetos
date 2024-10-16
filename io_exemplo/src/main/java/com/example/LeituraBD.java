package com.example;

import java.sql.*;

public class LeituraBD {
    public void exemplo() {

        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "postgres");
            // Tradutor de sql
            Statement stmt = con.createStatement();
            // Armazenar result
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id")
                        + ", NOME: " + rs.getString("nome")
                        + ", IDADE: " + rs.getInt("idade"));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
