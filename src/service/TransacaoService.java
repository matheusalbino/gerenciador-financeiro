package service;

import model.Filtro;
import model.Transacao;

import java.util.List;

public interface TransacaoService {
    // adicionar, remover, buscar por id, buscar com filtro

    public boolean adicionarTransacao(Transacao transacao);
    public boolean removerTransacaoPorId(int idTransacao);
    public Transacao buscarTransacaoPorID(int idTransacao);
    public List<Transacao> buscarTransacoesPorFiltro(Filtro filtro);
    public List<Transacao> buscarTransacoesPorIdUsuario(int idUsuario);

}
