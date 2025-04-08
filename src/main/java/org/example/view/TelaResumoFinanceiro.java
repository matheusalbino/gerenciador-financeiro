package org.example.view;

import org.example.controller.ResumoFinanceiroController;
import org.example.model.ResumoFinanceiro;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TelaResumoFinanceiro extends JPanel {
    private ResumoFinanceiroController resumoController = new ResumoFinanceiroController();

    private JLabel lblSaldoTotal;
    private JLabel lblTotalCreditos;
    private JLabel lblTotalDebitos;

    private JTextField txtDataInicio;
    private JTextField txtDataFim;
    private JButton btnAtualizarResumo;

    public TelaResumoFinanceiro() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel painelFiltro = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelFiltro.setBorder(new TitledBorder("Filtrar Período"));

        painelFiltro.add(new JLabel("Data Início (DD-MM-YYYY):"));
        txtDataInicio = new JTextField(10);
        painelFiltro.add(txtDataInicio);

        painelFiltro.add(new JLabel("Data Fim (DD-MM-YYYY):"));
        txtDataFim = new JTextField(10);
        painelFiltro.add(txtDataFim);

        btnAtualizarResumo = new JButton("Atualizar Resumo");
        painelFiltro.add(btnAtualizarResumo);

        JPanel painelResumoWrapper = new JPanel(new GridBagLayout());
        painelResumoWrapper.setBorder(new TitledBorder("Resumo Financeiro"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel painelResumo = new JPanel(new GridLayout(3, 1, 20, 20));
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Font valueFont = new Font("Arial", Font.BOLD, 22);

        JPanel saldoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblSaldoTexto = new JLabel("Saldo Total: ");
        lblSaldoTexto.setFont(labelFont);
        lblSaldoTotal = new JLabel("0.00");
        lblSaldoTotal.setFont(valueFont);
        lblSaldoTotal.setForeground(new Color(0, 102, 0));
        saldoPanel.add(lblSaldoTexto);
        saldoPanel.add(lblSaldoTotal);

        JPanel creditosPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblCreditosTexto = new JLabel("Total de Receitas: ");
        lblCreditosTexto.setFont(labelFont);
        lblTotalCreditos = new JLabel("0.00");
        lblTotalCreditos.setFont(valueFont);
        lblTotalCreditos.setForeground(new Color(0, 102, 204));
        creditosPanel.add(lblCreditosTexto);
        creditosPanel.add(lblTotalCreditos);

        JPanel debitosPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblDebitosTexto = new JLabel("Total de Despesas: ");
        lblDebitosTexto.setFont(labelFont);
        lblTotalDebitos = new JLabel("0.00");
        lblTotalDebitos.setFont(valueFont);
        lblTotalDebitos.setForeground(new Color(204, 0, 0));
        debitosPanel.add(lblDebitosTexto);
        debitosPanel.add(lblTotalDebitos);

        painelResumo.add(saldoPanel);
        painelResumo.add(creditosPanel);
        painelResumo.add(debitosPanel);

        painelResumoWrapper.add(painelResumo, gbc);

        btnAtualizarResumo.addActionListener(e -> {
            String dataInicio = txtDataInicio.getText().trim();
            String dataFim = txtDataFim.getText().trim();
            ResumoFinanceiro resumo = resumoController.gerarResumoFiltrado(dataInicio, dataFim);
            atualizarCamposResumo(resumo);
        });

        add(painelFiltro, BorderLayout.NORTH);
        add(painelResumoWrapper, BorderLayout.CENTER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                ResumoFinanceiro resumo = resumoController.gerarResumoFinanceiro();
                atualizarCamposResumo(resumo);
            }
        });

        ResumoFinanceiro resumo = resumoController.gerarResumoFinanceiro();
        atualizarCamposResumo(resumo);
    }

    private void atualizarCamposResumo(ResumoFinanceiro resumo) {
        if (resumo != null) {
            lblSaldoTotal.setText(String.valueOf(resumo.getSaldoTotal()));
            lblTotalCreditos.setText(String.valueOf(resumo.getTotalCreditos()));
            lblTotalDebitos.setText(String.valueOf(resumo.getTotalDebitos()));
        } else {
            lblSaldoTotal.setText("0.00");
            lblTotalCreditos.setText("0.00");
            lblTotalDebitos.setText("0.00");
        }
    }
}