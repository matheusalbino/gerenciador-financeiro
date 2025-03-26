package service;

import dao.TransacaoDAO;
import dao.TransacaoDAOImpl;
import model.Filtro;
import model.ResumoFinanceiro;
import model.Transacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResumoFinanceiroServiceImpl implements ResumoFinanceiroService {
    TransacaoDAO transacaoDAO = new TransacaoDAOImpl();
    private double totalEntradas;
    private double totalDespesas;

    public ResumoFinanceiro gerarResumo(int userID) {
        List<Transacao> transacoes = transacaoDAO.buscarTransacoesDeUsuario(userID);
        double totalEntradas = totalEntradas(transacoes);
        double totalDespesas = totalDespesas(transacoes);
        double saldoAtual = saldoAtual(totalEntradas, totalDespesas);

        return new ResumoFinanceiro(userID, saldoAtual, totalEntradas, totalDespesas);
    }

    @Override
    public ResumoFinanceiro gerarResumo(int userID, Date dataInicio, Date dataFinal) {
        // instanciar filtro
        List<Transacao> transacoes = transacaoDAO.buscarTransacoesDeUsuario(userID);
        List<Transacao> transacoesFiltradas = new ArrayList<>();
        for (Transacao transacao : transacoes){
            if (transacao.getData().after(dataInicio) && transacao.getData().before(dataFinal)){
                transacoesFiltradas.add(transacao);
            }
        }

        double totalEntradas = totalEntradas(transacoesFiltradas);
        double totalDespesas = totalDespesas(transacoesFiltradas);
        double saldoAtual = saldoAtual(totalEntradas, totalDespesas);

        return new ResumoFinanceiro(userID, saldoAtual, totalEntradas, totalDespesas);
    }


    @Override
    public double totalEntradas(List<Transacao> transacoes) {
        if (transacoes.size() <= 0) {
            // sem entradas
            return totalEntradas = 0;
        }
        for (Transacao transacao : transacoes) {
            if (transacao.getTipoTransacao().getText() == "RECEITA") {
                totalEntradas += transacao.getValor();
            }
        }
        return totalEntradas;
    }

    @Override
    public double totalDespesas(List<Transacao> transacoes) {
        if (transacoes.size() <= 0) {
            // sem despesas
            return totalDespesas = 0;
        }
        for (Transacao transacao : transacoes) {
            if (transacao.getTipoTransacao().getText() == "DESPESA") {
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
