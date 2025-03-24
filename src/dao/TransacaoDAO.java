package dao;

import model.Filtro;
import model.Transacao;

import java.util.List;

public interface TransacaoDAO {
    public void adicionarTransacao(Transacao transacao);
    public void removerTransacao(Transacao transacao);
    public List<Transacao> buscarTransacoesDeUsuario(int userid);
    public List<Transacao> buscarTransacoesComFiltro(Filtro filtro);
    public Transacao buscarTransacaoPorId(int idTransacao);
}
