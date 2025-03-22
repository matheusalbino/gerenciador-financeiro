package dao;

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
    public boolean removerTransacao(int id) {
        for (Transacao transacao : transacoes) {
            if (transacao.getId() == id) {
                transacoes.remove(transacao);
                return true;
            }
        }
        return false;
    }


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
}

