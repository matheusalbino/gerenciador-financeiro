package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class sistemaFinanceiroUIv1 extends JFrame implements ActionListener {

    JLabel jLabel1 = new JLabel("Sistema Financeiro:");
    JLabel jLabel2 = new JLabel("Sistema destinado para registros financeiros.");

    JTextField txtValor = new JTextField();
    JTextField txtData = new JTextField();

    JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Receita", "Despesa"});
    JComboBox<String> comboCategoria = new JComboBox<>(new String[]{"Comida", "Transporte", "Saúde", "Lazer", "Outros"});

    JButton btnRegistrar = new JButton("Registrar ");

    JTextArea txtDescricao = new JTextArea(5, 20);
    JTextArea txtResultados = new JTextArea(10, 30);

    JPanel painelBotoes = new JPanel(new GridLayout(5, 2,10,10));
    JPanel headerPainel = new JPanel(new GridLayout(2,1));
    JPanel painelEsquerdo = new JPanel(new BorderLayout(60, 20));
    JPanel painelInferior = new JPanel(new BorderLayout());
    JPanel painelInferiorBotao = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Centraliza o botão



    public sistemaFinanceiroUIv1() {

        setTitle("Sistema Financeiro");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(headerPainel, BorderLayout.NORTH);
        //painelEsquerdo.setBorder(BorderFactory.createEmptyBorder(60, 40, 20, 120));
        setResizable(false);


        jLabel1.setFont(new Font("Arial", Font.BOLD, 24));
        headerPainel.add(jLabel1 );

        jLabel2.setFont(new Font("Arial", Font.ITALIC,14));
        headerPainel.add(jLabel2);


        add(painelBotoes, BorderLayout.CENTER);
        add(painelEsquerdo, BorderLayout.EAST);

        painelBotoes.add(new JLabel("Tipo:"));
        painelBotoes.add(comboTipo);

        painelBotoes.add(new JLabel("Categoria:"));
        painelBotoes.add(comboCategoria);

        painelBotoes.add(new JLabel("Valor:"));
        txtValor.setPreferredSize(new Dimension(30, 25));
        painelBotoes.add(txtValor);


        painelBotoes.add(new JLabel("Data:"));
        painelBotoes.add(txtData);

        painelBotoes.add(new JLabel("Descrição:"));
        painelBotoes.add(txtDescricao);

        //add(painelInferior, BorderLayout.SOUTH);

        txtResultados.setFont(new Font("Arial", Font.PLAIN, 12)); // Define uma fonte menor
        painelInferior.add(txtResultados, BorderLayout.WEST); // Adiciona a label no canto inferior esquerdo

        btnRegistrar.setPreferredSize(new Dimension(120, 30)); // Reduz o tamanho do botão
        painelInferiorBotao.add(btnRegistrar);
        painelInferior.add(painelInferiorBotao, BorderLayout.CENTER);

        //btnRegistrar.addActionListener(e -> registrarTransacao());

        setVisible(true);
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sistemaFinanceiroUIv1().setVisible(true);
            }
        });

    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
