package controller;

import model.entities.Vendedor;
import model.repositories.VendedorRepository;

public class VendedorController {
    
    private VendedorRepository vendedorRepository = new VendedorRepository();

    public void createVendedor(String cpf, String nome) {
        Vendedor vendedor = new Vendedor();
        vendedor.setCpf(cpf);
        vendedor.setNome(nome);

        vendedorRepository.create(vendedor);
        System.out.println("Vendedor criado com sucesso!");
    }

    public Vendedor getVendedorById(int id) {
        Vendedor vendedor = vendedorRepository.findById(id);
        if (vendedor == null) {
            System.out.println("Vendedor nao encontrado!");
        }
        return vendedor;
    }
    
    public static Vendedor getVendedorByIdStt(int id) {
        Vendedor vendedor = new Vendedor();
        if (vendedor == null) {
            System.out.println("Vendedor nao encontrado!");
        }
        return vendedor;
    }

    public void updateVendedor(int id, String cpf, String nome) {
        Vendedor vendedor = vendedorRepository.findById(id);
        if (vendedor != null) {
            vendedor.setCpf(cpf);
            vendedor.setNome(nome);
            vendedorRepository.updateById(vendedor);
            System.out.println("Vendedor atualizado com sucesso!");
        } else {
            System.out.println("Vendedor nao encontrado!");
        }
    }

    public void deleteVendedor(int id) {
        Vendedor vendedor = vendedorRepository.findById(id);
        if (vendedor != null) {
            vendedorRepository.delete(id);
            System.out.println("Vendedor deletado com sucesso!");
        } else {
            System.out.println("Vendedor nao encontrado!");
        }
    }
}
