package org.example.factory;

import org.example.dao.CategoriaDAO;
import org.example.dao.JPAImpl.CategoriaJPA;
import org.example.dao.JPAImpl.TransacaoJPA;
import org.example.dao.JPAImpl.UsuarioJPA;
import org.example.dao.TransacaoDAO;
import org.example.dao.UsuarioDAO;


public class DAOFactory {

    public static CategoriaDAO getCategoriaDAO(){
        // return new CategoriaDAOImpl();
        return new CategoriaJPA();
    }

    public static TransacaoDAO getTransacaoDAO(){
        // return new TransacaoDAOImpl();
        return new TransacaoJPA();
    }

    public static UsuarioDAO getUsuarioDAO(){
        // return new UsuarioDAOImpl();
        return new UsuarioJPA();
    }

}
