package service;

import model.Usuario;

public interface UsuarioService {

    // que acoes vamos tratar/testar
    // acoes de null e not null, empty ou zero ja tratados em controller

    // testar cadastrar usuario
    // checar se id ja não existe, checar se não tem espaços ou chars especiais no username
    public void registrarUsuario(Usuario usuario);

    // testar remover usuario
    // checar se id corresponde, se corresponder apagar todos os dados relacionados depois o usuario
    // remover todas as transações e categorias primeiro, depois o usuario por último
    public void removerUsuario(int idUsuarioARemover);

    // testar buscar por login
    // checar se username existe e retornar usuario
    public Usuario buscarUsuarioPorLogin(String username);

    // testar buscar por ID
    // checar se id existe e retornar usuario
    public Usuario buscarUsuarioPorID(int userID);

    // testar login/senha
    // checar se usuario existe e comparar senhas
    public boolean validarLogin(String usuario, String senha);

}
