package com.example;

import javax.swing.JPanel;
import javax.swing.JButton;

import javax.swing.JTextField;

import java.awt.*;

public class CalculadoraAvancada extends JPanel {
    JTextField displayAvancado;
    String[] botoes = {

            "^", "sqrt", "log", "!",
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "=", "+", "C"
    };

    public CalculadoraAvancada() {
        super(new BorderLayout());
        displayAvancado = new JTextField();
        displayAvancado.setHorizontalAlignment(JTextField.RIGHT);
        displayAvancado.setEditable(false);
        this.add(displayAvancado, BorderLayout.NORTH);

        // Criar um painel para os botoes
        JPanel painelBotoes = new JPanel(new GridLayout(5, 4, 3, 3));
        for (String textoBotoes : botoes) {
            JButton botao = new JButton(textoBotoes);
            botao.addActionListener(null);
            // Adicionar ação dos botoes
            painelBotoes.add(botao);
        }
        this.add(painelBotoes, BorderLayout.CENTER);
    }
}