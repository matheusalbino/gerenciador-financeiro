package org.example.service;

import org.example.dao.*;
import org.example.factory.DAOFactory;
import org.example.model.Categoria;
import org.example.model.Transacao;
import java.util.List;
import java.util.Objects;

public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaDAO categoriaDAO = DAOFactory.getCategoriaDAO();
    private final TransacaoDAO transacaoDAO = DAOFactory.getTransacaoDAO();


    @Override
    public void adicionarCategoria(Categoria categoria) {
        if (categoriaDAO.buscarCategoriaPorNome(categoria.getNome(), categoria.getUserid()) != null ){
            throw new IllegalArgumentException("Categoria ja existe pro usuário");
        }

        categoriaDAO.adicionarCategoria(categoria);
    }

    @Override
    public void removerCategoria(String nomeCategoria, int idUsuario) {
        Categoria categoria = categoriaDAO.buscarCategoriaPorNome(nomeCategoria, idUsuario);

        if (categoria == null) {
            throw new IllegalArgumentException("Categoria não encontrada");
        }

        List<Transacao> transacoesUsuario = transacaoDAO.buscarTransacoesDeUsuario(idUsuario);

        for (Transacao transacao : transacoesUsuario) {
            if (Objects.equals(transacao.getCategoria().getNome(), categoria.getNome())) {
                throw new IllegalArgumentException("Não é possível deletar categorias que já estão sendo usadas em alguma transação");
            }
        }

        categoriaDAO.removerCategoria(categoria);
    }


    @Override
    public void editarCategoria(String nomeCategoria, int idUsuario, String nome, String descricao) {
        Categoria categoria = buscarCategoriaPorNome(nomeCategoria, idUsuario);

        if (categoria == null) {
            throw new IllegalArgumentException("Categoria não encontrada");
        }

        categoriaDAO.editarCategoria(categoria, nome, descricao);
    }

    @Override
    public List<Categoria> listarCategoriasDeUsuario(int idUsuario) {
        return categoriaDAO.listarCategoriasDeUsuario(idUsuario);
    }

    @Override
    public Categoria buscarCategoriaPorNome(String nomeCategoria, int idUsuario) {

        if (nomeCategoria != null) {
            return categoriaDAO.buscarCategoriaPorNome(nomeCategoria, idUsuario);
        }
        return null;
    }

    @Override
    public int ultimaCategoria(int idUsuario) {
        return categoriaDAO.ultimaCategoria();

    }

    @Override
    public int proximoIdCategoria(int idUsuario) {
        return ultimaCategoria(idUsuario) + 1;
    }
}
