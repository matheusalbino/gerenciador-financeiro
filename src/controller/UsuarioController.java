package controller;

import model.Usuario;
import service.UsuarioService;
import service.UsuarioServiceImpl;

public class UsuarioController {

    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    public void cadastrarUsuario(int id, String username, String senha){
        // verificar campos


        // criar objeto usuario
        Usuario usuario = new Usuario(id, username, senha);
        usuarioService.registrarUsuario(usuario);
    }

    public void removerUsuario(int idUsuario){
        // checar id

        usuarioService.removerUsuario(idUsuario);
    }

    public Usuario buscarUsuarioPorID(int idUsuario){
        // checar id

        return usuarioService.buscarUsuarioPorID(idUsuario);
    }

    public Usuario buscarUsuarioPorUsername(String username){
        // checar username

        return usuarioService.buscarUsuarioPorLogin(username);
    }

    public boolean validarLogin(String username, String senha){
        // verificar campos

        return usuarioService.validarLogin(username, senha);
    }
}
