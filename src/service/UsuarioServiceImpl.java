package service;

import dao.*;
import model.Categoria;
import model.Transacao;
import model.Usuario;

import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {
    UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    TransacaoDAO transacaoDAO = new TransacaoDAOImpl();
    CategoriaDAO categoriaDAO = new CategoriaDAOImpl();

    @Override
    public void registrarUsuario(Usuario usuario){
        if (usuarioDAO.buscarUsuarioPorId(usuario.getId()) != null){
            throw new IllegalArgumentException("Id de usuário já existente");
        }

        // se é um username no formato válido
        String username = usuario.getLogin().toLowerCase().strip();
        for (char c : username.toCharArray()){
            if (!Character.isLetterOrDigit(c) && c != '_'){
                throw new IllegalArgumentException("Username inválido. Apenas (a-z, 0-9 e _)");
            }
        }

        if (usuarioDAO.buscarPorLogin(usuario.getLogin()) != null){
            throw new IllegalArgumentException("Nome de usuário já existe");
        }

        usuarioDAO.cadastrarUsuario(usuario);
    }

    @Override
    public void removerUsuario(int idUsuarioARemover) {
        Usuario usuario = buscarUsuarioPorID(idUsuarioARemover);
        if (usuario == null){
            throw new IllegalArgumentException("ID de usuario não encontrado");
        }

        // buscar as categorias e transações do usuario a ser removido
        List<Transacao> transacoesUsuario = transacaoDAO.buscarTransacoesDeUsuario(idUsuarioARemover);
        List<Categoria> categoriasUsuario = categoriaDAO.listarCategoriasDeUsuario(idUsuarioARemover);

        if (transacoesUsuario != null){
            // remover transações do usuário
            for (Transacao transacao : transacoesUsuario){
                transacaoDAO.removerTransacao(transacao);
            }
        }

        if (categoriasUsuario != null){
            // remover categorias do usuario
            for (Categoria categoria : categoriasUsuario){
                categoriaDAO.removerCategoria(categoria);
            }
        }

        // remover usuario
        usuarioDAO.removerUsuario(usuario);
    }

    @Override
    public Usuario buscarUsuarioPorLogin(String username) {
        Usuario usuario = usuarioDAO.buscarPorLogin(username);
        if (usuario == null){
            throw new IllegalArgumentException("Username não encontrado");
        }

        return usuario;
    }

    @Override
    public Usuario buscarUsuarioPorID(int userID) {
        Usuario usuario = usuarioDAO.buscarUsuarioPorId(userID);
        if (usuario == null){
            throw new IllegalArgumentException("ID de usuário não encontrado");
        }

        return usuario;
    }

    @Override
    public boolean validarLogin(String usuario, String senha) {
        return false;
    }

}
