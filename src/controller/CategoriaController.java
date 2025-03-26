package controller;

import model.Categoria;
import model.enums.TipoCategoria;
import service.CategoriaService;
import service.CategoriaServiceImpl;

import java.util.List;

public class CategoriaController {
    private final CategoriaService categoriaService = new CategoriaServiceImpl();

    public void cadastrarCategoria(int idCategoria, int idUsuario, String nome, String descricao){
        // verificar campos


        Categoria categoria = new Categoria(idCategoria, idUsuario, nome, descricao);

        categoriaService.adicionarCategoria(categoria);
    }

    public void removerCategoria(int idCategoria, int idUsuario){
        // verificar ids


        categoriaService.removerCategoria(idCategoria, idUsuario);
    }

    public void editarCategoria(int idCategoria, int idUsuario){
        // verificar id

        categoriaService.editarCategoria(idCategoria, idUsuario);
    }

    public List<Categoria> listarCategoriasDoUsuario(int idUsuario){
        // verificar id

        return categoriaService.buscarCategoriasPorUserID(idUsuario);
    }

    public Categoria buscarCategoria(int idCategoria, int idUsuario){
        // verificar ids

        return categoriaService.buscarCategoriaPorId(idCategoria, idUsuario);
    }

}
