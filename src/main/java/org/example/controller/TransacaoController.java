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
import java.util.Objects;

public class TransacaoController {
    private final TransacaoService transacaoService = new TransacaoServiceImpl();
    private final UsuarioService usuarioService = new UsuarioServiceImpl();
    private final CategoriaService categoriaService = new CategoriaServiceImpl();

    public void cadastrarTransacao(String nomeCategoria, String valorStr, String dataStr, String descricao, String tipoTransacaoStr) {
        try {
            int idUsuario = this.usuarioService.getUsuarioLogado().getId();
            int idTransacao = this.transacaoService.proximoIdTransacao(idUsuario);
            Categoria categoria = this.categoriaService.buscarCategoriaPorNome(nomeCategoria, idUsuario);
            double valorTransacao = ValidarEntrada.validateDouble(valorStr);

            Date dataTransacao = ValidarEntrada.formatarData(dataStr);

            TipoTransacao tipo = TipoTransacao.getTransacao(tipoTransacaoStr.toUpperCase());
            ValidarEntrada.validarStringNuloOuVazia(descricao, "Insira uma descricao");

            Transacao transacao = new Transacao(idTransacao, idUsuario, categoria, valorTransacao, dataTransacao, descricao, tipo);
            this.transacaoService.adicionarTransacao(transacao);

        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                JOptionPane.showMessageDialog(null, e.getMessage() + "Data Invalida", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void removerTransacao(String idTransacao) {
        int id = ValidarEntrada.validateInteger(idTransacao);
        this.transacaoService.removerTransacaoPorId(id);
    }

    public List<Transacao> listarTransacoesDoUsuario() {
        return this.transacaoService.buscarTransacoesPorIdUsuario(this.usuarioService.getUsuarioLogado().getId());
    }

    public List<Transacao> listarTransacoesComFiltro(String dataInicioStr, String dataFinalStr, String nomeCategoriaStr, String tipoTransacaoStr) {
        try {
            int idUsuario = usuarioService.getUsuarioLogado().getId();

            Date dataInicio = null;
            Date dataFinal = null;
            String categoria = null;
            String tipo = null;

            if (!dataInicioStr.isBlank()) {
                dataInicio = ValidarEntrada.formatarData(dataInicioStr);
            }
            if (!dataFinalStr.isBlank()) {
                dataFinal = ValidarEntrada.formatarData(dataFinalStr);
            }


            if (!Objects.equals(nomeCategoriaStr, "Todos")) {
                categoria = categoriaService.buscarCategoriaPorNome(nomeCategoriaStr, idUsuario).getNome();
            }
            if (!Objects.equals(tipoTransacaoStr, "Todos")) {
                tipo = Objects.requireNonNull(TipoTransacao.getTransacao(tipoTransacaoStr)).getText();
            }

            Filtro filtro = new Filtro(idUsuario, dataInicio, dataFinal, categoria, tipo);

            return transacaoService.buscarTransacoesPorFiltro(filtro);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }
}