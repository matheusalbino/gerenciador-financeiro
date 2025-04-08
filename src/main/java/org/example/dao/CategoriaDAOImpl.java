package org.example.dao;

import org.example.model.Categoria;
import org.example.singleton.CategoriaSingleton;
import org.example.singleton.UsuarioSingleton;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO {

    @Override
    public void adicionarCategoria(Categoria categoria) {
        CategoriaSingleton.getInstance().getCategorias().add(categoria);
    }

    @Override
    public void removerCategoria(Categoria categoria) {
        CategoriaSingleton.getInstance().getCategorias().remove(categoria);
    }

    @Override
    public void editarCategoria(Categoria categoria, String nome, String descricao) {
        if (nome != null){
            if (buscarCategoriaPorNome(nome, categoria.getUserid()) != null){
                throw new IllegalArgumentException("Categoria ja existe pro usuário");
            }
            categoria.setNome(nome);
        }
        if (descricao != null){
            categoria.setDescricao(descricao);
        }
    }

    @Override
    public List<Categoria> listarCategoriasDeUsuario(int userid) {
        List<Categoria> categoriasDoUsuario = new ArrayList<>();
        for (Categoria categoria : CategoriaSingleton.getInstance().getCategorias()) {
            if (categoria.getUserid() == userid) {
                categoriasDoUsuario.add(categoria);
            }
        }

        return categoriasDoUsuario;
    }

    @Override
    public Categoria buscarCategoriaPorNome(String nomeCategoria, int idUsuario) {

        List<Categoria> categoriasDoUsuario = listarCategoriasDeUsuario(idUsuario);

        for (Categoria categoria : categoriasDoUsuario) {
            if (categoria.getNome().equals(nomeCategoria)) {
                return categoria;
            }
        }

        return null;
    }

}
