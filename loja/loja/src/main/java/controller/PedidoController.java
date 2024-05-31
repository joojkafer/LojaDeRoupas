package controller;

import model.entities.Cliente;
import model.entities.Pedido;
import model.entities.Vendedor;
import model.repositories.ClienteRepository;
import model.repositories.PedidoRepository;
import model.repositories.VendedorRepository;

import java.util.Date;
import java.util.List;

public class PedidoController {

    private PedidoRepository pedidoRepository = new PedidoRepository();
    private ClienteRepository clienteRepository = new ClienteRepository();
    private VendedorRepository vendedorRepository = new VendedorRepository();

    public int createPedido(Double valorTotal, Double valorDesconto, Date dataPedido, int idVendedor, int idCliente) {
        Vendedor vendedor = vendedorRepository.findById(idVendedor);
        Cliente cliente = clienteRepository.findById(idCliente);

        if (vendedor == null || cliente == null) {
            throw new RuntimeException("Vendedor ou Cliente nao encontrado");
        }
        
        Pedido pedido = new Pedido();
        pedido.setValorTotal(valorTotal);
        pedido.setValorDesconto(valorDesconto);
        pedido.setDataPedido(dataPedido);
        pedido.setVendedor(vendedor);
        pedido.setCliente(cliente);
        
        Pedido savedPedido = pedidoRepository.create(pedido);
        return savedPedido.getIdPedido();
    }

    public Pedido getPedidoById(Integer id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> getAllPedido() {
        return pedidoRepository.findAll();
    }

    public void updatePedido(int idPedido, Double valorTotal, Double valorDesconto, Date dataPedido, int idVendedor, int idCliente) {
        Pedido pedido = pedidoRepository.findById(idPedido);
        if (pedido != null) {
            pedido.setValorTotal(valorTotal);
            pedido.setValorDesconto(valorDesconto);
            pedido.setDataPedido(dataPedido);

            Vendedor vendedor = vendedorRepository.findById(idVendedor);
            Cliente cliente = clienteRepository.findById(idCliente);

            if (vendedor == null || cliente == null) {
                throw new RuntimeException("Vendedor ou Cliente nao encontrado");
            }
            
            pedido.setVendedor(vendedor);
            pedido.setCliente(cliente);
            
            pedidoRepository.updateById(pedido);
        } else {
            throw new RuntimeException("Pedido nao encontrado");
        }
    }

    public void deletePedido(Integer id) {
        Pedido pedido = pedidoRepository.findById(id);
        if (pedido != null) {
            pedidoRepository.delete(id);
        } else {
            throw new RuntimeException("Pedido nao encontrado");
        }
    }
}
