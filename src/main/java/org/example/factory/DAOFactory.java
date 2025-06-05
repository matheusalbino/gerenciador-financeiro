package org.example.factory;

import org.example.dao.*;
import org.example.dao.singletonImpl.CategoriaDAOImpl;
import org.example.dao.singletonImpl.TransacaoDAOImpl;
import org.example.dao.singletonImpl.UsuarioDAOImpl;
import org.example.dao.JPAImpl.CategoriaJPA;
import org.example.dao.JPAImpl.TransacaoJPA;
import org.example.dao.JPAImpl.UsuarioJPA;
import org.example.model.enums.TipoPersistencia;

public class DAOFactory {

    private static TipoPersistencia tipo = TipoPersistencia.BANCO; // ou MEMORIA

    public static void configurar(TipoPersistencia novoTipo) {
        tipo = novoTipo;
    }

    public static CategoriaDAO getCategoriaDAO() {
        if (tipo == TipoPersistencia.BANCO) {
            return new CategoriaJPA();
        }
        return new CategoriaDAOImpl();
    }

    public static UsuarioDAO getUsuarioDAO() {
        if (tipo == TipoPersistencia.BANCO) {
            return new UsuarioJPA();
        }
        return new UsuarioDAOImpl();
    }

    public static TransacaoDAO getTransacaoDAO() {
        if (tipo == TipoPersistencia.BANCO) {
            return new TransacaoJPA();
        }
        return new TransacaoDAOImpl();
    }
}

