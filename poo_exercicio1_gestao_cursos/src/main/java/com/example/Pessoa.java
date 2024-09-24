package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Pessoa {

    // Atributos

    private String nome;
    private String cpf;

    // Métodos

    public String exibirInfo() {
        return " NOME: " + nome + " CPF: " + cpf;
    }
}
