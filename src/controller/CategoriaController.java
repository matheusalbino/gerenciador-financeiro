package controller;

import model.Categoria;
import service.CategoriaService;
import service.CategoriaServiceImpl;

import java.util.List;

public class CategoriaController {
    private final CategoriaService categoriaService = new CategoriaServiceImpl();

    public void cadastrarCategoria(Categoria categoria){
        // checar categoria

        categoriaService.adicionarCategoria(categoria);
    }

    public void removerCategoria(Categoria categoria){
        // checar categoria

        categoriaService.removerCategoria(categoria);
    }

    public void editarCategoria(Categoria categoria){
        // checar categoria

        categoriaService.editarCategoria(categoria);
    }

    public List<Categoria> listarCategoriasDoUsuario(int idUsuario){
        // checar id

        return categoriaService.buscarCategoriasPorUserID(idUsuario);
    }

    public Categoria buscarCategoria(int idCategoria, int idUsuario){
        // checar ids

        return categoriaService.buscarCategoriaPorId(idCategoria, idUsuario);
    }

}
