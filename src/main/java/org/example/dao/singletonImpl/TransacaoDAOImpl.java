package org.example.dao.singletonImpl;

import org.example.dao.TransacaoDAO;
import org.example.model.Transacao;
import org.example.singleton.TransacaoSingleton;

import java.util.ArrayList;
import java.util.List;

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


    @Override
    public Transacao buscarTransacaoPorId(int idTransacao){
        for (Transacao transacao : TransacaoSingleton.getInstance().getTransacoes()){
            if (transacao.getId() == idTransacao){
                return transacao;
            }
        }
        return null;
    }

    @Override
    public int buscarUltimaTransacao(){
        return TransacaoSingleton.getInstance().getTransacoes().size();
    }

}

