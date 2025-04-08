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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

public class TelaHistoricoFiltro extends JPanel {
    private TransacaoController transacaoController = new TransacaoController();
    private CategoriaController categoriaController = new CategoriaController();

    private JTable tabelaHistorico;
    private DefaultTableModel modeloTabela;

    // Campos para filtro
    private JTextField txtDataInicio;
    private JTextField txtDataFim;
    private JComboBox<String> cbTipo;
    private JComboBox<String> cbCategoria;
    private JButton btnFiltrar;

    public TelaHistoricoFiltro() {
        setLayout(new BorderLayout(10, 10));

        JPanel painelFiltro = new JPanel(new GridBagLayout());
        painelFiltro.setBorder(new LineBorder(Color.GRAY));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelFiltro.add(new JLabel("Data Início (DD-MM-YYYY):"), gbc);
        txtDataInicio = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        painelFiltro.add(txtDataInicio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painelFiltro.add(new JLabel("Data Final (DD-MM-YYYY):"), gbc);
        txtDataFim = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        painelFiltro.add(txtDataFim, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        painelFiltro.add(new JLabel("Tipo:"), gbc);
        String[] opcoesTipo = {"Todos", "Receita", "Despesa"};
        cbTipo = new JComboBox<>(opcoesTipo);
        gbc.gridx = 1;
        gbc.gridy = 2;
        painelFiltro.add(cbTipo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        painelFiltro.add(new JLabel("Categoria:"), gbc);
        cbCategoria = new JComboBox<>();
        populateCategorias();
        gbc.gridx = 1;
        gbc.gridy = 3;
        painelFiltro.add(cbCategoria, gbc);

        btnFiltrar = new JButton("Filtrar");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        painelFiltro.add(btnFiltrar, gbc);

        String[] colunas = {"ID", "Valor", "Tipo", "Categoria", "Data", "Descrição"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaHistorico = new JTable(modeloTabela);

        tabelaHistorico.getColumnModel().getColumn(0).setMinWidth(0);
        tabelaHistorico.getColumnModel().getColumn(0).setMaxWidth(0);
        tabelaHistorico.getColumnModel().getColumn(0).setWidth(0);
        JScrollPane scrollTabela = new JScrollPane(tabelaHistorico);
        scrollTabela.setBorder(new LineBorder(Color.GRAY));

        add(painelFiltro, BorderLayout.NORTH);
        add(scrollTabela, BorderLayout.CENTER);


        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarTransacoes();
            }
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                atualizarTabela();
                populateCategorias();
            }
        });

        atualizarTabela();
    }


    private void populateCategorias() {
        cbCategoria.removeAllItems();
        cbCategoria.addItem("Todos");
        try {
            List<Categoria> categorias = categoriaController.listarCategoriasDoUsuario();
            if (categorias != null && !categorias.isEmpty()) {
                for (Categoria c : categorias) {
                    cbCategoria.addItem(c.getNome());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar categorias: " + e.getMessage());
        }
    }


    private void atualizarTabela() {
        modeloTabela.setRowCount(0);

        List<Transacao> transacoes = transacaoController.listarTransacoesDoUsuario();
        if (transacoes != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            for (Transacao t : transacoes) {
                modeloTabela.addRow(new Object[]{
                        t.getId(),
                        t.getValor(),
                        t.getTipo(),
                        t.getCategoria().getNome(),
                        formatter.format(t.getData()),
                        t.getDescricao()
                });
            }
        }
    }


    private void filtrarTransacoes() {
        String dataInicio = txtDataInicio.getText().trim();
        String dataFim = txtDataFim.getText().trim();
        String tipo = cbTipo.getSelectedItem().toString();
        String categoria = cbCategoria.getSelectedItem().toString();
        List<Transacao> transacoes = List.of();

        System.out.println("THF: " + tipo + " " + categoria);
        System.out.println("THF: " + dataInicio + " " +  dataFim);

        try {
            transacoes = transacaoController.listarTransacoesComFiltro(dataInicio, dataFim, categoria, tipo);
        }catch (Exception e){
            System.out.println("THF: " + e.getMessage());
        }

        modeloTabela.setRowCount(0);

        if (transacoes != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            for (Transacao t : transacoes) {
                modeloTabela.addRow(new Object[]{
                        t.getId(),
                        t.getValor(),
                        t.getTipo(),
                        t.getCategoria().getNome(),
                        formatter.format(t.getData()),
                        t.getDescricao()
                });
            }
        }
    }
}
