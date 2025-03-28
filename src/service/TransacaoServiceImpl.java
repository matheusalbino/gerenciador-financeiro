package service;

import dao.*;
import model.Filtro;
import model.Transacao;

import java.util.Date;
import java.util.List;

public class TransacaoServiceImpl implements TransacaoService {
    private final TransacaoDAO transacaoDAO = new TransacaoDAOImpl();

    @Override
    public void adicionarTransacao(Transacao transacao) {
        if (transacao.getValor() <= 0) {
            throw new IllegalArgumentException("Valor da transação deve ser positivo");
        }

        if (transacao.getData().compareTo(new Date()) > 0) {
            throw new IllegalArgumentException("Usuário não pode lançar transações futuras");
        }

        util.ValidarEntrada.validarStringNuloOuVazia(transacao.getDescricao(), "Insira uma descrição");

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
        if (filtro.getDataInicio().after(filtro.getDataFinal())) {
            throw new IllegalArgumentException("Intervalo de datas não permitido");
        }

        return transacaoDAO.buscarTransacoesComFiltro(filtro);
    }

    @Override
    public List<Transacao> buscarTransacoesPorIdUsuario(int idUsuario) {
        List<Transacao> transacoesUsuario = transacaoDAO.buscarTransacoesDeUsuario(idUsuario);

        if (transacoesUsuario.isEmpty()) {
            throw new IllegalArgumentException("Sem transações de usuario");
        }

        return transacoesUsuario;
    }

    @Override
    public Transacao buscarTransacaoPorID(int idTransacao) {
        Transacao transacao = transacaoDAO.buscarTransacaoPorId(idTransacao);

        if (transacao == null) {
            throw new IllegalArgumentException("Transação não encontrada");
        }

        return transacao;
    }

    @Override
    public int ultimaTransacao(int idUsuario) {
        List<Transacao> listaTransacao = buscarTransacoesPorIdUsuario(idUsuario);

        if (listaTransacao.isEmpty()) {
            return 0;
        }

        return listaTransacao.getLast().getId();
    }

    @Override
    public int proximoIdTransacao(int idUsuario) {
        return ultimaTransacao(idUsuario) + 1;
    }

}
