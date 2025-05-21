package org.example.dao;

import org.example.model.Transacao;

import java.util.List;

public interface TransacaoDAO {
    void adicionarTransacao(Transacao transacao);
    void removerTransacao(Transacao transacao);
    List<Transacao> buscarTransacoesDeUsuario(int userid);
    Transacao buscarTransacaoPorId(int idTransacao);
    int buscarUltimaTransacao();
}
