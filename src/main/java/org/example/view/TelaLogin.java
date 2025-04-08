package org.example.view;

import org.example.controller.UsuarioController;
import org.example.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaLogin extends JFrame {


    private UsuarioController usuarioController = new UsuarioController();
    private JTextField txtUsername;
    private JPasswordField txtSenha;
    private JButton btnLogin;
    private JButton btnCriarConta;

    public TelaLogin() {
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(new Dimension(1290, 760));

        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        setSenha(panel,gbc);
        setUsername(panel,gbc);
        setBotoes(panel,gbc);

        add(panel);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String senha = new String(txtSenha.getPassword());
                Usuario usuario = usuarioController.Login(username, senha);
                if (usuario != null) {
                    JanelaPrincipal janelaPrincipal = new JanelaPrincipal();
                    janelaPrincipal.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(TelaLogin.this,
                            "Usuário ou senha não encontrado.",
                            "Erro de Login",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCriarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroUsuario telaCadastro = new TelaCadastroUsuario();
                telaCadastro.setVisible(true);
                dispose();
            }
        });
    }


    private void setSenha(JPanel panel, GridBagConstraints gbc) {
        JLabel lblSenha = new JLabel("Senha:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblSenha, gbc);

        txtSenha = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtSenha, gbc);
    }


    private void setBotoes(JPanel panel, GridBagConstraints gbc) {
        btnLogin = new JButton("Logar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(btnLogin, gbc);

        btnCriarConta = new JButton("Criar Conta");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(btnCriarConta, gbc);
    }


    private void setUsername(JPanel panel, GridBagConstraints gbc) {
        JLabel lblUsername = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblUsername, gbc);

        txtUsername = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtUsername, gbc);
    }

}
