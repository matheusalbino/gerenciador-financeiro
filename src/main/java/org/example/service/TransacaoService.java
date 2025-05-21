package org.example.service;

import org.example.model.Filtro;
import org.example.model.Transacao;
import java.util.List;

public interface TransacaoService {
    void adicionarTransacao(Transacao transacao);
    void removerTransacaoPorId(int idTransacao);
    List<Transacao> buscarTransacoesPorFiltro(Filtro filtro);
    List<Transacao> buscarTransacoesPorIdUsuario(int idUsuario);
    int ultimaTransacao();
    int proximoIdTransacao(int idUsuario);
}
