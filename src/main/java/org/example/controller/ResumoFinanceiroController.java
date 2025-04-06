package org.example.controller;

import org.example.model.ResumoFinanceiro;
import org.example.service.ResumoFinanceiroService;
import org.example.service.ResumoFinanceiroServiceImpl;
import org.example.service.UsuarioService;
import org.example.service.UsuarioServiceImpl;
import org.example.util.ValidarEntrada;

import java.text.ParseException;
import java.util.Date;

public class ResumoFinanceiroController {
    private final ResumoFinanceiroService resumoFinanceiroService = new ResumoFinanceiroServiceImpl();
    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    public ResumoFinanceiro gerarResumoFinanceiro(){
        int idUsuario = usuarioService.getUsuarioLogado().getId();
        return resumoFinanceiroService.gerarResumo(idUsuario);
    }
    public ResumoFinanceiro gerarResumoFiltrado(String dataInicioStr, String dataFimStr){
        try {
            int idUsuario = usuarioService.getUsuarioLogado().getId();
            Date dataInicio = ValidarEntrada.formatarData(dataInicioStr);
            Date dataFim = ValidarEntrada.formatarData(dataFimStr);

            return resumoFinanceiroService.gerarResumo(idUsuario, dataInicio, dataFim);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
