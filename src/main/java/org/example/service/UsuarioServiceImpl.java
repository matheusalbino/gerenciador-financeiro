package org.example.service;

import org.example.dao.*;
import org.example.factory.DAOFactory;
import org.example.model.Categoria;
import org.example.model.Transacao;
import org.example.model.Usuario;
import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {
    UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
    private final CategoriaDAO categoriaDAO = DAOFactory.getCategoriaDAO();
    private final TransacaoDAO transacaoDAO = DAOFactory.getTransacaoDAO();

    @Override
    public void registrarUsuario(Usuario usuario) {
        String username = usuario.getLogin().toLowerCase().strip();

        for (char c : username.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '_') {
                throw new IllegalArgumentException("Username inválido. Apenas (a-z, 0-9 e _)");
            }
        }

        if (usuarioDAO.buscarPorNome(usuario.getLogin()) != null) {
            throw new IllegalArgumentException("Nome de usuário já existe");
        }

        usuarioDAO.cadastrarUsuario(usuario);
    }

    @Override
    public void removerUsuario(int idUsuarioARemover) {
        Usuario usuario = buscarUsuarioPorID(idUsuarioARemover);

        if (usuario == null) {
            throw new IllegalArgumentException("ID de usuario não encontrado");
        }

        List<Transacao> transacoesUsuario = transacaoDAO.buscarTransacoesDeUsuario(idUsuarioARemover);
        List<Categoria> categoriasUsuario = categoriaDAO.listarCategoriasDeUsuario(idUsuarioARemover);

        if (!transacoesUsuario.isEmpty()) {
            for (Transacao transacao : transacoesUsuario) {
                transacaoDAO.removerTransacao(transacao);
            }
        }

        if (!categoriasUsuario.isEmpty()) {
            for (Categoria categoria : categoriasUsuario) {
                categoriaDAO.removerCategoria(categoria);
            }
        }

        usuarioDAO.removerUsuario(usuario);
    }

    @Override
    public Usuario buscarUsuarioPorNome(String username) {
        return usuarioDAO.buscarPorNome(username);
    }

    @Override
    public Usuario buscarUsuarioPorID(int userID) {
        Usuario usuario = usuarioDAO.buscarUsuarioPorId(userID);

        if (usuario == null) {
            throw new IllegalArgumentException("ID de usuário não encontrado");
        }

        return usuario;
    }

    @Override
    public Usuario validarLogin(String username, String password) {
        Usuario usuario = buscarUsuarioPorNome(username);

        if (usuario == null){
            throw new IllegalArgumentException("Username não encontrado");
        }

        if (!usuario.getSenha().equals(password)){
            throw new IllegalArgumentException("Senha incorreta");
        }

        this.usuarioDAO.logarUsuario(usuario);

        return usuario;
    }

    @Override
    public void logOutUsuario(){

        if (this.getUsuarioLogado() == null){
            throw new IllegalArgumentException("Usuario não encontrado");
        }

        usuarioDAO.logoutUsuario();
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return this.usuarioDAO.listarUsuarios();
    }

    @Override
    public Usuario getUsuarioLogado() {
        Usuario usuario = this.usuarioDAO.getUsuarioLogado();

        if (usuario == null){
            throw new IllegalArgumentException("Nenhum usuario logado");
        }

        return usuario;
    }

    @Override
    public int ultimoIdUsuario() {
        List<Usuario> listaUsuarios = listarUsuarios();

        if (listaUsuarios.isEmpty()) {
            return 0;
        }

        return listaUsuarios.getLast().getId();
    }

    @Override
    public int proximoIdUsuario() {
        return ultimoIdUsuario() + 1;
    }
}
