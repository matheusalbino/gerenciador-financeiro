package service;

import dao.TransacaoDAO;
import model.Transacao;

import java.util.List;

public class ResumoFinanceiroServiceImpl implements ResumoFinanceiroService {
    TransacaoDAO transacaoDAO;
    private double totalEntradas;
    private double totalDespesas;
    private double saldoAtual;

    @Override
    public double totalEntradas(int idUsuario) {
        List<Transacao> transacoes = transacaoDAO.buscarTransacoesDeUsuario(idUsuario);
        if (transacoes.size() <= 0){
            // sem entradas
            return totalEntradas = 0;
        }
        for (Transacao transacao : transacoes){
            if (transacao.getTipoTransacao().getText() == "RECEITA"){
                totalEntradas += transacao.getValor();
            }
        }
        return totalEntradas;
    }

    @Override
    public double totalDespesas(int idUsuario) {
        List<Transacao> transacoes = transacaoDAO.buscarTransacoesDeUsuario(idUsuario);
        if (transacoes.size() <= 0){
            // sem despesas
            return totalDespesas = 0;
        }
        for (Transacao transacao : transacoes){
            if (transacao.getTipoTransacao().getText() == "DESPESA"){
                totalDespesas += transacao.getValor();
            }
        }
        return totalDespesas;
    }

    @Override
    public double saldoAtual(int idUsuario) {
        return saldoAtual = totalEntradas(idUsuario) - totalDespesas(idUsuario);
    }

}
