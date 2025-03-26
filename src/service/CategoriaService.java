package service;

import model.Categoria;

import java.util.List;

public interface CategoriaService {
    // geral: adicionar, remover, buscar por id de usuario, buscar por id de categoria
    // checagem de argumentos nulo e < 0 ja feitos no controller

    // adicionar categoria
    // checar se o usuario existe, se a categoria ainda não existe
    public void adicionarCategoria(Categoria categoria);

    // remover categoria
    // checar se categoria existe, checar se está ligada a transações
    // redirecionar transações a outra categoria e remover categoria
    public void removerCategoria(int idCategoria, int idUsuario);

    // editar categoria
    // verificar se categoria existe e redefinir parâmetros com getters e setters
    public void editarCategoria(int idCategoria, int idUsuario);

    // buscar categorias por id de usuario
    // verificar se usuario existe, checar se tem categorias, retornar categorias
    public List<Categoria> buscarCategoriasPorUserID(int idUsuario);

    // buscar categoria por id de categoria
    // verificar se categoria existe, retornar
    public Categoria buscarCategoriaPorId(int idCategoria, int idUsuario);
}
