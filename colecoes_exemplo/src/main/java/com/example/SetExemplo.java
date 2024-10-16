package com.example;

import java.util.HashSet;
import java.util.Set;

public class SetExemplo {
    private Set<String> nomes;

    public SetExemplo() {
        nomes = new HashSet<>();
    }

    public void adicionarNome(String nome) {
        nomes.add(nome);
        System.out.println(nomes.hashCode());

    }

    public void listarNome() {
        for (String nome : nomes) {
            System.out.println(nome);
        }
    }

    public void removerNome(String nome) {
        nomes.remove(nome);
        System.out.println("Nome removido com sucesso!");

    }

    public void modificarNome(String nome, String nomeNovo) {
        nomes.remove(nome);
        nomes.add(nomeNovo);
        System.out.println("Nome mudado de " + nome + " para " + nomeNovo);

    }
}
