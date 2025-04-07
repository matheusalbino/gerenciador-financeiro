package org.example.view;

import org.example.controller.TransacaoController;
import org.example.controller.CategoriaController;
import org.example.model.Transacao;
import org.example.model.Categoria;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class TelaTransacao extends JPanel {
    private TransacaoController transacaoController = new TransacaoController();
    private CategoriaController categoriaController = new CategoriaController();

    private JTable tabelaTransacoes;
    private DefaultTableModel modeloTabela;
    private JButton btnDeletar;
    private JButton btnCadastrar;

    private JTextField txtValor;
    private JComboBox<String> cbTipo;
    private JComboBox<String> cbCategoria;
    private JTextField txtData;
    private JTextArea taDescricao;

    public TelaTransacao() {
        this.setLayout(new BorderLayout(10, 10));

        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBorder(new LineBorder(Color.GRAY));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        setCampos(painelFormulario, gbc);
        setBotoes(painelFormulario, gbc);

        btnDeletar = new JButton("Deletar Transação");
        btnDeletar.setPreferredSize(new Dimension(180, 40));
        btnDeletar.setEnabled(false);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        painelFormulario.add(btnDeletar, gbc);

        this.add(painelFormulario, BorderLayout.NORTH);

        btnCadastrar.addActionListener(e -> {
            try {
                transacaoController.cadastrarTransacao(
                        cbCategoria.getSelectedItem().toString(),
                        txtValor.getText(),
                        txtData.getText(),
                        taDescricao.getText(),
                        cbTipo.getSelectedItem().toString()
                );
                limparFormulario();
                atualizarTabela();
                System.out.println("Transação cadastrada.");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        String[] colunas = {"ID", "Valor", "Tipo", "Categoria", "Data", "Descrição"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaTransacoes = new JTable(modeloTabela);
        tabelaTransacoes.getColumnModel().getColumn(0).setMinWidth(0);
        tabelaTransacoes.getColumnModel().getColumn(0).setMaxWidth(0);
        tabelaTransacoes.getColumnModel().getColumn(0).setWidth(0);

        JScrollPane scrollTabela = new JScrollPane(tabelaTransacoes);
        scrollTabela.setBorder(new LineBorder(Color.GRAY));

        JPanel painelCentro = new JPanel(new BorderLayout(10, 10));
        painelCentro.add(scrollTabela, BorderLayout.CENTER);

        tabelaTransacoes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && tabelaTransacoes.getSelectedRow() != -1) {
                    btnDeletar.setEnabled(true);
                } else {
                    btnDeletar.setEnabled(false);
                }
            }
        });

        btnDeletar.addActionListener(e -> {
            int linhaSelecionada = tabelaTransacoes.getSelectedRow();
            if (linhaSelecionada != -1) {
                String idTransacao = modeloTabela.getValueAt(linhaSelecionada, 0).toString();
                transacaoController.removerTransacao(idTransacao);
                atualizarTabela();
                System.out.println("Transação removida.");
            }
        });

        this.add(painelCentro, BorderLayout.CENTER);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                atualizarTabela();
            }
        });

        atualizarTabela();
    }

    private void setCampos(JPanel painelFormulario, GridBagConstraints gbc) {
        JLabel lblValor = new JLabel("Valor:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelFormulario.add(lblValor, gbc);

        txtValor = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        painelFormulario.add(txtValor, gbc);

        JLabel lblTipo = new JLabel("Tipo:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelFormulario.add(lblTipo, gbc);

        String[] opcoesTipo = {"Receita", "Despesa"};
        cbTipo = new JComboBox<>(opcoesTipo);
        gbc.gridx = 1;
        gbc.gridy = 1;
        painelFormulario.add(cbTipo, gbc);

        JLabel lblCategoria = new JLabel("Categoria:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        painelFormulario.add(lblCategoria, gbc);

        cbCategoria = new JComboBox<>();
        populateCategorias();
        gbc.gridx = 1;
        gbc.gridy = 2;
        painelFormulario.add(cbCategoria, gbc);

        JLabel lblData = new JLabel("Data:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        painelFormulario.add(lblData, gbc);

        txtData = new JTextField("DD/MM/YYYY");
        gbc.gridx = 1;
        gbc.gridy = 3;
        painelFormulario.add(txtData, gbc);

        JLabel lblDescricao = new JLabel("Descrição:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        painelFormulario.add(lblDescricao, gbc);

        taDescricao = new JTextArea(3, 20);
        JScrollPane scrollDescricao = new JScrollPane(taDescricao);
        gbc.gridx = 1;
        gbc.gridy = 4;
        painelFormulario.add(scrollDescricao, gbc);
    }

    private void setBotoes(JPanel painelFormulario, GridBagConstraints gbc) {
        btnCadastrar = new JButton("Cadastrar Transação");
        btnCadastrar.setPreferredSize(new Dimension(180, 40));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        painelFormulario.add(btnCadastrar, gbc);
    }

    private void populateCategorias() {
        cbCategoria.removeAllItems();
        try {
            List<Categoria> categorias = categoriaController.listarCategoriasDoUsuario();
            if (categorias != null && !categorias.isEmpty()) {
                for (Categoria c : categorias) {
                    cbCategoria.addItem(c.getNome());
                }
            } else {
                cbCategoria.addItem("Nenhuma categoria cadastrada");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar categorias: " + e.getMessage());
        }
    }

    private void limparFormulario() {
        txtValor.setText("");
        cbTipo.setSelectedIndex(0);
        if (cbCategoria.getItemCount() > 0) {
            cbCategoria.setSelectedIndex(0);
        }
        txtData.setText("DD/MM/YYYY");
        taDescricao.setText("");
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        List<Transacao> transacoes = transacaoController.listarTransacoesDoUsuario();
        if (transacoes != null) {
            for (Transacao t : transacoes) {
                modeloTabela.addRow(new Object[]{
                        t.getId(),
                        t.getValor(),
                        t.getTipo(),
                        t.getCategoria().getNome(),
                        t.getData(),
                        t.getDescricao()
                });
            }
        }
        populateCategorias();
    }
}
