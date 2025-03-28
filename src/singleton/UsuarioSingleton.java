package singleton;

import dao.UsuarioDAOImpl;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioSingleton {
    private static UsuarioSingleton instancia;
    private final List<Usuario> usuarios;
    private Usuario usuarioLogado = null;

    private UsuarioSingleton() {
        usuarios = new ArrayList<>();
    }

    public static synchronized UsuarioSingleton getInstance() {
        if (instancia == null) {
            instancia = new UsuarioSingleton();
        }

        return instancia;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
