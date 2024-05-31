package controller;

import model.entities.CartaoCredito;
import model.entities.Cliente;
import model.repositories.CartaoCreditoRepository;
import model.repositories.ClienteRepository;

import java.util.Date;

public class CartaoCreditoController {

    private CartaoCreditoRepository cartaoCreditoRepository = new CartaoCreditoRepository();
    private ClienteRepository clienteRepository = new ClienteRepository();

    public void createCartaoCredito(String nomeCartao, String nmrCartao, String cvv, Date dataValidade, int idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        CartaoCredito cartaoCredito = new CartaoCredito(null, nomeCartao, nmrCartao, cvv, dataValidade, cliente);

        cartaoCreditoRepository.create(cartaoCredito);
    }
    
    public CartaoCredito getCartaoCreditoById(Integer id) {
        return cartaoCreditoRepository.findById(id);
    }

    public void deleteCartaoCredito(Integer id) {
        cartaoCreditoRepository.delete(id);
    }
}
