import javax.swing.*;
import java.awt.*;

public class JanelaPrincipal extends JFrame {
    private CardLayout layout;
    private JPanel painelPrincipal;

    public JanelaPrincipal() {
        setTitle("Sistema Pessoal Financeiro");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        layout = new CardLayout();
        painelPrincipal = new JPanel(layout);

        TelaDeLogin telaDeLogin = new TelaDeLogin(this);
        TelaDeCadastroDeTransacao telaDeCadastro = new TelaDeCadastroDeTransacao(this);
        Menu telaDeMenu = new Menu(this);

        painelPrincipal.add(telaDeLogin, "Login");
        painelPrincipal.add(telaDeCadastro, "Cadastro");
        painelPrincipal.add(telaDeMenu, "Menu");

        add(painelPrincipal);
        setVisible(true);
    }

    public void mostrarPainel(String nomePainel) {
        layout.show(painelPrincipal, nomePainel);
    }

    public static void main(String[] args) {
        new JanelaPrincipal();
    }
}

class TelaDeLogin extends JPanel {
    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JLabel rotuloErro;

    public TelaDeLogin(JanelaPrincipal janelaPrincipal) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel rotuloTitulo = new JLabel("Sistema Pessoal Financeiro");
        rotuloTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(rotuloTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Usuário:"), gbc);

        campoUsuario = new JTextField(15);
        gbc.gridx = 1;
        add(campoUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Senha:"), gbc);

        campoSenha = new JPasswordField(15);
        gbc.gridx = 1;
        add(campoSenha, gbc);

        JButton botaoEntrar = new JButton("Entrar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(botaoEntrar, gbc);

        rotuloErro = new JLabel("", SwingConstants.CENTER);
        rotuloErro.setForeground(Color.RED);
        gbc.gridy = 4;
        add(rotuloErro, gbc);

        botaoEntrar.addActionListener(e -> {
            String usuario = campoUsuario.getText();
            String senha = new String(campoSenha.getPassword());
            if (usuario.equals("admin") && senha.equals("1234")) {
                janelaPrincipal.mostrarPainel("Menu");
            } else {
                rotuloErro.setText("Usuário ou senha incorretos!");
            }
        });
    }
}

class TelaDeCadastroDeTransacao extends JPanel {
    public TelaDeCadastroDeTransacao(JanelaPrincipal janelaPrincipal) {
        setLayout(new BorderLayout());
        JLabel rotulo = new JLabel("Tela de Cadastro de Transação", SwingConstants.CENTER);
        add(rotulo, BorderLayout.CENTER);
    }
}
