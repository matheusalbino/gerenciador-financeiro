package org.example.view;

import org.example.controller.TransacaoController;
import org.example.controller.CategoriaController;
import org.example.model.Transacao;
import org.example.model.Categoria;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

public class TelaTransacao extends JPanel {
    private final TransacaoController transacaoController = new TransacaoController();
    private final CategoriaController categoriaController = new CategoriaController();

    private final JTable tabelaTransacoes;
    private final DefaultTableModel modeloTabela;
    private final JButton btnDeletar;
    private JButton btnCadastrar;

    private JTextField txtValor;
    private JComboBox<String> cbTipo;
    private JComboBox<String> cbCategoria;
    private JTextField txtData;
    private JTextArea taDescricao;

    public TelaTransacao() {
        this.setLayout(new BorderLayout(10, 10));

        JPanel painelFormulario = new JPanel();
        painelFormulario.setBorder(new LineBorder(Color.GRAY));
        painelFormulario.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        setCampos(painelFormulario,gbc);
        setBotoes(painelFormulario,gbc);

        btnCadastrar.addActionListener(e -> {
            try {
                transacaoController.cadastrarTransacao(
                        Objects.requireNonNull(cbCategoria.getSelectedItem()).toString(),
                        txtValor.getText(),
                        txtData.getText(),
                        taDescricao.getText(),
                        Objects.requireNonNull(cbTipo.getSelectedItem()).toString()
                );
                limparFormulario();
                atualizarTabela();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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

        JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnDeletar = new JButton("Deletar");
        btnDeletar.setEnabled(false);
        painelAcoes.add(btnDeletar);

        tabelaTransacoes.getSelectionModel().addListSelectionListener(e ->
                btnDeletar.setEnabled(!e.getValueIsAdjusting() && tabelaTransacoes.getSelectedRow() != -1)
        );

        btnDeletar.addActionListener(e -> {
            int linhaSelecionada = tabelaTransacoes.getSelectedRow();
            if (linhaSelecionada != -1) {
                String idTransacao = modeloTabela.getValueAt(linhaSelecionada, 0).toString();
                transacaoController.removerTransacao(idTransacao);
                atualizarTabela();
            }
        });

        JPanel painelCentro = new JPanel(new BorderLayout(10, 10));
        painelCentro.add(scrollTabela, BorderLayout.CENTER);
        painelCentro.add(painelAcoes, BorderLayout.SOUTH);

        this.add(painelFormulario, BorderLayout.NORTH);
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
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        if (transacoes != null) {
            for (Transacao t : transacoes) {
                modeloTabela.addRow(new Object[]{
                        t.getId(),
                        t.getValor(),
                        t.getTipo(),
                        // formater aqui
                        t.getCategoria().getNome(),
                        t.getData().toString(),
                        t.getDescricao()
                });
            }
        }
        populateCategorias();
    }
}
