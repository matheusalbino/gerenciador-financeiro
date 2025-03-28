package controller;

import model.ResumoFinanceiro;
import service.ResumoFinanceiroService;
import service.ResumoFinanceiroServiceImpl;

import java.util.Date;

public class ResumoFinanceiroController {
    private final ResumoFinanceiroService resumoFinanceiroService = new ResumoFinanceiroServiceImpl();

    public ResumoFinanceiro gerarResumo(int idUsuario){
        // verificar id
        util.ValidarEntrada.validarInt(idUsuario, "Id usuario não permitido");

        return resumoFinanceiroService.gerarResumo(idUsuario);
    }

    public ResumoFinanceiro gerarResumoFiltrado(int idUsuario, Date dataInicio, Date dataFim){
        // checar id e datas
        util.ValidarEntrada.validarInt(idUsuario, "Id usuario não permitido");
        if (dataInicio != null){ util.ValidarEntrada.validarData(dataInicio, "Data de inicio inválida"); }
        if (dataFim != null){ util.ValidarEntrada.validarData(dataFim, "Data final inválida"); }

        return resumoFinanceiroService.gerarResumo(idUsuario, dataInicio, dataFim);
    }

}
