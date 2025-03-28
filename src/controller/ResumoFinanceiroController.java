package controller;

import model.ResumoFinanceiro;
import service.ResumoFinanceiroService;
import service.ResumoFinanceiroServiceImpl;
import service.UsuarioService;
import service.UsuarioServiceImpl;

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
            Date dataInicio = util.ValidarEntrada.formatarData(dataInicioStr);
            Date dataFim = util.ValidarEntrada.formatarData(dataFimStr);

            return resumoFinanceiroService.gerarResumo(idUsuario, dataInicio, dataFim);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
