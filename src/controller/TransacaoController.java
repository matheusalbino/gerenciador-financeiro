package controller;

import model.Categoria;
import model.Filtro;
import model.Transacao;
import model.enums.TipoTransacao;
import service.*;
import util.ValidarEntrada;

import java.util.Date;
import java.util.List;

public class TransacaoController {
    private final TransacaoService transacaoService = new TransacaoServiceImpl();
    private final CategoriaController categoriaController = new CategoriaController();
    private final UsuarioService usuarioService = new UsuarioServiceImpl();
    private final CategoriaService categoriaService = new CategoriaServiceImpl();

    public void cadastrarTransacao(String nomeCategoria, String valorStr,
                                   String dataStr, String descricao, String tipoTransacaoStr) {
        try {
            int idUsuario = this.usuarioService.getUsuarioLogado().getId();
            int idTransacao = this.transacaoService.proximoIdTransacao(idUsuario);
            Categoria categoria = this.categoriaService.buscarCategoriaPorNome(nomeCategoria, idUsuario);

            double valorTransacao = util.ValidarEntrada.validateDouble(valorStr);
            Date dataTransacao = util.ValidarEntrada.formatarData(dataStr);
            TipoTransacao tipo = TipoTransacao.getTrasancao(tipoTransacaoStr.toUpperCase());

            Transacao transacao = new Transacao(idTransacao, idUsuario, categoria, valorTransacao, dataTransacao, descricao, tipo);
            this.transacaoService.adicionarTransacao(transacao);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void removerTransacao(String idTransacao) {
        int id = ValidarEntrada.validateInteger(idTransacao);
        this.transacaoService.removerTransacaoPorId(id);
    }

    public Transacao buscarTransacao(String idTransacao) {
        int id = ValidarEntrada.validateInteger(idTransacao);
        return this.transacaoService.buscarTransacaoPorID(id);
    }

    public List<Transacao> listarTransacoesDoUsuario() {
        return this.transacaoService.buscarTransacoesPorIdUsuario(this.usuarioService.getUsuarioLogado().getId());
    }

    public List<Transacao> listarTransacoesComFiltro(String dataInicioStr, String dataFinalStr,
                                                     String nomeCategoriaStr, String tipoTransacaoStr) {
        try {
            // todo esse método aqui está dando erro pois os métodos de verificação nao aceitam null

            int idUsuario = usuarioService.getUsuarioLogado().getId();

            Date dataInicio = util.ValidarEntrada.formatarData(dataInicioStr);
            Date dataFinal = util.ValidarEntrada.formatarData(dataFinalStr);

            Categoria categoria = categoriaService.buscarCategoriaPorNome(nomeCategoriaStr, idUsuario);
            TipoTransacao tipo = TipoTransacao.getTrasancao(tipoTransacaoStr);

            Filtro filtro = new Filtro(idUsuario, dataInicio, dataFinal, categoria, tipo);

            return transacaoService.buscarTransacoesPorFiltro(filtro);

        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void editarTransacao(String nomeCategoria, String valorStr,
                                   String dataStr, String descricao, String tipoTransacaoStr){}
}
