package com.example.api;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.models.Maquina; // Certifique-se de que o pacote está correto

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class MaquinaAPI {

    // Método para obter uma lista de máquinas do servidor
    public static List<Maquina> getMaquinas() {
        // Faz uma requisição GET para o endpoint "maquinas" e armazena a resposta JSON
        String json = ApiConnection.getData("maquinas");
        List<Maquina> maquinas = new ArrayList<>();

        // Verifica se a resposta JSON não é nula
        if (json != null) {
            // Converte a string JSON para um JSONArray
            JSONArray jsonArray = new JSONArray(json);

            // Itera sobre o JSONArray para criar objetos Maquina
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Cria um objeto Maquina com base nos dados do JSON
                Maquina maquina = new Maquina(
                        jsonObject.getString("id"),
                        jsonObject.getString("codigo"),
                        jsonObject.getString("nome"),
                        jsonObject.getString("modelo"),
                        jsonObject.getString("fabricante"),
                        LocalDate.parse(jsonObject.getString("dataAquisicao")),
                        jsonObject.getInt("tempoVidaEstimado"),
                        jsonObject.getString("localizacao"),
                        jsonObject.getString("detalhes"),
                        jsonObject.getString("manual"));

                // Adiciona a máquina à lista de máquinas
                maquinas.add(maquina);
            }
        }
        // Retorna a lista de máquinas
        return maquinas;
    }

    public static Maquina createMaquina(Maquina maquina) {
        // Cria um objeto JSON com os dados da máquina
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("codigo", maquina.getCodigo());
        jsonObject.put("nome", maquina.getNome());
        jsonObject.put("modelo", maquina.getModelo());
        jsonObject.put("fabricante", maquina.getFabricante());
        jsonObject.put("dataAquisicao", maquina.getDataAquisicao());
        jsonObject.put("tempoVidaEstimado", maquina.getTempoVidaEstimado());
        jsonObject.put("localizacao", maquina.getLocalizacao());
        jsonObject.put("detalhes", maquina.getDetalhes());
        jsonObject.put("manual", maquina.getManual());
    
        // Converte o objeto JSON para string para enviar no payload da requisição
        String jsonPayload = jsonObject.toString();
    
        // Faz uma requisição POST para o endpoint "maquinas" com o payload JSON
        String responseJson = ApiConnection.postData("maquinas", jsonPayload);
        
        // Verifica se a resposta não é nula e processa
        if (responseJson != null) {
            JSONObject response = new JSONObject(responseJson);
            // Aqui você pode verificar se o ID está presente para confirmar que a criação foi bem-sucedida
            if (response.has("id")) {
                // Retorna a máquina criada como um objeto
                return new Maquina(
                    response.getString("id"),
                    response.getString("codigo"),
                    response.getString("nome"),
                    response.getString("modelo"),
                    response.getString("fabricante"),
                    LocalDate.parse(response.getString("dataAquisicao")),
                    response.getInt("tempoVidaEstimado"),
                    response.getString("localizacao"),
                    response.getString("detalhes"),
                    response.getString("manual")
                );
            }
        }
        return null; // Retorna nulo se houver algum erro
    }
    

    // Método para atualizar uma máquina existente no servidor
    public static String updateMaquina(Maquina maquina) {
        // Cria um objeto JSON com os dados atualizados da máquina
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("codigo", maquina.getCodigo());
        jsonObject.put("nome", maquina.getNome());
        jsonObject.put("modelo", maquina.getModelo());
        jsonObject.put("fabricante", maquina.getFabricante());
        jsonObject.put("dataAquisicao", maquina.getDataAquisicao());
        jsonObject.put("tempoVidaEstimado", maquina.getTempoVidaEstimado());
        jsonObject.put("localizacao", maquina.getLocalizacao());
        jsonObject.put("detalhes", maquina.getDetalhes());
        jsonObject.put("manual", maquina.getManual());

        // Converte o objeto JSON para string para enviar no payload da requisição
        String jsonPayload = jsonObject.toString();

        // Faz uma requisição PUT para atualizar a máquina com o ID especificado
        return ApiConnection.putData("maquinas/" + maquina.getId(), jsonPayload);
    }

    // Método para deletar uma máquina no servidor com base no ID
    public static String deleteMaquina(String id) {
        // Faz uma requisição DELETE para o endpoint "maquinas/{id}"
        return ApiConnection.deleteData("maquinas/" + id);
    }
}