package org.example.dao;

import org.example.model.Filtro;
import org.example.model.Transacao;
import org.example.singleton.TransacaoSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        // todo
        for (Transacao transacao : TransacaoSingleton.getInstance().getTransacoes()) {
            if (transacao.getUserId() == userid) {
                transacoesUsuario.add(transacao);
            }
        }

        return transacoesUsuario;
    }


    public List<Transacao> buscarTransacoesComFiltro(Filtro filtro) {
        return TransacaoSingleton.getInstance().getTransacoes().stream()
                .filter(transacao -> transacao.getUserId() == filtro.getUserID())
                .filter(transacao -> transacao.getData().compareTo(filtro.getDataInicio()) >= 0)
                .filter(transacao -> transacao.getData().compareTo(filtro.getDataFinal()) <= 0)
                .filter(transacao -> transacao.getCategoria().getNome() == filtro.getCategoria().getNome())
                .filter(transacao -> transacao.getTipo() == filtro.getTipoTransacao())
                .collect(Collectors.toList());
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

