package org.example;

import org.example.factory.DAOFactory;
import org.example.model.enums.TipoPersistencia;
import org.example.view.TelaLogin;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        DAOFactory.configurar(TipoPersistencia.MEMORIA);

        SwingUtilities.invokeLater(() -> {
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
        });

    }
}