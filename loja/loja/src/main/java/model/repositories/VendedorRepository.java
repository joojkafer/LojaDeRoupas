package model.repositories;

import model.entities.Vendedor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class VendedorRepository implements BasicCrud {
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public VendedorRepository() {
        this.emf = Persistence.createEntityManagerFactory("LojaDeRoupas");
        this.em = emf.createEntityManager();
    }

    @Override
    public Vendedor create(Object object) {
        Vendedor vendedor = (Vendedor) object;
        try {
        	em.getTransaction().begin();
        	em.persist(vendedor);
        	em.getTransaction().commit();
        } catch (Exception e) {
        	em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar Vendedor.");
        }
        return vendedor;
    }

    @Override
    public Vendedor findById(Integer id) {
        return em.find(Vendedor.class, id);
    }

    @Override
    public Vendedor updateById(Object object) {
        Vendedor vendedor = (Vendedor) object;
        try {
        	em.getTransaction().begin();
            vendedor = em.merge(vendedor);
            em.getTransaction().commit();
        } catch (Exception e) {
        	em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar Vendedor.");
        }
        return vendedor;
    }

    @Override
    public void delete(Integer id) {
        try {
        	em.getTransaction().begin();
            Vendedor vendedor = em.find(Vendedor.class, id);
            if (vendedor != null) {
            	em.remove(vendedor);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
        	em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar Vendedor.");
        }
    }
}
