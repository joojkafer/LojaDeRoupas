package controller;

import model.entities.Cliente;
import model.repositories.ClienteRepository;

import java.util.Date;

public class ClienteController {

    private ClienteRepository clienteRepository = new ClienteRepository();
    
    public Cliente createCliente(String cpf, String nome) {
        Date dataCadastro = new Date();
        Cliente cliente = new Cliente(null, cpf, nome, dataCadastro, null, null, null);
        return clienteRepository.create(cliente);
    }

    public Cliente getClienteById(int id) {
        return clienteRepository.findById(id);
    }

    public void updateCliente(int id, String cpf, String nome) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente != null) {
            cliente.setCpf(cpf);
            cliente.setNome(nome);
            clienteRepository.updateById(cliente);
        } else {
            System.out.println("Cliente nao encontrado.");
        }
    }

    public void deleteCliente(int id) {
        try {
            clienteRepository.delete(id);
            System.out.println("Cliente deletado com sucesso!");
        } catch (RuntimeException e) {
            System.out.println("Erro ao deletar Cliente: " + e.getMessage());
        }
    }
}
