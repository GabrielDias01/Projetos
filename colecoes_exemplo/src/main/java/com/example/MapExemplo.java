package com.example;

import java.util.Map;
import java.util.HashMap;

public class MapExemplo {
    private Map<String, Integer> nomeIdade;

    public MapExemplo() {
        nomeIdade = new HashMap<>();
    }
    public void adicionarNomeIdade() {
        nomeIdade.put("Maria", 23);
    }
    public void listarNomeIdades() {
        for (String nome : nomeIdade.keySet()) {
            System.out.println(nome + " "+ );
        }
    }
}
