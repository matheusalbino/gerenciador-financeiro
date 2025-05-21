package org.example.view;

import org.example.controller.UsuarioController;
import org.example.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class TelaHome extends JPanel {
    private final UsuarioController usuarioController = new UsuarioController();

    public TelaHome() {
        setLayout(new BorderLayout());

        Usuario usuario = usuarioController.buscarUsuarioLogado();
        String nomeUsuario = usuario != null ? usuario.getLogin() : "Usuário";

        JPanel topo = new JPanel(new BorderLayout());
        topo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblBoasVindas = new JLabel("Bem-vindo ao Sistema Financeiro!");
        lblBoasVindas.setFont(new Font("Arial", Font.BOLD, 24));

        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Arial", Font.PLAIN, 16));

        topo.add(lblBoasVindas, BorderLayout.WEST);
        topo.add(btnLogout, BorderLayout.EAST);

        JTextArea textoInfo = new JTextArea("\nUsuário conectado: " + nomeUsuario + "\n\n");
        textoInfo.setEditable(false);
        textoInfo.setFont(new Font("Arial", Font.PLAIN, 18));
        textoInfo.setMargin(new Insets(20, 20, 20, 20));

        add(topo, BorderLayout.NORTH);
        add(textoInfo, BorderLayout.CENTER);

        btnLogout.addActionListener(e -> {
            usuarioController.logoutUsuario();
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(TelaHome.this);
            frame.dispose();
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
        });
    }
}
