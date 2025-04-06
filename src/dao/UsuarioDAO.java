package dao;

import model.Usuario;

import java.util.List;

public interface UsuarioDAO {
    void cadastrarUsuario(Usuario usuario);
    void removerUsuario(Usuario usuario);
    Usuario buscarPorNome(String username);
    Usuario buscarUsuarioPorId(int idUsuario);
    List<Usuario> listarUsuarios();
    void logarUsuario(Usuario usuario);
    Usuario getUsuarioLogado();
    void logoutUsuario();
}
