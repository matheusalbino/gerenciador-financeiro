package org.example.view;

import org.example.controller.UsuarioController;
import javax.swing.*;
import java.awt.*;

public class TelaCadastroUsuario extends JFrame {
    private final UsuarioController usuarioController = new UsuarioController();

    private JTextField txtUsername;
    private JPasswordField txtSenha;
    private JPasswordField txtConfirmSenha;
    private JButton btnCadastrar;
    private JButton btnVoltar;

    public TelaCadastroUsuario() {
        super("Cadastro de Usuário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(new Dimension(1290, 760));
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        setUsername(panel,gbc);
        setBotoes(panel,gbc);
        setSenha(panel,gbc);

        add(panel);

        btnCadastrar.addActionListener(e -> {
            String username = txtUsername.getText();
            String senha = new String(txtSenha.getPassword());
            String confirmSenha = new String(txtConfirmSenha.getPassword());

            if (!senha.equals(confirmSenha)) {
                JOptionPane.showMessageDialog(TelaCadastroUsuario.this,
                        "Senha e confirmação não conferem.",
                        "Erro no Cadastro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            usuarioController.cadastrarUsuario(username, senha);
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
            dispose();
        });

        btnVoltar.addActionListener(e -> {
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
            dispose();
        });
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


    private void setSenha(JPanel panel, GridBagConstraints gbc) {
        JLabel lblSenha = new JLabel("Senha:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblSenha, gbc);

        txtSenha = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtSenha, gbc);

        JLabel lblConfirmSenha = new JLabel("Confirmar Senha:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblConfirmSenha, gbc);

        txtConfirmSenha = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtConfirmSenha, gbc);
    }



    private void setBotoes(JPanel panel, GridBagConstraints gbc) {
        btnCadastrar = new JButton("Cadastrar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(btnCadastrar, gbc);

        btnVoltar = new JButton("Voltar");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(btnVoltar, gbc);
    }

}
