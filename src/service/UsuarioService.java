package service;

import model.Usuario;

import java.util.List;

public interface UsuarioService {
    void registrarUsuario(Usuario usuario);
    void removerUsuario(int idUsuarioARemover);
    Usuario buscarUsuarioPorLogin(String username);
    Usuario buscarUsuarioPorID(int userID);
    Usuario validarLogin(String usuario, String senha);
    List<Usuario> listarUsuarios();
    void login(Usuario usuario);
    Usuario getUsuarioLogado();
}
