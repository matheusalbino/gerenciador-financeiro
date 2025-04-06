package org.example.service;

import org.example.model.Usuario;

import java.util.List;

public interface UsuarioService {
    void registrarUsuario(Usuario usuario);
    void removerUsuario(int idUsuarioARemover);
    Usuario buscarUsuarioPorNome(String username);
    Usuario buscarUsuarioPorID(int userID);
    Usuario validarLogin(String usuario, String senha);
    List<Usuario> listarUsuarios();
    Usuario getUsuarioLogado();
    void logOutUsuario();
    int ultimoIdUsuario();
    int proximoIdUsuario();
}
