package controller;

import model.entities.Cliente;
import model.entities.Endereco;
import model.repositories.ClienteRepository;
import model.repositories.EnderecoRepository;

public class EnderecoController {

    private EnderecoRepository enderecoRepository = new EnderecoRepository();
    private ClienteRepository clienteRepository = new ClienteRepository();

    public void createEndereco(String cep, String numero, String rua, String bairro, String cidade, String estado, String pais, String complemento, int idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }

        Endereco endereco = new Endereco(null, cep, numero, rua, bairro, cidade, estado, pais, complemento, cliente);

        enderecoRepository.create(endereco);
    }
    
    public void updateEndereco(Endereco endereco) {
        enderecoRepository.updateById(endereco);
    }

    public Endereco getEnderecoById(Integer id) {
        return enderecoRepository.findById(id);
    }

    public void deleteEndereco(Integer id) {
        enderecoRepository.delete(id);
    }
}