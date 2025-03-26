package service;

import dao.CategoriaDAO;
import dao.CategoriaDAOImpl;
import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import model.Categoria;

import java.util.List;

public class CategoriaServiceImpl implements CategoriaService{
    UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    private final CategoriaDAO categoriaDAO = new CategoriaDAOImpl();

    @Override
    public void adicionarCategoria(Categoria categoria) {
        if (categoriaDAO.buscarCategoriaPorId(categoria.getId(), categoria.getUserid()) != null){
            throw new IllegalArgumentException("Categoria ja existe pro usuário");
        }
        // verificar se tem categoria com o mesmo nome

        categoriaDAO.adicionarCategoria(categoria);
    }

    @Override
    public void removerCategoria(int idCategoria, int idUsuario) {
        throw new IllegalArgumentException("Ainda não implementado");
        // buscar categoria, checar se categoria existe naquele usuario
        // pegar a categoria e entregar ao method da camada dao

    }

    @Override
    public void editarCategoria(int idCategoria, int idUsuario) {
        // buscar
        Categoria categoria = buscarCategoriaPorId(idCategoria, idUsuario);

        if (categoria == null){
            throw new IllegalArgumentException("Categoria não encontrada");
        }

        categoriaDAO.editarCategoria(categoria);
    }

    @Override
    public List<Categoria> buscarCategoriasPorUserID(int idUsuario) {
        if (usuarioDAO.buscarUsuarioPorId(idUsuario) == null){
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        List<Categoria> categorias = categoriaDAO.listarCategoriasDeUsuario(idUsuario);

        if (categorias == null || categorias.size() <= 0){
            throw new IllegalArgumentException("Usuario sem categorias");
        }

        return categorias;
    }

    @Override
    public Categoria buscarCategoriaPorId(int idCategoria, int idUsuario) {
        if (usuarioDAO.buscarUsuarioPorId(idUsuario) == null){
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        Categoria categoria = categoriaDAO.buscarCategoriaPorId(idCategoria, idUsuario);
        if (categoria == null){
            throw new IllegalArgumentException("Categoria não encontrada");
        }

        return categoria;
    }
}
