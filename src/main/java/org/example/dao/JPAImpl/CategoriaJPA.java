package org.example.dao.JPAImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.dao.CategoriaDAO;
import org.example.model.Categoria;
import org.example.util.JPAUtil;

import java.util.List;

public class CategoriaJPA implements CategoriaDAO {

    @Override
    public void adicionarCategoria(Categoria categoria){
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            em.persist(categoria);
            tx.commit();
        }catch (RuntimeException e){
            if (tx.isActive()){
                tx.rollback();
                throw e;
            }
        }finally {
            em.close();
        }

    }

    @Override
    public void removerCategoria(Categoria categoria){

    }

    @Override
    public void editarCategoria(Categoria categoria, String nome, String descricao){

    }

    @Override
    public List<Categoria> listarCategoriasDeUsuario(int idUsuario){
        return List.of();
    }

    @Override
    public Categoria buscarCategoriaPorNome(String nome_categoria, int idUsuario){
        return new Categoria();
    }

}
