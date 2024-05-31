package model.repositories;

import model.entities.Produto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProdutoRepository implements BasicCrud {

    private EntityManagerFactory emf;
    private EntityManager em;

    public ProdutoRepository() {
        this.emf = Persistence.createEntityManagerFactory("LojaDeRoupas");
        this.em = emf.createEntityManager();
    }

    @Override
    public Produto create(Object object) {
        Produto produto = (Produto) object;
        try {
        	em.getTransaction().begin();
        	em.persist(produto);
        	em.getTransaction().commit();
        } catch (Exception e) {
        	em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar Produto");
        }
        return produto;
    }

    @Override
    public Produto findById(Integer id) {
        return em.find(Produto.class, id);
    }
    
    public List<Produto> findAll() {
        return em.createQuery("FROM Produto", Produto.class).getResultList();

    }

    @Override
    public Produto updateById(Object object) {
        Produto produto = (Produto) object;
        try {
        	em.getTransaction().begin();
            produto = em.merge(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
        	em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar Produto");
        }
        return produto;
    }

    @Override
    public void delete(Integer id) {
        try {
        	em.getTransaction().begin();
            Produto produto = em.find(Produto.class, id);
            if (produto != null) {
            	em.remove(produto);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
        	em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar Produto");
        }
    }
}
