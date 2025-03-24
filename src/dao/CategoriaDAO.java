package dao;

import model.Categoria;

import java.util.List;

public interface CategoriaDAO {
    public void adicionarCategoria(Categoria categoria);
    public boolean removerCategoria(int id);
    public List<Categoria> buscarCategoriasDeUsuario(int id);
    public Categoria buscarCategoriaPorId(int idCategoria, int idUsuario);
}
