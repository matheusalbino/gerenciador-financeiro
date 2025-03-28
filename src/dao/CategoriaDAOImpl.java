package dao;

import model.Categoria;
import singleton.CategoriaSingleton;

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
            if (categoria.getNome() == nomeCategoria) {
                return categoria;
            }
        }

        return null;
    }

}
