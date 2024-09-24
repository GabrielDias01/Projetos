package com.example;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class Professor extends Pessoa {
    // Atributes

    private double salario;

    // Constructor

    public Professor(String nome, String cpf, Double salario) {
        super(nome,cpf);
        this.salario= salario;

    }

    // Methods para exibir INFO
    @Override
    public String exibirInfo() {
        super.exibirInfo();

        return "Salario: " + salario;
    }
}
