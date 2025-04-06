package controller;

import model.Usuario;
import service.UsuarioService;
import service.UsuarioServiceImpl;

public class UsuarioController {

    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    public void cadastrarUsuario(String username, String senha){
        util.ValidarEntrada.validarStringNuloOuVazia(username, "Insira um nome de usuário.");
        util.ValidarEntrada.validarStringNuloOuVazia(senha, "Insira uma senha");

        int idUsuario = usuarioService.proximoIdUsuario();

        if (usuarioService.buscarUsuarioPorNome(username) != null){
            throw new IllegalArgumentException("Nome de usuario já existe");
        }

        Usuario usuario = new Usuario(idUsuario, username, senha);
        usuarioService.registrarUsuario(usuario);
    }

    public void removerUsuario(int idUsuario){
        usuarioService.removerUsuario(idUsuario);
    }

    public Usuario buscarUsuarioPorID(int idUsuario){
        util.ValidarEntrada.validarInt(idUsuario, "Id usuário não permitido");

        return usuarioService.buscarUsuarioPorID(idUsuario);
    }

    public void logoutUsuario(){
        usuarioService.logOutUsuario();
    }

    public Usuario buscarUsuarioLogado(){
        return usuarioService.getUsuarioLogado();
    }

    public Usuario buscarUsuarioPorUsername(String username){
        util.ValidarEntrada.validarStringNuloOuVazia(username, "Insira um username");
        return usuarioService.buscarUsuarioPorNome(username);
    }

    public Usuario Login (String username, String senha){
        util.ValidarEntrada.validarStringNuloOuVazia(username, "Insira um username");
        util.ValidarEntrada.validarStringNuloOuVazia(senha, "Insira uma senha");

        return usuarioService.validarLogin(username, senha);
    }

}
