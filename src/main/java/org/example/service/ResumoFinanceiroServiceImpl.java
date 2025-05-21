package org.example.service;

import org.example.dao.TransacaoDAO;
import org.example.factory.DAOFactory;
import org.example.model.ResumoFinanceiro;
import org.example.model.Transacao;
import java.util.Date;
import java.util.List;

public class ResumoFinanceiroServiceImpl implements ResumoFinanceiroService {
    private final TransacaoDAO transacaoDAO = DAOFactory.getTransacaoDAO();

    @Override
    public ResumoFinanceiro gerarResumo(int userID) {
        List<Transacao> transacoes = transacaoDAO.buscarTransacoesDeUsuario(userID);
        double totalEntradas = totalEntradas(transacoes);
        double totalDespesas = totalDespesas(transacoes);
        double saldoAtual = saldoAtual(totalEntradas, totalDespesas);

        return new ResumoFinanceiro(userID, saldoAtual, totalEntradas, totalDespesas);
    }

    @Override
    public ResumoFinanceiro gerarResumo(int userID, Date dataInicio, Date dataFinal) {
        List<Transacao> transacoes = transacaoDAO.buscarTransacoesDeUsuario(userID);

        if (transacoes.isEmpty()){
            return new ResumoFinanceiro(userID, 0, 0, 0);
        }
        if (dataInicio != null) {
            transacoes = transacoes.stream().filter(transacao -> transacao.getData().compareTo(dataInicio) >= 0).toList();
        }
        if (dataFinal != null) {
            transacoes = transacoes.stream().filter(transacao -> transacao.getData().compareTo(dataFinal) <= 0).toList();
        }


        double totalEntradas = totalEntradas(transacoes);
        double totalDespesas = totalDespesas(transacoes);
        double saldoAtual = saldoAtual(totalEntradas, totalDespesas);

        return new ResumoFinanceiro(userID, saldoAtual, totalEntradas, totalDespesas);
    }

    @Override
    public double totalEntradas(List<Transacao> transacoes) {
        double totalEntradas = 0;
        for (Transacao transacao : transacoes) {
            if (transacao.getTipo().getText().equals("RECEITA")) {
                totalEntradas += transacao.getValor();
            }
        }

        return totalEntradas;
    }

    @Override
    public double totalDespesas(List<Transacao> transacoes) {
        double totalDespesas = 0;
        for (Transacao transacao : transacoes) {
            if (transacao.getTipo().getText().equals("DESPESA")) {
                totalDespesas += transacao.getValor();
            }
        }

        return totalDespesas;
    }

    @Override
    public double saldoAtual(double totalEntradas, double totalDespesas) {
        return totalEntradas - totalDespesas;
    }

}
