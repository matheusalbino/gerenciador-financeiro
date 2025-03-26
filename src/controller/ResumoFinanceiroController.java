package controller;

import model.ResumoFinanceiro;
import service.ResumoFinanceiroService;
import service.ResumoFinanceiroServiceImpl;

import java.util.Date;

public class ResumoFinanceiroController {
    private final ResumoFinanceiroService resumoFinanceiroService = new ResumoFinanceiroServiceImpl();

    public ResumoFinanceiro gerarResumo(int idUsuario){
        // verificar id

        return resumoFinanceiroService.gerarResumo(idUsuario);
    }

    public ResumoFinanceiro gerarResumoFiltrado(int idUsuario, Date dataInicio, Date dataFim){
        // checar id e datas

        return resumoFinanceiroService.gerarResumo(idUsuario, dataInicio, dataFim);
    }

}
