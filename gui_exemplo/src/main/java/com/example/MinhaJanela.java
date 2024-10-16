package com.example;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MinhaJanela extends JFrame {

    public MinhaJanela() {
        super("Minha Janela");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 300);

        JPanel painel = new JPanel();
        this.add(painel);

        JButton botao = new JButton();
        painel.add(botao);
        botao.setText("Clique aqui");

        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Botão Clicado");
            }
        });

        this.setVisible(true);
    }

}
