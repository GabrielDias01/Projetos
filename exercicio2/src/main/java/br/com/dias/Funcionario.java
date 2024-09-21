package br.com.dias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class Funcionario {
    private String nome;
    private int idade;
    private double salario;

    @Override
    public String toString() {
        return "Nome: " + nome + ", Idade: " + idade + ", Salário: " + salario;
    }
}
