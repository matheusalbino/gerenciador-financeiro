package org.example.dao.JPAImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.example.dao.UsuarioDAO;
import org.example.model.SessaoUsuario;
import org.example.model.Usuario;
import org.example.util.JPAUtil;

import java.util.List;

public class UsuarioJPA implements UsuarioDAO {

    @Override
    public void cadastrarUsuario(Usuario usuario) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(usuario);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()){
                tx.rollback();
            }
            throw e;
        }finally {
            em.close();
        }
    }

    @Override
    public void removerUsuario(Usuario usuario) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            Usuario usuario_db = em.find(Usuario.class, usuario.getId());

            if (usuario_db != null) {
                em.remove(usuario_db);
            }
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()){
                tx.rollback();
            }
            throw e;
        }finally {
            em.close();
        }
    }

    @Override
    public Usuario buscarPorNome(String username) {
        EntityManager em = JPAUtil.getEntityManager();

        TypedQuery<Usuario> q = em.createQuery("SELECT u FROM Usuario u WHERE u.login = :nome", Usuario.class);
        q.setParameter("nome", username);

        List<Usuario> busca = q.getResultList();
        if (!busca.isEmpty()){
            return q.getResultList().getFirst();
        }

        return null;
    }

    @Override
    public Usuario buscarUsuarioPorId(int idUsuario) {
        EntityManager em = JPAUtil.getEntityManager();

        return em.find(Usuario.class, idUsuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        EntityManager em = JPAUtil.getEntityManager();

        TypedQuery<Usuario> q = em.createQuery("SELECT u FROM Usuario u", Usuario.class);

        return q.getResultList();
    }

    @Override
    public void logarUsuario(Usuario usuario) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        SessaoUsuario sessao = new SessaoUsuario(0, usuario.getId());
        try {
            tx.begin();
            if (em.find(SessaoUsuario.class, 0) == null) {
                em.persist(sessao);
            }else {
                em.merge(sessao);
            }
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()){
                tx.rollback();
            }
            throw e;
        }finally {
            em.close();
        }
    }

    @Override
    public Usuario getUsuarioLogado() {
        EntityManager em = JPAUtil.getEntityManager();

        TypedQuery<SessaoUsuario> q = em.createQuery("SELECT s.idUsuario FROM SessaoUsuario s WHERE s.id = 0", SessaoUsuario.class);

        return em.find(Usuario.class, q.getResultList().getFirst());
    }

    @Override
    public void logoutUsuario() {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Query q = em.createQuery("UPDATE SessaoUsuario SET idUsuario = null WHERE id = 0");
            q.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()){
                tx.rollback();
            }
            throw e;
        }
        finally {
            em.close();
        }
    }
}
