package dao;

import model.Filtro;
import model.Transacao;
import singleton.TransacaoSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransacaoDAOImpl implements TransacaoDAO {

    @Override
    public void adicionarTransacao(Transacao transacao) {
        TransacaoSingleton.getInstance().getTransacoes().add(transacao);
    }

    @Override
    public void removerTransacao(Transacao transacao) { TransacaoSingleton.getInstance().getTransacoes().remove(transacao); }

    @Override
    public List<Transacao> buscarTransacoesDeUsuario(int userid) {
        List<Transacao> transacoesUsuario = new ArrayList<>();

        for (Transacao transacao : TransacaoSingleton.getInstance().getTransacoes()) {
            if (transacao.getUserId() == userid) {
                transacoesUsuario.add(transacao);
            }
        }

        return transacoesUsuario;
    }


    // buscar transações com filtro
    @Override
    public List<Transacao> buscarTransacoesComFiltro(Filtro filtro){
        List<Transacao> transacoesFiltradas = new ArrayList<>();

        for (Transacao transacao : TransacaoSingleton.getInstance().getTransacoes()){
            if ((transacao.getUserId() == filtro.getUserID())
                    && (transacao.getData().compareTo(filtro.getDataInicio()) >= 0)
                    && (transacao.getData().compareTo(filtro.getDataFinal()) <= 0)
                    && (Objects.equals(transacao.getCategoria().getNome(), filtro.getCategoria().getNome()))
                    && (transacao.getTipoTransacao() == filtro.getTipoTransacao())
            )
            {
                transacoesFiltradas.add(transacao);
            }
        }

        return transacoesFiltradas;
    }

    @Override
    public Transacao buscarTransacaoPorId(int idTransacao){
        for (Transacao transacao : TransacaoSingleton.getInstance().getTransacoes()){
            if (transacao.getId() == idTransacao){
                return transacao;
            }
        }
        return null;
    }

}

