package com.example.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiConnection {
    // URL base da API
    private static final String API_URL = "http://localhost:3000/";

    // Método para realizar uma requisição GET
    public static String getData(String endpoint) {
        try {
            // Cria uma URL completa com o endpoint
            URL url = new URL(API_URL + endpoint);
            
            // Abre uma conexão HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Define o método de requisição como GET
            connection.setRequestMethod("GET");

            // Lê a resposta da requisição
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            // Processa o conteúdo da resposta
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            // Fecha o leitor e a conexão
            in.close();
            connection.disconnect();

            // Retorna o conteúdo da resposta como string
            return content.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Retorna null em caso de erro
        }
    }

    // Método para realizar uma requisição POST com um payload JSON
    public static String postData(String endpoint, String jsonPayload) {
        try {
            // Cria uma URL completa com o endpoint
            URL url = new URL(API_URL + endpoint);
            
            // Abre uma conexão HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Define o método de requisição como POST
            connection.setRequestMethod("POST");
            
            // Define os headers da requisição
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true); // Permite envio de dados no corpo da requisição

            // Envia o JSON payload na requisição
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.writeBytes(jsonPayload);
                wr.flush();
            }

            // Lê a resposta da requisição
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            // Processa o conteúdo da resposta
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            // Fecha o leitor e a conexão
            in.close();
            connection.disconnect();

            // Retorna o conteúdo da resposta como string
            return content.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Retorna null em caso de erro
        }
    }

    // Método para realizar uma requisição PUT com um payload JSON
    public static String putData(String endpoint, String jsonPayload) {
        try {
            // Cria uma URL completa com o endpoint
            URL url = new URL(API_URL + endpoint);
            
            // Abre uma conexão HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Define o método de requisição como PUT
            connection.setRequestMethod("PUT");
            
            // Define os headers da requisição
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true); // Permite envio de dados no corpo da requisição

            // Envia o JSON payload na requisição
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.writeBytes(jsonPayload);
                wr.flush();
            }

            // Lê a resposta da requisição
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            // Processa o conteúdo da resposta
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            // Fecha o leitor e a conexão
            in.close();
            connection.disconnect();

            // Retorna o conteúdo da resposta como string
            return content.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Retorna null em caso de erro
        }
    }

    // Método para realizar uma requisição DELETE
    public static String deleteData(String endpoint) {
        try {
            // Cria uma URL completa com o endpoint
            URL url = new URL(API_URL + endpoint);
            
            // Abre uma conexão HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Define o método de requisição como DELETE
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Accept", "application/json");

            // Lê a resposta da requisição
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            // Processa o conteúdo da resposta
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            // Fecha o leitor e a conexão
            in.close();
            connection.disconnect();

            // Retorna o conteúdo da resposta como string
            return content.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Retorna null em caso de erro
        }
    }
}