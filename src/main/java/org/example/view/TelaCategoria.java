package org.example.view;

import org.example.controller.CategoriaController;
import org.example.model.Categoria;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaCategoria extends JPanel {
    private final CategoriaController categoriaController = new CategoriaController();

    private final JTable tabelaCategorias;
    private final DefaultTableModel modeloTabela;
    private final JButton btnEditar;
    private final JButton btnDeletar;
    private JButton btnCadastrar;
    private JButton btnAtualizar;
    private JTextField txtNome;
    private JTextArea txtDescricao;

    private String categoriaSelecionadaNome = null;

    public TelaCategoria() {
        this.setLayout(new BorderLayout(10, 10));

        JPanel painelFormulario = new JPanel();
        painelFormulario.setBorder(new LineBorder(Color.GRAY));
        painelFormulario.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        setTxtFields(painelFormulario, gbc);
        setBotoes(painelFormulario, gbc);

        btnEditar = new JButton("Editar");
        btnDeletar = new JButton("Deletar");
        btnEditar.setEnabled(false);
        btnDeletar.setEnabled(false);

        JPanel painelEdicao = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelEdicao.add(btnEditar);
        painelEdicao.add(btnDeletar);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        painelFormulario.add(painelEdicao, gbc);

        try {
            btnCadastrar.addActionListener(e -> {
                categoriaController.cadastrarCategoria(
                        txtNome.getText(),
                        txtDescricao.getText()
                );
                limparFormulario();
                atualizarTabela();
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        btnAtualizar.addActionListener(e -> {
            try {
                categoriaController.editarCategoria(
                        categoriaSelecionadaNome,
                        txtNome.getText(),
                        txtDescricao.getText()
                );
                limparFormulario();
                atualizarTabela();
                btnAtualizar.setEnabled(false);
                btnCadastrar.setEnabled(true);
                categoriaSelecionadaNome = null;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        String[] colunas = {"Nome", "Descrição"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaCategorias = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaCategorias);
        scrollTabela.setBorder(new LineBorder(Color.GRAY));

        tabelaCategorias.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabelaCategorias.getSelectedRow() != -1) {
                btnEditar.setEnabled(true);
                btnDeletar.setEnabled(true);
            } else {
                btnEditar.setEnabled(false);
                btnDeletar.setEnabled(false);
            }
        });

        btnEditar.addActionListener(e -> {
            int linhaSelecionada = tabelaCategorias.getSelectedRow();
            if (linhaSelecionada != -1) {
                categoriaSelecionadaNome = modeloTabela.getValueAt(linhaSelecionada, 0).toString();
                Categoria categoria = categoriaController.buscarCategoria(categoriaSelecionadaNome);
                if (categoria != null) {
                    txtNome.setText(categoria.getNome());
                    txtDescricao.setText(categoria.getDescricao());

                    btnAtualizar.setEnabled(true);
                    btnCadastrar.setEnabled(false);
                }
            }
        });

        btnDeletar.addActionListener(e -> {
            int linhaSelecionada = tabelaCategorias.getSelectedRow();
            if (linhaSelecionada != -1) {
                String nomeCategoria = modeloTabela.getValueAt(linhaSelecionada, 0).toString();
                categoriaController.removerCategoria(nomeCategoria);
                atualizarTabela();
            }
        });

        JPanel painelCentro = new JPanel(new BorderLayout(10, 10));
        painelCentro.add(scrollTabela, BorderLayout.CENTER);

        this.add(painelFormulario, BorderLayout.NORTH);
        this.add(painelCentro, BorderLayout.CENTER);

        atualizarTabela();
    }

    private void setTxtFields(JPanel painelFormulario, GridBagConstraints gbc) {
        JLabel lblNome = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelFormulario.add(lblNome, gbc);

        txtNome = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        painelFormulario.add(txtNome, gbc);

        JLabel lblDescricao = new JLabel("Descrição:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelFormulario.add(lblDescricao, gbc);

        txtDescricao = new JTextArea(3, 20);
        JScrollPane scrollDescricao = new JScrollPane(txtDescricao);
        gbc.gridx = 1;
        gbc.gridy = 1;
        painelFormulario.add(scrollDescricao, gbc);
    }

    private void setBotoes(JPanel painelFormulario, GridBagConstraints gbc) {
        btnCadastrar = new JButton("Cadastrar Categoria");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        painelFormulario.add(btnCadastrar, gbc);

        btnAtualizar = new JButton("Atualizar Categoria");
        btnAtualizar.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        painelFormulario.add(btnAtualizar, gbc);
    }

    private void limparFormulario() {
        txtNome.setText("");
        txtDescricao.setText("");
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        try {
            List<Categoria> categorias = categoriaController.listarCategoriasDoUsuario();
            if (categorias != null) {
                for (Categoria c : categorias) {
                    modeloTabela.addRow(new Object[]{
                            c.getNome(),
                            c.getDescricao()
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
