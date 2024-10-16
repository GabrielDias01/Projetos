package com.example;

public class Calculadora {
    public double soma(double a, double b) {
        return a + b;
    }
    
    public double subtracao(double a, double b) {
        return a - b;
    }
    
    public double multiplicacao(double a, double b) {
        return a * b;
    }
    
    public double divisao(double a, double b) throws Exception {
        if (b == 0) {
            throw new Exception("Não dividiras por Zero!");
        }
        return a / b;
    }
}
