import controller.UsuarioController;
import test.test;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        UsuarioController usuarioController = new UsuarioController();
        test test = new test();
        test.testarFuncionalidades();
        usuarioController.cadastrarUsuario("a", "a");
        usuarioController.Login("a", "a");

    }
}