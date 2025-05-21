package org.example.controller;

import org.example.model.Categoria;
import org.example.model.Usuario;
import org.example.service.CategoriaService;
import org.example.service.CategoriaServiceImpl;
import org.example.service.UsuarioService;
import org.example.service.UsuarioServiceImpl;
import org.example.util.ValidarEntrada;

import javax.swing.*;
import java.util.List;

public class CategoriaController {
    private final CategoriaService categoriaService = new CategoriaServiceImpl();
    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    public void cadastrarCategoria(String nome, String descricao){
        try {
            Usuario usuarioLogado = usuarioService.getUsuarioLogado();
            int idCategoria = categoriaService.proximoIdCategoria(usuarioLogado.getId());

            ValidarEntrada.validarStringNuloOuVazia(nome, "Nome vazio não permitido");

            Categoria categoria = new Categoria(idCategoria, usuarioLogado.getId(), nome, descricao);
            categoriaService.adicionarCategoria(categoria);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removerCategoria(String nomeCategoria){
        int idUsuario = usuarioService.getUsuarioLogado().getId();
        ValidarEntrada.validarStringVazia(nomeCategoria, "Nome de categoria inválido");

        try {
            categoriaService.removerCategoria(nomeCategoria, idUsuario);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarCategoria(String nomeCategoria, String novoNome, String novaDescricao){

        ValidarEntrada.validarStringNuloOuVazia(novoNome, "Nome não pode ser vazio");
        ValidarEntrada.validarStringNuloOuVazia(nomeCategoria, "Nome de categoria inválido");
        int idUsuario = usuarioService.getUsuarioLogado().getId();

        categoriaService.editarCategoria(nomeCategoria, idUsuario, novoNome, novaDescricao);
    }

    public List<Categoria> listarCategoriasDoUsuario(){
        int idUsuario = usuarioService.getUsuarioLogado().getId();
        return categoriaService.listarCategoriasDeUsuario(idUsuario);
    }

    public Categoria buscarCategoria (String nomeCategoria){
        int idUsuario = usuarioService.getUsuarioLogado().getId();
        ValidarEntrada.validarStringNuloOuVazia(nomeCategoria, "Nome categoria inválido");
        return categoriaService.buscarCategoriaPorNome(nomeCategoria, idUsuario);
    }

}
