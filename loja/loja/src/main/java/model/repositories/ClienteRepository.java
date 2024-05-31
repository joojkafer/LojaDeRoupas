package model.repositories;

import model.entities.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClienteRepository implements BasicCrud{
	
	private EntityManagerFactory emf;
    private EntityManager em;

    public ClienteRepository() {
        this.emf = Persistence.createEntityManagerFactory("LojaDeRoupas");
        this.em = emf.createEntityManager();
    }

    @Override
    public Cliente create(Object object) {
        Cliente cliente = (Cliente) object;
        try {
        	em.getTransaction().begin();
        	em.persist(cliente);
        	em.getTransaction().commit();
        } catch (Exception e) {
        	em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar Cliente");
        }
        return cliente;
    }

    @Override
    public Cliente findById(Integer id) {
        return em.find(Cliente.class, id);
    }

    @Override
    public Cliente updateById(Object object) {
        Cliente cliente = (Cliente) object;
        try {
        	em.getTransaction().begin();
            cliente = em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
        	em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar Cliente");
        }
        return cliente;
    }

    @Override
    public void delete(Integer id) {
        try {
        	em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, id);
            if (cliente != null) {
            	em.remove(cliente);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
        	em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar Cliente");
        }
    }
}
