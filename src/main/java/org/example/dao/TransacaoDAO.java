package org.example.dao;

import org.example.model.Filtro;
import org.example.model.Transacao;

import java.util.List;

public interface TransacaoDAO {
    public void adicionarTransacao(Transacao transacao);
    public void removerTransacao(Transacao transacao);
    public List<Transacao> buscarTransacoesDeUsuario(int userid);
    public Transacao buscarTransacaoPorId(int idTransacao);
}
