package model.repositories;

import model.entities.NotaFiscal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class NotaFiscalRepository implements BasicCrud {

    private EntityManagerFactory emf;
    private EntityManager em;

    public NotaFiscalRepository() {
        emf = Persistence.createEntityManagerFactory("LojaDeRoupas");
        em = emf.createEntityManager();
    }

    @Override
    public NotaFiscal create(Object object) {
        NotaFiscal notaFiscal = (NotaFiscal) object;
        try {
            em.getTransaction().begin();
            em.persist(notaFiscal);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar Nota Fiscal");
        }
        return notaFiscal;
    }

    @Override
    public NotaFiscal findById(Integer id) {
        return em.find(NotaFiscal.class, id);
    }

    public List<NotaFiscal> findAll() {
        return em.createQuery("FROM NotaFiscal", NotaFiscal.class).getResultList();
    }

    @Override
    public NotaFiscal updateById(Object object) {
        NotaFiscal notaFiscal = (NotaFiscal) object;
        try {
            em.getTransaction().begin();
            notaFiscal = em.merge(notaFiscal);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar Nota Fiscal");
        }
        return notaFiscal;
    }

    @Override
    public void delete(Integer id) {
        try {
            em.getTransaction().begin();
            NotaFiscal notaFiscal = em.find(NotaFiscal.class, id);
            if (notaFiscal != null) {
                em.remove(notaFiscal);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar Nota Fiscal");
        }
    }
}
