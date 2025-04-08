package org.example.service;

import org.example.model.Categoria;

import java.util.List;

public interface CategoriaService {
    void adicionarCategoria(Categoria categoria);
    void removerCategoria(String nomeCategoria, int idUsuario);
    void editarCategoria(String nomeCategoria, int idUsuario, String nome, String descricao);
    List<Categoria> listarCategoriasDeUsuario(int idUsuario);
    Categoria buscarCategoriaPorNome(String nomeCategoria, int idUsuario);
    int ultimaCategoria(int idUsuario);
    int proximoIdCategoria(int idUsuario);
}
