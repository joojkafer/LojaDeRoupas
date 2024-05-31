package model.repositories;

import model.entities.Endereco;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EnderecoRepository implements BasicCrud {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public EnderecoRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("LojaDeRoupas");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Endereco create(Object object) {
        Endereco endereco = (Endereco) object;
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(endereco);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar endereço.");
        }
        return endereco;
    }

    @Override
    public Endereco findById(Integer id) {
        return entityManager.find(Endereco.class, id);
    }

    @Override
    public Endereco updateById(Object object) {
        Endereco endereco = (Endereco) object;
        try {
            entityManager.getTransaction().begin();
            endereco = entityManager.merge(endereco);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar Endereço");
        }
        return endereco;
    }

    @Override
    public void delete(Integer id) {
        try {
            entityManager.getTransaction().begin();
            Endereco endereco = entityManager.find(Endereco.class, id);
            if (endereco != null) {
                entityManager.remove(endereco);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar Endereço");
        }
    }
}
