package controller;

import model.entities.ProdutoPedido;
import model.repositories.ProdutoPedidoRepository;

import java.util.List;

import model.entities.Pedido;
import model.entities.Produto;

public class ProdutoPedidoController {

    private ProdutoPedidoRepository produtoPedidoRepository = new ProdutoPedidoRepository();

    public void createProdutoPedido(int idPedido, int idProduto, int quantidade, double valorUnitario) {
        ProdutoPedido produtoPedido = new ProdutoPedido();
        produtoPedido.setPedido(new Pedido(idPedido));
        produtoPedido.setProduto(new Produto(idProduto));
        produtoPedido.setQuantidade(quantidade);
        produtoPedido.setValorUnitario(valorUnitario);
        
        produtoPedidoRepository.create(produtoPedido);
    }

    /*public List<ProdutoPedido> getProdutoPedidoById(int idPedido) {
        ProdutoPedido produtoPedido = (ProdutoPedido) produtoPedidoRepository.findById(idPedido);
        if (produtoPedido == null) {
            System.out.println("ProdutoPedido nao encontrado!");
        }
        return produtoPedidoRepository.findByPedidoId(idPedido);
    }*/
    
    public List<ProdutoPedido> getProdutoPedidoById(int idPedido) {
        return produtoPedidoRepository.findByPedidoId(idPedido);
    }
    
    public List<ProdutoPedido> getAllProdutoPedidos() {
        return produtoPedidoRepository.findAll();
    }

    public void updateProdutoPedido(int idProdutoPedido, int idPedido, int idProduto, int quantidade, double valorUnitario) {
        ProdutoPedido produtoPedido = (ProdutoPedido) produtoPedidoRepository.findById(idProdutoPedido);
        if (produtoPedido != null) {
            produtoPedido.setPedido(new Pedido(idPedido));
            produtoPedido.setProduto(new Produto(idProduto));
            produtoPedido.setQuantidade(quantidade);
            produtoPedido.setValorUnitario(valorUnitario);
            produtoPedidoRepository.updateById(produtoPedido);
            System.out.println("Atualizado com sucesso!");
        } else {
            System.out.println("Bao encontrado!");
        }
    }

    public void deleteProdutoPedido(int id) {
        ProdutoPedido produtoPedido = (ProdutoPedido) produtoPedidoRepository.findById(id);
        if (produtoPedido != null) {
            produtoPedidoRepository.delete(id);
            System.out.println("Deletado com sucesso!");
        } else {
            System.out.println("Nao encontrado!");
        }
    }
}
