package controller;

import model.Categoria;
import model.Filtro;
import model.Transacao;
import model.enums.TipoTransacao;
import service.TransacaoService;
import service.TransacaoServiceImpl;

import java.util.Date;
import java.util.List;

public class TransacaoController {
    private final TransacaoService transacaoService = new TransacaoServiceImpl();
    private final CategoriaController categoriaController = new CategoriaController();

    public void cadastrarTransacao(int id, int idUsuario, int idCategoria, double valor,
                                   Date data, String descricao, TipoTransacao tipo)
    {
        // verificar campos


        // buscar categoria
        Categoria categoria = categoriaController.buscarCategoria(idCategoria, idUsuario);
        // criar transacao
        Transacao transacao = new Transacao(id, idUsuario, categoria, valor, data, descricao, tipo);

        transacaoService.adicionarTransacao(transacao);
    }

    public boolean removerTransacao(int idTransacao){
        // checar id


        return transacaoService.removerTransacaoPorId(idTransacao);
    }

    public Transacao buscarTransacao(int idTransacao){
        // verificar not null not zero


        return transacaoService.buscarTransacaoPorID(idTransacao);
    }

    public List<Transacao> listarTransacoesDoUsuario(int idUsuario){
        // verificar not zero or null


        return transacaoService.buscarTransacoesPorIdUsuario(idUsuario);
    }

    // todo filtro ainda a implementar, esse metodo irá receber dados soltos e criará o filtro necessário
    public List<Transacao> listarTransacoesFiltradas(Filtro filtro){
        // verificar campos
        // criar filtro


        return transacaoService.buscarTransacoesPorFiltro(filtro);
    }

}
