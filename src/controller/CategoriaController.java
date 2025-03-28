package controller;

import model.Categoria;
import model.Usuario;
import service.CategoriaService;
import service.CategoriaServiceImpl;
import service.UsuarioService;
import service.UsuarioServiceImpl;

import java.util.List;

public class CategoriaController {
    private final CategoriaService categoriaService = new CategoriaServiceImpl();
    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    public void cadastrarCategoria(String nome, String descricao){
        Usuario usuarioLogado = usuarioService.getUsuarioLogado();
        int idCategoria = categoriaService.proximoIdCategoria(usuarioLogado.getId());

        util.ValidarEntrada.validarStringNuloOuVazia(nome, "Nome vazio não permitido");

        Categoria categoria = new Categoria(idCategoria, usuarioLogado.getId(), nome, descricao);
        categoriaService.adicionarCategoria(categoria);
    }

    public void removerCategoria(String nomeCategoria){
        int idUsuario = usuarioService.getUsuarioLogado().getId();

        util.ValidarEntrada.validarStringVazia(nomeCategoria, "Nome de categoria inválido");

        categoriaService.removerCategoria(nomeCategoria, idUsuario);
    }

    public void editarCategoria(String nomeCategoria, String novoNome, String novaDescricao){

        util.ValidarEntrada.validarStringNuloOuVazia(novoNome, "Nome não pode ser vazio");
        util.ValidarEntrada.validarStringNuloOuVazia(nomeCategoria, "Nome de categoria inválido");
        int idUsuario = usuarioService.getUsuarioLogado().getId();

        categoriaService.editarCategoria(nomeCategoria, idUsuario, novoNome, novaDescricao);
    }

    public List<Categoria> listarCategoriasDoUsuario(){
        int idUsuario = usuarioService.getUsuarioLogado().getId();
        return categoriaService.listarCategoriasDeUsuario(idUsuario);
    }

    public Categoria buscarCategoria (String nomeCategoria){
        int idUsuario = usuarioService.getUsuarioLogado().getId();
        util.ValidarEntrada.validarStringNuloOuVazia(nomeCategoria, "Nome categoria inválido");
        return categoriaService.buscarCategoriaPorNome(nomeCategoria, idUsuario);
    }

}
