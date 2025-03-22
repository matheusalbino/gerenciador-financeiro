package dao;

import model.Usuario;

public interface UsuarioDAO {
    public void cadastrarUsuario(Usuario usuario);
    public boolean removerUsuario(int userid);
    public Usuario buscarPorLogin(String username);
    // public boolean validarEntrada(String usuario, String senha);
}
