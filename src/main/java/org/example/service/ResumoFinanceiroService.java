package org.example.service;

import org.example.model.ResumoFinanceiro;
import org.example.model.Transacao;

import java.util.Date;
import java.util.List;

public interface ResumoFinanceiroService {
    double totalEntradas(List<Transacao> transacoes);
    double totalDespesas(List<Transacao> transacoes);
    double saldoAtual(double totalEntradas, double totalDespesas);
    ResumoFinanceiro gerarResumo(int userID);
    ResumoFinanceiro gerarResumo(int userID, Date dataInicio, Date dataFinal);
}
