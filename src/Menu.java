import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {
    public Menu(JanelaPrincipal janelaPrincipal) {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Menu", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new GridLayout(5, 1, 10, 10));

        JButton botaoGerenciarUsuario = new JButton("Gerenciar Usuário");
        JButton botaoGerenciarCategorias = new JButton("Gerenciar Categorias");
        JButton botaoCadastrarTransacoes = new JButton("Cadastrar Transações");
        JButton botaoResumoFinanceiro = new JButton("Resumo Financeiro");
        JButton botaoConsultaHistorico = new JButton("Consulta de Histórico");

        painelCentral.add(botaoGerenciarUsuario);
        painelCentral.add(botaoGerenciarCategorias);
        painelCentral.add(botaoCadastrarTransacoes);
        painelCentral.add(botaoResumoFinanceiro);
        painelCentral.add(botaoConsultaHistorico);

        add(painelCentral, BorderLayout.CENTER);

        JButton botaoSair = new JButton("Sair");
        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaPrincipal.mostrarPainel("Login");
            }
        });

        JPanel painelSul = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelSul.add(botaoSair);
        add(painelSul, BorderLayout.SOUTH);
    }
}

