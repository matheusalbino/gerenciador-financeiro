package org.example.service;

import org.example.dao.*;
import org.example.factory.DAOFactory;
import org.example.model.Filtro;
import org.example.model.Transacao;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TransacaoServiceImpl implements TransacaoService {
    private final TransacaoDAO transacaoDAO = DAOFactory.getTransacaoDAO();

    @Override
    public void adicionarTransacao(Transacao transacao) {
        if (transacao.getValor() <= 0) {
            throw new IllegalArgumentException("Valor da transação deve ser positivo");
        }

        if (transacao.getData().compareTo(new Date()) > 0) {
            throw new IllegalArgumentException("Usuário não pode lançar transações futuras");
        }

        transacaoDAO.adicionarTransacao(transacao);
    }

    @Override
    public void removerTransacaoPorId(int idTransacao) {
        Transacao transacao = transacaoDAO.buscarTransacaoPorId(idTransacao);

        if (transacao == null) {
            throw new IllegalArgumentException("Transação não encontrada");
        }

        transacaoDAO.removerTransacao(transacao);
    }

    @Override
    public List<Transacao> buscarTransacoesPorFiltro(Filtro filtro) {

        List<Transacao> transacoes = transacaoDAO.buscarTransacoesDeUsuario(filtro.getUserID());

        if (transacoes.isEmpty()){
            return transacoes;
        }

        if (filtro.getDataInicio() != null) {
            transacoes = transacoes.stream().filter(transacao -> transacao.getData().compareTo(filtro.getDataInicio()) >= 0).toList();
        }
        if (filtro.getDataFinal() != null) {
            transacoes = transacoes.stream().filter(transacao -> transacao.getData().compareTo(filtro.getDataFinal()) <= 0).toList();
        }
        if (filtro.getCategoria() != null){
            transacoes = transacoes.stream().filter(transacao -> Objects.equals(transacao.getCategoria().getNome(), filtro.getCategoria())).toList();
        }
        if(filtro.getTipoTransacao() != null){
            transacoes = transacoes.stream().filter(transacao -> transacao.getTipo().getText().equals(filtro.getTipoTransacao())).toList();
        }

        return transacoes;
    }

    @Override
    public List<Transacao> buscarTransacoesPorIdUsuario(int idUsuario) {
        List<Transacao> transacoesUsuario = transacaoDAO.buscarTransacoesDeUsuario(idUsuario);

        if (transacoesUsuario.isEmpty()) {
            return null;
        }

        return transacoesUsuario;
    }

    @Override
    public int ultimaTransacao() {
        return transacaoDAO.buscarUltimaTransacao();
    }

    @Override
    public int proximoIdTransacao(int idUsuario) {
        return ultimaTransacao() + 1;
    }

}
