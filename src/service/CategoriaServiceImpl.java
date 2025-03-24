package service;

import dao.CategoriaDAO;
import dao.UsuarioDAO;
import model.Categoria;

import java.util.List;

public class CategoriaServiceImpl implements CategoriaService{
    UsuarioDAO usuarioDAO;
    CategoriaDAO categoriaDAO;

    @Override
    public void adicionarCategoria(Categoria categoria) {
        if (categoriaDAO.buscarCategoriaPorId(categoria.getId(), categoria.getUserid()) != null){
            throw new IllegalArgumentException("Categoria ja existe pro usuário");
        }

        categoriaDAO.adicionarCategoria(categoria);
    }

    @Override
    public void removerCategoria(Categoria categoria) {
        throw new RuntimeException("Ainda não implementado");
    }

    @Override
    public void editarCategoria(Categoria categoria) {
        if (categoriaDAO.buscarCategoriaPorId(categoria.getId(), categoria.getUserid()) == null){
            throw new IllegalArgumentException("Erro na edição");
        }

        categoriaDAO.editarCategoria(categoria);
    }

    @Override
    public List<Categoria> buscarCategoriasPorUserID(int idUsuario) {
        List<Categoria> categorias = categoriaDAO.listarCategoriasDeUsuario(idUsuario);

        if (usuarioDAO.buscarUsuarioPorId(idUsuario) == null){
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        if (categorias == null || categorias.size() <= 0){
            throw new IllegalArgumentException("Usuario sem categorias");
        }

        return categorias;
    }

    @Override
    public Categoria buscarCategoriaPorId(int idCategoria, int idUsuario) {
        Categoria categoria = categoriaDAO.buscarCategoriaPorId(idCategoria, idUsuario);
        if (categoria == null){
            throw new IllegalArgumentException("Categoria não encontrada");
        }

        return categoria;
    }
}
