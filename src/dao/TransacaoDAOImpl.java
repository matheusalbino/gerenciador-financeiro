package dao;

import model.Filtro;
import model.Transacao;

import java.util.ArrayList;
import java.util.List;

public class TransacaoDAOImpl implements TransacaoDAO {
    private static TransacaoDAOImpl instancia;
    private final List<Transacao> transacoes;

    private TransacaoDAOImpl() {
        transacoes = new ArrayList<>();
    }

    public static synchronized TransacaoDAOImpl getInstance() {
        if (instancia == null) {
            instancia = new TransacaoDAOImpl();
        }
        return instancia;
    }

    @Override
    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    @Override
    public void removerTransacao(Transacao transacao) { transacoes.remove(transacao); }

    @Override
    public List<Transacao> buscarTransacoesDeUsuario(int userid) {
        List<Transacao> transacoesUsuario = new ArrayList<>();

        for (Transacao transacao : transacoes) {
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

        for (Transacao transacao : transacoes){
            if ((transacao.getUserId() == filtro.getUserID())
                    && (transacao.getData().compareTo(filtro.getDataInicio()) >= 0)
                    && (transacao.getData().compareTo(filtro.getDataFinal()) <=0)
                    && (transacao.getCategoria().getNome() == filtro.getCategoria().getNome())
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
        for (Transacao transacao : transacoes){
            if (transacao.getId() == idTransacao){
                return transacao;
            }
        }
        return null;
    }

}

