package model.repositories;

import model.entities.CartaoCredito;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CartaoCreditoRepository implements BasicCrud {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public CartaoCreditoRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("LojaDeRoupas");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public CartaoCredito create(Object object) {
        CartaoCredito cartaoCredito = (CartaoCredito) object;
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cartaoCredito);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar Cartao de Credito");
        }
        return cartaoCredito;
    }

    @Override
    public CartaoCredito findById(Integer id) {
        return entityManager.find(CartaoCredito.class, id);
    }
    
    @Override
	public Object updateById(Object object) {
		return null;
	}

    @Override
    public void delete(Integer id) {
        try {
            entityManager.getTransaction().begin();
            CartaoCredito cartaoCredito = entityManager.find(CartaoCredito.class, id);
            if (cartaoCredito != null) {
                entityManager.remove(cartaoCredito);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar Cartao de Credito");
        }
    }
}
