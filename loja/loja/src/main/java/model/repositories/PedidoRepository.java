package model.repositories;

import model.entities.Pedido;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PedidoRepository implements BasicCrud {

    private EntityManagerFactory emf;
    private EntityManager em;

    public PedidoRepository() {
        emf = Persistence.createEntityManagerFactory("LojaDeRoupas");
        em = emf.createEntityManager();
    }

    @Override
    public Pedido create(Object object) {
        Pedido pedido = (Pedido) object;
        try {
            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar Pedido");
        }
        return pedido;
    }

    @Override
    public Pedido findById(Integer id) {
        return em.find(Pedido.class, id);
    }

    public List<Pedido> findAll() {
        return em.createQuery("FROM Pedido", Pedido.class).getResultList();
    }

    @Override
    public Pedido updateById(Object object) {
        Pedido pedido = (Pedido) object;
        try {
            em.getTransaction().begin();
            pedido = em.merge(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar Pedido");
        }
        return pedido;
    }
    
    @Override
    public void delete(Integer id) {
        try {
            em.getTransaction().begin();
            Pedido pedido = em.find(Pedido.class, id);
            if (pedido != null) {
                em.remove(pedido);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar Pedido");
        }
    }
}
