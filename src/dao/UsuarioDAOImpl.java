package dao;
import model.Usuario;
import singleton.UsuarioSingleton;


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


    public boolean validarEntrada(String username, String password) {
        for (Usuario usuario : UsuarioSingleton.getInstance().getUsuarios()) {
            if (usuario.getLogin().equals(username) && usuario.getSenha().equals(password)) {
                return true;
            }
        }
        return false;
    }
}






