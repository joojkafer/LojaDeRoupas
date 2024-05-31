package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.entities.ProdutoPedido;

public class ProdutoPedidoRepository implements BasicCrud {

    private EntityManagerFactory emf;
    private EntityManager em;

    public ProdutoPedidoRepository() {
        this.emf = Persistence.createEntityManagerFactory("LojaDeRoupas");
        this.em = emf.createEntityManager();
    }

    @Override
    public Object create(Object object) {
        ProdutoPedido produtoPedido = (ProdutoPedido) object;
        try {
            em.getTransaction().begin();
            em.persist(produtoPedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar.");
        }
        return produtoPedido;
    }

    @Override
    public Object findById(Integer id) {
        return em.find(ProdutoPedido.class, id);
    }
    
    public List<ProdutoPedido> findAll() {
        TypedQuery<ProdutoPedido> query = em.createQuery("SELECT pp FROM ProdutoPedido pp", ProdutoPedido.class);
        return query.getResultList();
    }

    public List<ProdutoPedido> findByPedidoId(int pedidoId) {
        TypedQuery<ProdutoPedido> query = em.createQuery(
                "SELECT pp FROM ProdutoPedido pp WHERE pp.pedido.idPedido = :pedidoId", ProdutoPedido.class)
                .setParameter("pedidoId", pedidoId);
        return query.getResultList();
    }
    
    @Override
    public Object updateById(Object object) {
        ProdutoPedido produtoPedido = (ProdutoPedido) object;
        try {
            em.getTransaction().begin();
            produtoPedido = em.merge(produtoPedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar.");
        }
        return produtoPedido;
    }

    @Override
    public void delete(Integer id) {
        try {
            em.getTransaction().begin();
            ProdutoPedido produtoPedido = em.find(ProdutoPedido.class, id);
            if (produtoPedido != null) {
                em.remove(produtoPedido);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar.");
        }
    }
}
