package singleton;

import dao.UsuarioDAOImpl;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioSingleton {
    private static UsuarioSingleton instancia;
    private static List<Usuario> usuarios;

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
}
