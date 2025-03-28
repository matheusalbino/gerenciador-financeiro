package dao;
import model.Usuario;
import singleton.UsuarioSingleton;

import java.util.List;


public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public void cadastrarUsuario(Usuario usuario) {
        UsuarioSingleton.getInstance().getUsuarios().add(usuario);
    }

    @Override
    public void removerUsuario(Usuario usuario) {
        UsuarioSingleton.getInstance().getUsuarios().remove(usuario);
    }


    @Override
    public Usuario buscarUsuarioPorId(int idUsuario){
        for (Usuario usuario : UsuarioSingleton.getInstance().getUsuarios()){
            if (usuario.getId() == idUsuario){
                return usuario;
            }
        }
        return null;
    }


    @Override
    public Usuario buscarPorLogin(String username) {
        for (Usuario usuario : UsuarioSingleton.getInstance().getUsuarios()) {
            if (usuario.getLogin().equals(username)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public List<Usuario> listarUsuarios(){
        return UsuarioSingleton.getInstance().getUsuarios();
    }

    @Override
    public void login(Usuario usuario) {
        UsuarioSingleton.getInstance().setUsuarioLogado(usuario);
    }

    @Override
    public Usuario getUsuarioLogado() {
        return UsuarioSingleton.getInstance().getUsuarioLogado();
    }

}






