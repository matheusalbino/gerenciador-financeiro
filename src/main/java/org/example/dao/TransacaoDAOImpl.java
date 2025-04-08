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

        System.out.println("TrDAO Filtro: " + filtro.getDataInicio().toString() + filtro.getDataFinal().toString() + filtro.getTipoTransacao() + filtro.getCategoria());

        List<Transacao> transacoes = TransacaoSingleton.getInstance().getTransacoes();

        if (filtro.getDataInicio() != null) {
            transacoes = transacoes.stream().filter(transacao -> transacao.getData().compareTo(filtro.getDataInicio()) >= 0).toList();
        }
        if (filtro.getDataFinal() != null) {
            transacoes = transacoes.stream().filter(transacao -> transacao.getData().compareTo(filtro.getDataFinal()) <= 0).toList();
        }
        if (filtro.getCategoria() != null){
            transacoes = transacoes.stream().filter(transacao -> transacao.getCategoria().getNome() == filtro.getCategoria()).toList();
        }
        if(filtro.getTipoTransacao() != null){
            transacoes = transacoes.stream().filter(transacao -> transacao.getTipo().getText() == filtro.getTipoTransacao()).toList();
        }

        return transacoes;
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

