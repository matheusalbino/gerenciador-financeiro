package org.example;

import org.example.controller.CategoriaController;
import org.example.controller.TransacaoController;
import org.example.controller.UsuarioController;
import org.example.view.TelaLogin;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        UsuarioController usuarioController = new UsuarioController();
        CategoriaController categoriaController = new CategoriaController();
        TransacaoController transacaoController = new TransacaoController();

        // Usuario e inserções teste
        usuarioController.cadastrarUsuario("a", "a");
        usuarioController.Login("a", "a");

        categoriaController.cadastrarCategoria("Mercado", "Compras no mercado");
        categoriaController.cadastrarCategoria("Internet", "Conta de internet");
        categoriaController.cadastrarCategoria("Conta Luz", "Conta de luz de casa");
        categoriaController.cadastrarCategoria("Salario", "Pagamento do mês");

        transacaoController.cadastrarTransacao("Mercado", "300", "06/04/2025", "Compra Carrefour para 15 dias", "Despesa");
        transacaoController.cadastrarTransacao("Salario", "2500", "05/04/2025", "Pagamento salario do mes de abril", "Receita");
        transacaoController.cadastrarTransacao("Internet", "200", "08/04/2025", "Pagamento da internet abril", "Despesa");
        // ===================


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.setVisible(true);
            }
        });

    }
}