package dao;

import model.Usuario;

public interface UsuarioDAO {
    public void cadastrarUsuario(Usuario usuario);
    public void removerUsuario(Usuario usuario);
    public Usuario buscarPorLogin(String username);
    public Usuario buscarUsuarioPorId(int idUsuario);
}
