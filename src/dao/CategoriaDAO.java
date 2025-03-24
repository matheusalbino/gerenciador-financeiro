package dao;

import model.Categoria;

import java.util.List;

public interface CategoriaDAO {
    public void adicionarCategoria(Categoria categoria);
    public void removerCategoria(Categoria categoria);
    public void editarCategoria(Categoria categoria);
    public List<Categoria> listarCategoriasDeUsuario(int id);
    public Categoria buscarCategoriaPorId(int idCategoria, int idUsuario);
}
