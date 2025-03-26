package controller;

import model.Usuario;
import service.UsuarioService;
import service.UsuarioServiceImpl;
import singleton.UsuarioSingleton;

public class UsuarioController {

    private UsuarioService usuarioService = new UsuarioServiceImpl();

    public void cadastrarUsuario(Usuario usuario){
        // checar usuario

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
        // checar username e senha

        return usuarioService.validarLogin(username, senha);
    }
}
