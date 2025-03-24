package service;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioServiceImpl implements UsuarioService {
    UsuarioDAO usuarioDAO;

    @Override
    public void registrarUsuario(Usuario usuario){
        if (usuarioDAO.buscarUsuarioPorId(usuario.getId()) != null){
            throw new IllegalArgumentException("Id de usuário já existente");
        }
        if (usuarioDAO.buscarPorLogin(usuario.getLogin()) != null){
            throw new IllegalArgumentException("Nome de usuário já existe");
        }


    }

    @Override
    public void removerUsuario(int idUsuarioARemover) {
        return;
    }

    @Override
    public Usuario buscarUsuarioPorLogin(String username) {
        Usuario usuario = usuarioDAO.buscarPorLogin(username);
        if (usuario == null){
            throw new IllegalArgumentException("Username não encontrado");
        }

        return usuario;
    }

    @Override
    public Usuario buscarUsuarioPorID(int userID) {
        Usuario usuario = usuarioDAO.buscarUsuarioPorId(userID);
        if (usuario == null){
            throw new IllegalArgumentException("ID de usuário não encontrado");
        }

        return usuario;
    }

    @Override
    public boolean validarLogin(String usuario, String senha) {
        return false;
    }

}
