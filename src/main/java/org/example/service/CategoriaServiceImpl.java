package org.example.service;

import org.example.dao.*;
import org.example.model.Categoria;
import org.example.model.Transacao;

import java.util.List;

public class CategoriaServiceImpl implements CategoriaService {
    UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    private final CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
    private final TransacaoDAO transacaoDAO = new TransacaoDAOImpl();


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
            if (transacao.getCategoria().getNome() == categoria.getNome()) {
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

        List<Categoria> categorias = categoriaDAO.listarCategoriasDeUsuario(idUsuario);

        if (categorias == null || categorias.isEmpty()) {
            throw new IllegalArgumentException("Usuario sem categorias");
        }

        return categorias;
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
        List<Categoria> listaCategorias = categoriaDAO.listarCategoriasDeUsuario(idUsuario);

        if (listaCategorias.isEmpty()) {
            return 0;
        }

        return listaCategorias.getLast().getId();
    }

    @Override
    public int proximoIdCategoria(int idUsuario) {
        return ultimaCategoria(idUsuario) + 1;
    }
}
