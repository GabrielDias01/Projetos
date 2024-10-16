package com.example.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.models.Manutencao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ManutencaoAPI {

    // Método para obter a lista de manutenções
    public static List<Manutencao> getManutencoes() {
        String json = ApiConnection.getData("historicoManutencao");
        List<Manutencao> manutencoes = new ArrayList<>();

        if (json != null) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Manutencao manutencao = jsonToManutencao(jsonObject);
                    manutencoes.add(manutencao);
                }
            } catch (JSONException e) {
                e.printStackTrace(); // Log de erros no parsing
            }
        }
        return manutencoes;
    }

    // Método para converter JSONObject em Manutencao
    private static Manutencao jsonToManutencao(JSONObject jsonObject) {
        String id = jsonObject.optString("id", "desconhecido"); // Usa optString para evitar exceções
        String maquinaId = jsonObject.optString("maquinaId", "desconhecido");
        LocalDate data = LocalDate.parse(jsonObject.optString("data", LocalDate.now().toString()), 
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String tipo = jsonObject.optString("tipo", "desconhecido");
        String pecasTrocadas = jsonObject.optString("pecasTrocadas", "nenhuma");
        int tempoDeParada = jsonObject.optInt("tempoDeParada", 0); // Usa optInt para evitar exceções
        String tecnicoID = jsonObject.optString("tecnicoId", "desconhecido"); // Verifique se a chave é "tecnicoId" ou "tecnicoID"
        String observacoes = jsonObject.optString("observacoes", "sem observações");

        return new Manutencao(id, maquinaId, data, tipo, pecasTrocadas, tempoDeParada, tecnicoID, observacoes);
    }

    // Método para criar uma nova manutenção
    public static Manutencao createManutencao(Manutencao manutencao) {
        JSONObject jsonObject = manutencaoToJson(manutencao);
        String response = ApiConnection.postData("historicoManutencao", jsonObject.toString());

        if (response != null) {
            try {
                JSONObject createdManutencaoJson = new JSONObject(response);
                return jsonToManutencao(createdManutencaoJson);
            } catch (JSONException e) {
                e.printStackTrace(); // Log de erros no parsing
            }
        }
        return null; // Retorna null em caso de falha
    }

    // Método para atualizar uma manutenção existente
    public static String updateManutencao(Manutencao manutencao) {
        JSONObject jsonObject = manutencaoToJson(manutencao);
        return ApiConnection.putData("historicoManutencao/" + manutencao.getId(), jsonObject.toString());
    }

    // Método para deletar uma manutenção pelo ID
    public static String deleteManutencao(String id) {
        return ApiConnection.deleteData("historicoManutencao/" + id);
    }

    // Método para converter Manutencao em JSONObject
    private static JSONObject manutencaoToJson(Manutencao manutencao) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("maquinaId", manutencao.getMaquinaID());
        jsonObject.put("data", manutencao.getData().toString());
        jsonObject.put("tipo", manutencao.getTipo());
        jsonObject.put("pecasTrocadas", manutencao.getPecasTrocadas());
        jsonObject.put("tempoDeParada", manutencao.getTempoDeParada());
        jsonObject.put("tecnicoId", manutencao.getTecnicoID()); // Verifique se deve ser "tecnicoId" ou "tecnicoID"
        jsonObject.put("observacoes", manutencao.getObservacoes());
        return jsonObject;
    }
}
