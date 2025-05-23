package org.example.dao.JPAImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.example.dao.TransacaoDAO;
import org.example.model.Categoria;
import org.example.model.Transacao;
import org.example.util.JPAUtil;

import java.util.List;

public class TransacaoJPA implements TransacaoDAO {

    @Override
    public void adicionarTransacao(Transacao transacao) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();

            // relacionando a categoria no banco
            transacao.setCategoria(em.find(Categoria.class, transacao.getCategoria().getId()));
            em.persist(transacao);
            tx.commit();

        }catch (Exception e){
            if (tx.isActive()){
                tx.rollback();
            }
            throw e;
        }finally {
            em.close();
        }
    }

    @Override
    public void removerTransacao(Transacao transacao) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            Transacao tr = em.find(Transacao.class, transacao.getId());
            if (tr != null){
                em.remove(tr);
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
    public List<Transacao> buscarTransacoesDeUsuario(int userid) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Transacao> q = em.createQuery("SELECT t FROM Transacao t WHERE t.userid = :userid", Transacao.class);
            q.setParameter("userid", userid);
            return q.getResultList();
        }
        finally {
            em.close();
        }
    }

    public int buscarUltimaTransacao(){
        EntityManager em = JPAUtil.getEntityManager();

        try{
            Query q = em.createQuery("SELECT MAX(id) FROM Transacao ");
            Integer result = (Integer) q.getSingleResult();
            if (result == null){
                return 0;
            }
            return result;
        }finally {
            em.close();
        }
    }

    @Override
    public Transacao buscarTransacaoPorId(int idTransacao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Transacao.class, idTransacao);
        }finally {
            em.close();
        }
    }
}
