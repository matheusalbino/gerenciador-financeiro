package service;

import model.Filtro;
import model.ResumoFinanceiro;
import model.Transacao;

import java.util.Date;
import java.util.List;

public interface ResumoFinanceiroService {
    // calcular o total atual, total de crédito, total de débito, totais com filtro de data
    // calcular totais vindo de uma lista

    // calcular totais do usuario
    // somar todas as transações tipo crédito
    public double totalEntradas(List<Transacao> transacoes);

    // somar todos os débitos
    public double totalDespesas(List<Transacao> transacoes);

    // calcular quanto sobra do total
    public double saldoAtual(double totalEntradas, double totalDespesas);

    // gerar resumo com filtro
    public ResumoFinanceiro gerarResumo(int userID);
    public ResumoFinanceiro gerarResumo(int userID, Date dataInicio, Date dataFinal);
}
