package org.example;

import org.example.controller.CategoriaController;
import org.example.controller.TransacaoController;
import org.example.controller.UsuarioController;
import org.example.dao.CategoriaDAO;
import org.example.dao.JPAImpl.CategoriaJPA;
import org.example.model.Categoria;
import org.example.view.TelaLogin;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Categoria categoria = new Categoria(1, 1, "nome", "descricao");

        CategoriaDAO cjpa = new CategoriaJPA();
        cjpa.adicionarCategoria(categoria);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.setVisible(true);
            }
        });


    }
}