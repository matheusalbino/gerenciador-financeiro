package org.example.dao;

import org.example.model.Categoria;

import java.util.List;

public interface CategoriaDAO {
    void adicionarCategoria(Categoria categoria);
    void removerCategoria(Categoria categoria);
    void editarCategoria(Categoria categoria, String nome, String descricao);
    List<Categoria> listarCategoriasDeUsuario(int id);
    Categoria buscarCategoriaPorNome(String nomeCategoria, int idUsuario);
}
