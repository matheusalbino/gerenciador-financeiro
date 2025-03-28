package controller;

import model.Usuario;
import service.UsuarioService;
import service.UsuarioServiceImpl;

public class UsuarioController {

    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    public void cadastrarUsuario(int id, String username, String senha){
        // verificar campos
        util.ValidarEntrada.validarInt(id, "Id não permitido.");
        util.ValidarEntrada.validarStringNuloOuVazia(username, "Insira um nome de usuário.");
        util.ValidarEntrada.validarStringNuloOuVazia(senha, "Insira uma senha");

        // criar objeto usuario
        Usuario usuario = new Usuario(id, username, senha);
        usuarioService.registrarUsuario(usuario);
    }

    public void removerUsuario(int idUsuario){
        // checar id
        util.ValidarEntrada.validarInt(idUsuario, "Id usuário não permitido");


        usuarioService.removerUsuario(idUsuario);
    }

    public Usuario buscarUsuarioPorID(int idUsuario){
        // checar id
        util.ValidarEntrada.validarInt(idUsuario, "Id usuário não permitido");


        return usuarioService.buscarUsuarioPorID(idUsuario);
    }

    public Usuario buscarUsuarioPorUsername(String username){
        // checar username
        util.ValidarEntrada.validarStringNuloOuVazia(username, "Insira um username");

        return usuarioService.buscarUsuarioPorLogin(username);
    }

    public Usuario validarLogin(String username, String senha){
        // verificar campos
        util.ValidarEntrada.validarStringNuloOuVazia(username, "Insira um username");
        util.ValidarEntrada.validarStringNuloOuVazia(senha, "Insira uma senha");

        return usuarioService.validarLogin(username, senha);
    }
}
