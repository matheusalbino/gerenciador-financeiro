package dao;
import model.Usuario;
import java.util.List;
import java.util.ArrayList;

public class UsuarioDAOImpl implements UsuarioDAO {
    private static UsuarioDAOImpl instancia;
    private static List<Usuario> usuarios;

    private UsuarioDAOImpl() {
        usuarios = new ArrayList<>();
    }

    public static synchronized UsuarioDAOImpl getInstance() {
        if (instancia == null) {
            instancia = new UsuarioDAOImpl();
        }

        return instancia;
    }

    @Override
    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public void removerUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }


    @Override
    public Usuario buscarUsuarioPorId(int idUsuario){
        for (Usuario usuario : usuarios){
            if (usuario.getId() == idUsuario){
                return usuario;
            }
        }
        return null;
    }


    @Override
    public Usuario buscarPorLogin(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(username)) {
                return usuario;
            }
        }
        return null;
    }


    public boolean validarEntrada(String username, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(username) && usuario.getSenha().equals(password)) {
                return true;
            }
        }
        return false;
    }
}






