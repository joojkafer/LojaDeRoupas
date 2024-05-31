package model.repositories;

import model.entities.Pagamento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PagamentoRepository implements BasicCrud {
	
	private EntityManagerFactory emf;
    private EntityManager em;

    public PagamentoRepository() {
        this.emf = Persistence.createEntityManagerFactory("LojaDeRoupas");
        this.em = emf.createEntityManager();
    }

    @Override
    public Pagamento create(Object object) {
        Pagamento pagamento = (Pagamento) object;
        try {
        	em.getTransaction().begin();
        	em.persist(pagamento);
        	em.getTransaction().commit();
        } catch (Exception e) {
        	em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar Pagamento");
        }
        return pagamento;
    }

    @Override
    public Pagamento findById(Integer id) {
        return em.find(Pagamento.class, id);
    }

    @Override
    public Pagamento updateById(Object object) {
        Pagamento pagamento = (Pagamento) object;
        try {
        	em.getTransaction().begin();
            pagamento = em.merge(pagamento);
            em.getTransaction().commit();
        } catch (Exception e) {
        	em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar Pagamento");
        }
        return pagamento;
    }

    @Override
    public void delete(Integer id) {
        try {
        	em.getTransaction().begin();
            Pagamento pagamento = em.find(Pagamento.class, id);
            if (pagamento != null) {
            	em.remove(pagamento);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
        	em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar Pagamento");
        }
    }
}
