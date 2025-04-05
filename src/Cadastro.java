import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cadastro extends JFrame implements ActionListener {

    private JTextField valorField;
    private JFormattedTextField dataField;
    private JTextField descricaoField;
    private JRadioButton receitaButton, creditoButton;
    private JComboBox<String> categoriaBox;
    private JButton adicionarCategoriaButton, cadastrarButton, limparButton, cancelarButton;

    public Cadastro() {
        setTitle("Sistema Financeiro");
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Cadastrar Transação");
        titleLabel.setBounds(180, 10, 200, 20);
        add(titleLabel);

        JLabel valorLabel = new JLabel("Valor:");
        valorLabel.setBounds(20, 50, 100, 20);
        add(valorLabel);

        valorField = new JTextField();
        valorField.setBounds(85, 50, 100, 20);
        add(valorField);

        JLabel dataLabel = new JLabel("Data:");
        dataLabel.setBounds(20, 90, 100, 20);
        add(dataLabel);

        dataField = new JFormattedTextField();
        dataField.setBounds(85, 90, 100, 20);
        add(dataField);

        JLabel descricaoLabel = new JLabel("Descrição:");
        descricaoLabel.setBounds(20, 130, 100, 20);
        add(descricaoLabel);

        descricaoField = new JTextField();
        descricaoField.setBounds(85, 130, 200, 20);
        add(descricaoField);

        receitaButton = new JRadioButton("Receita");
        receitaButton.setBounds(300, 50, 100, 20);
        add(receitaButton);

        creditoButton = new JRadioButton("Crédito");
        creditoButton.setBounds(300, 80, 100, 20);
        add(creditoButton);

        ButtonGroup tipoGroup = new ButtonGroup();
        tipoGroup.add(receitaButton);
        tipoGroup.add(creditoButton);

        JLabel categoriaLabel = new JLabel("Categoria:");
        categoriaLabel.setBounds(250, 160, 100, 20);
        add(categoriaLabel);

        String[] categorias = {"Item 1", "Item 2", "Item 3"};
        categoriaBox = new JComboBox<>(categorias);
        categoriaBox.setBounds(320, 160, 120, 20);
        add(categoriaBox);

        adicionarCategoriaButton = new JButton("Adicionar Categoria...");
        adicionarCategoriaButton.setBounds(300, 190, 150, 20);
        add(adicionarCategoriaButton);

        cancelarButton = new JButton("Cancelar");
        cancelarButton.setBounds(50, 350, 100, 30);
        add(cancelarButton);

        limparButton = new JButton("Limpar campos...");
        limparButton.setBounds(180, 350, 130, 30);
        add(limparButton);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(340, 350, 100, 30);
        add(cadastrarButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implementar funcionalidades aqui
    }

    public static void main(String[] args) {
        new Cadastro();
    }
}
