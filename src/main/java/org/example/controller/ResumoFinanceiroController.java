package org.example.controller;

import org.example.model.ResumoFinanceiro;
import org.example.service.ResumoFinanceiroService;
import org.example.service.ResumoFinanceiroServiceImpl;
import org.example.service.UsuarioService;
import org.example.service.UsuarioServiceImpl;
import org.example.util.ValidarEntrada;

import javax.swing.*;
import java.util.Date;

public class ResumoFinanceiroController {
    private final ResumoFinanceiroService resumoFinanceiroService = new ResumoFinanceiroServiceImpl();
    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    public ResumoFinanceiro gerarResumoFinanceiro(){
        int idUsuario = usuarioService.getUsuarioLogado().getId();
        return resumoFinanceiroService.gerarResumo(idUsuario);
    }

    public ResumoFinanceiro gerarResumoFiltrado(String dataInicioStr, String dataFimStr){

        Date dataInicio = null;
        Date dataFim = null;

        int idUsuario = usuarioService.getUsuarioLogado().getId();

        try {
            dataInicio = ValidarEntrada.formatarData(dataInicioStr);
            dataFim = ValidarEntrada.formatarData(dataFimStr);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return resumoFinanceiroService.gerarResumo(idUsuario, dataInicio, dataFim);

    }
}
