package dao;

import model.Transacao;

import java.util.List;

public interface TransacaoDAO {
    public void adicionarTransacao(Transacao transacao);
    public boolean removerTransacao(int id);
    public List<Transacao> buscarTransacoesDeUsuario(int userid);
    // public List<Transacao> buscarTransacoesComFiltro(int userid, Filtro filtro);

}
