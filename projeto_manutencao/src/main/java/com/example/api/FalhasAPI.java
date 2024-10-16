package com.example.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.models.Falha; // Certifique-se de que o pacote está correto

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FalhasAPI{

  public static List<Falha> getFalhas() {
    String json = ApiConnection.getData("falhas");
    List<Falha> falhas = new ArrayList<>();

    if (json != null) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Usando optString para evitar JSONException
                String id = jsonObject.getString("id");
                String maquinaId = jsonObject.optString("maquinaId", "desconhecido"); // Usando optString
                LocalDate data = LocalDate.parse(jsonObject.getString("data"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String problema = jsonObject.getString("problema");
                String prioridade = jsonObject.getString("prioridade");
                String operador = jsonObject.getString("operador");

                Falha falha = new Falha(id, maquinaId, data, problema, prioridade, operador);
                falhas.add(falha);
            }
        } catch (JSONException e) {
            e.printStackTrace(); // Log de erros no parsing
        }
    }
    return falhas;
}


   // Método para criar uma nova falha no servidor
public static Falha createFalha(Falha falha) {
    // Cria um objeto JSON com os dados da falha
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("maquinaId", falha.getMaquinaID());
    jsonObject.put("data", falha.getData().toString()); // Converte LocalDate para String
    jsonObject.put("problema", falha.getProblema());
    jsonObject.put("prioridade", falha.getPrioridade());
    jsonObject.put("operador", falha.getOperador());

    // Converte o objeto JSON para string para enviar no payload da requisição
    String jsonPayload = jsonObject.toString();

    // Faz uma requisição POST para o endpoint "falhas" com o payload JSON
    String responseJson = ApiConnection.postData("falhas", jsonPayload);
    
    // Verifica se a resposta não é nula e processa
    if (responseJson != null) {
        JSONObject response = new JSONObject(responseJson);
        // Aqui você pode verificar se o ID está presente para confirmar que a criação foi bem-sucedida
        if (response.has("id")) {
            // Retorna a falha criada como um objeto
            return new Falha(
                response.getString("id"),
                response.getString("maquinaId"),
                LocalDate.parse(response.getString("data")), // Converte para LocalDate
                response.getString("problema"),
                response.getString("prioridade"),
                response.getString("operador")
            );
        }
    }
    return null; // Retorna nulo se houver algum erro
}

// Método para atualizar uma falha existente no servidor
public static Falha updateFalha(Falha falha) {
    // Cria um objeto JSON com os dados atualizados da falha
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("maquinaId", falha.getMaquinaID());
    jsonObject.put("data", falha.getData().toString()); // Converte LocalDate para String
    jsonObject.put("problema", falha.getProblema());
    jsonObject.put("prioridade", falha.getPrioridade());
    jsonObject.put("operador", falha.getOperador());

    // Converte o objeto JSON para string para enviar no payload da requisição
    String jsonPayload = jsonObject.toString();

    // Faz uma requisição PUT para atualizar a falha com o ID especificado
    String responseJson = ApiConnection.putData("falhas/" + falha.getId(), jsonPayload);

    // Verifica se a resposta não é nula e processa
    if (responseJson != null) {
        JSONObject response = new JSONObject(responseJson);
        // Aqui você pode verificar se o ID está presente para confirmar que a atualização foi bem-sucedida
        if (response.has("id")) {
            // Retorna a falha atualizada como um objeto
            return new Falha(
                response.getString("id"),
                response.getString("maquinaId"),
                LocalDate.parse(response.getString("data")), // Converte para LocalDate
                response.getString("problema"),
                response.getString("prioridade"),
                response.getString("operador")
            );
        }
    }
    return null; // Retorna nulo se houver algum erro
}

// Método para deletar uma falha no servidor com base no ID
public static String deleteFalha(String id) {
    // Faz uma requisição DELETE para o endpoint "falhas/{id}"
    return ApiConnection.deleteData("falhas/" + id);
}


  
}