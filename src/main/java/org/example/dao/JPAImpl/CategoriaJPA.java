package org.example.dao.JPAImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
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
            }
            throw e;
        }finally {
            em.close();
        }

    }

    @Override
    public void removerCategoria(Categoria categoria){
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{

            tx.begin();
            Categoria cat = em.find(Categoria.class, categoria.getId());
            if (cat != null){
                em.remove(cat);
            }
            tx.commit();
        }catch (RuntimeException e){
            if (tx.isActive()){
                tx.rollback();
            }
            throw e;
        }finally {
            em.close();
        }
    }

    @Override
    public void editarCategoria(Categoria categoria, String nome, String descricao){
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            Categoria cat = em.find(Categoria.class, categoria.getId());
            if (cat != null){
                cat.setNome(nome);
                cat.setDescricao(descricao);
                em.merge(cat);
            }
            tx.commit();
        }catch (RuntimeException e){
            if (tx.isActive()){
                tx.rollback();
            }
            throw e;
        }finally {
            em.close();
        }
    }

    @Override
    public List<Categoria> listarCategoriasDeUsuario(int id){
        EntityManager em = JPAUtil.getEntityManager();

        try{
            TypedQuery<Categoria> q = em.createQuery("SELECT c FROM Categoria c WHERE c.userid = :userid", Categoria.class);
            q.setParameter("userid", id);
            return q.getResultList();
        }finally {
            em.close();
        }


    }

    @Override
    public Categoria buscarCategoriaPorNome(String nomeCategoria, int idUsuario){
        EntityManager em = JPAUtil.getEntityManager();

        try{
            TypedQuery<Categoria> q = em.createQuery("SELECT c FROM Categoria c WHERE c.userid = :userid AND c.nome = :cat_nome", Categoria.class);
            q.setParameter("userid", idUsuario);
            q.setParameter("cat_nome", nomeCategoria);
            return q.getResultList().getFirst();
        }finally {
            em.close();
        }

    }

}
