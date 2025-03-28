package dao;

import model.Usuario;

import java.util.List;

public interface UsuarioDAO {
    void cadastrarUsuario(Usuario usuario);
    void removerUsuario(Usuario usuario);
    Usuario buscarPorLogin(String username);
    Usuario buscarUsuarioPorId(int idUsuario);
    //Usuario validarEntrada(String username, String password);
    List<Usuario> listarUsuarios();
    void login(Usuario usuario);
    Usuario getUsuarioLogado();
}
