package service;

import model.Filtro;
import model.Transacao;

import java.util.List;

public interface TransacaoService {
    void adicionarTransacao(Transacao transacao);
    void removerTransacaoPorId(int idTransacao);
    Transacao buscarTransacaoPorID(int idTransacao);
    List<Transacao> buscarTransacoesPorFiltro(Filtro filtro);
    List<Transacao> buscarTransacoesPorIdUsuario(int idUsuario);
    int ultimaTransacao(int idUsuario);
    int proximoIdTransacao(int idUsuario);
}
