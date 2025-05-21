package org.example.view;

import javax.swing.*;
import java.awt.*;

public class JanelaPrincipal extends JFrame {

    public JanelaPrincipal() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Home", new TelaHome());
        tabbedPane.addTab("Transação", new TelaTransacao());
        tabbedPane.addTab("Categorias", new TelaCategoria());
        tabbedPane.addTab("Resumo", new TelaResumoFinanceiro());
        tabbedPane.addTab("Histórico/Filtro", new TelaHistoricoFiltro());


        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(1290, 760));

        this.add(tabbedPane, BorderLayout.CENTER);

        this.setLocationRelativeTo(getOwner());

    }
}
