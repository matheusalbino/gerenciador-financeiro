package org.example.controller;

import org.example.model.Usuario;
import org.example.service.UsuarioService;
import org.example.service.UsuarioServiceImpl;
import org.example.util.ValidarEntrada;

import javax.swing.*;

public class UsuarioController {

    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    public void cadastrarUsuario(String username, String senha){
        try {
            ValidarEntrada.validarStringNuloOuVazia(username, "Insira um nome de usuário.");
            ValidarEntrada.validarStringNuloOuVazia(senha, "Insira uma senha");

            int idUsuario = usuarioService.proximoIdUsuario();

            if (usuarioService.buscarUsuarioPorNome(username) != null){
                throw new IllegalArgumentException("Nome de usuario já existe");
            }

            Usuario usuario = new Usuario(idUsuario, username, senha);
            usuarioService.registrarUsuario(usuario);

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removerUsuario(int idUsuario){
        usuarioService.removerUsuario(idUsuario);
    }

    public Usuario buscarUsuarioPorID(int idUsuario){
        ValidarEntrada.validarInt(idUsuario, "Id usuário não permitido");

        return usuarioService.buscarUsuarioPorID(idUsuario);
    }

    public void logoutUsuario(){
        usuarioService.logOutUsuario();
    }

    public Usuario buscarUsuarioLogado(){
        return usuarioService.getUsuarioLogado();
    }

    public Usuario buscarUsuarioPorUsername(String username){
        ValidarEntrada.validarStringNuloOuVazia(username, "Insira um username");
        return usuarioService.buscarUsuarioPorNome(username);
    }

    public Usuario Login (String username, String senha){
        try {
            ValidarEntrada.validarStringNuloOuVazia(username, "Insira um username");
            ValidarEntrada.validarStringNuloOuVazia(senha, "Insira uma senha");

            return usuarioService.validarLogin(username, senha);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

}
