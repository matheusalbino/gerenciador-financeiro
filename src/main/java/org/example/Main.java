package org.example;

import org.example.dao.CategoriaDAO;
import org.example.dao.JPAImpl.CategoriaJPA;
import org.example.dao.JPAImpl.TransacaoJPA;
import org.example.dao.JPAImpl.UsuarioJPA;
import org.example.dao.TransacaoDAO;
import org.example.dao.UsuarioDAO;
import org.example.model.Categoria;
import org.example.model.SessaoUsuario;
import org.example.model.Transacao;
import org.example.model.Usuario;
import org.example.model.enums.TipoTransacao;
import org.example.view.TelaLogin;


import javax.swing.*;
import java.time.Instant;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Categoria categoria = new Categoria(1, 1, "nome", "descricao");
        CategoriaDAO categoriaDAO = new CategoriaJPA();
        //categoriaDAO.adicionarCategoria(categoria);

        Date data = Date.from(Instant.now());

        Transacao transacao = new Transacao(1, 1, categoria, 1, data, "description", TipoTransacao.DESPESA);
        TransacaoDAO transacaoDAO = new TransacaoJPA();
        //transacaoDAO.adicionarTransacao(transacao);

        Usuario usuario = new Usuario(1, "rodrigo", "rato");
        UsuarioDAO usuarioDAO = new UsuarioJPA();
        //usuarioDAO.cadastrarUsuario(usuario);

        SessaoUsuario sessaoUsuario = new SessaoUsuario(0, null);
        usuarioDAO.logarUsuario(usuario);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.setVisible(true);
            }
        });


    }
}