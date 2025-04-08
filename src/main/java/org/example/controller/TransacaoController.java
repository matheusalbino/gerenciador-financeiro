package org.example.controller;

import org.example.model.Categoria;
import org.example.model.Filtro;
import org.example.model.Transacao;
import org.example.model.enums.TipoTransacao;
import org.example.service.*;
import org.example.util.ValidarEntrada;

import javax.swing.*;
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

            double valorTransacao = ValidarEntrada.validateDouble(valorStr);

            Date dataTransacao = ValidarEntrada.formatarData(dataStr);
            TipoTransacao tipo = TipoTransacao.getTrasancao(tipoTransacaoStr.toUpperCase());
            ValidarEntrada.validarStringNuloOuVazia(descricao, "Insira uma descricao");

            Transacao transacao = new Transacao(idTransacao, idUsuario, categoria, valorTransacao, dataTransacao, descricao, tipo);
            this.transacaoService.adicionarTransacao(transacao);
            System.out.println("Transação cadastrada.");

        } catch (Exception e) {
            // todo colocar caixa de erro em todos os controllers, ver onde é apropriado
            if (e instanceof NullPointerException) {
                JOptionPane.showMessageDialog(null, "Data Invalida", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
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

            int idUsuario = usuarioService.getUsuarioLogado().getId();

            Date dataInicio = null;
            Date dataFinal = null;
            String categoria = null;
            String tipo = null;


            if (!dataInicioStr.isBlank()){
                dataInicio = ValidarEntrada.formatarData(dataInicioStr);
            }
            if (!dataFinalStr.isBlank()){
                dataFinal = ValidarEntrada.formatarData(dataFinalStr);
            }


            if (nomeCategoriaStr != "Todos"){
                categoria = categoriaService.buscarCategoriaPorNome(nomeCategoriaStr, idUsuario).getNome();
            }
            if (tipoTransacaoStr != "Todos") {
                tipo = TipoTransacao.getTrasancao(tipoTransacaoStr).getText();
            }

            // System.out.println("TC datas: " + dataInicio + dataFinal);

            Filtro filtro = new Filtro(idUsuario, dataInicio, dataFinal, categoria, tipo);

            //System.out.println("TrController Filtro: " + filtro.getDataInicio().toString() + filtro.getDataFinal().toString() + filtro.getTipoTransacao() + filtro.getCategoria());


            return transacaoService.buscarTransacoesPorFiltro(filtro);

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    public void editarTransacao(String nomeCategoria, String valorStr,
                                   String dataStr, String descricao, String tipoTransacaoStr){}
}
