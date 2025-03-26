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
    public void editarCategoria(Categoria categoria) {
        Categoria categoriaAEditar = buscarCategoriaPorId(categoria.getId(), categoria.getUserid());
        categoriaAEditar.setNome(categoria.getNome());
        categoriaAEditar.setDescricao(categoria.getDescricao());
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
    public Categoria buscarCategoriaPorId(int idCategoria, int idUsuario) {
        List<Categoria> categoriasDoUsuario = listarCategoriasDeUsuario(idUsuario);
        for (Categoria categoria : categoriasDoUsuario) {
            if (categoria.getId() == idCategoria) {
                return categoria;
            }
        }

        return null;
    }
}
