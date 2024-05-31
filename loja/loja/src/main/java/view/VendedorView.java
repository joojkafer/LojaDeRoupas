package view;

import controller.VendedorController;
import model.entities.Vendedor;

import java.util.Scanner;

public class VendedorView {

    private VendedorController vendedorController = new VendedorController();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("________________________");
            System.out.println("     MENU VENDEDORES");
            System.out.println("1. Cadastrar Vendedor");
            System.out.println("2. Consultar Vendedor por ID");
            System.out.println("3. Atualizar Vendedor");
            System.out.println("4. Deletar Vendedor");
            System.out.println("5. Sair");
            System.out.print("\nEscolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    createVendedor();
                    break;
                case 2:
                    findVendedorById();
                    break;
                case 3:
                    updateVendedor();
                    break;
                case 4:
                    deleteVendedor();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção invalida!");
            }
        }
    }

    private void createVendedor() {
        System.out.println("\nCadastrar Vendedor");
        
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        
        System.out.print("Digite o Nome: ");
        String nome = scanner.nextLine();
        
        vendedorController.createVendedor(cpf, nome);
        System.out.println("\nVendedor cadastrado com sucesso!");
    }

    private void findVendedorById() {
    	int id;
    	
        System.out.print("\nDigite o ID do Vendedor: ");
        id = scanner.nextInt();
        scanner.nextLine();
        
        Vendedor vendedor = vendedorController.getVendedorById(id);
        
        if (vendedor != null) {
            System.out.println("Cliente encontrado!\n");
            System.out.println("ID: " + vendedor.getIdVendedor());
            System.out.println("Nome: " + vendedor.getNome());
            System.out.println("CPF: " + vendedor.getCpf());
        } else {
            System.out.println("\nVendedor nao encontrado.");
        }
    }

    private void updateVendedor() {
    	int id;
    	
        System.out.print("\nDigite o ID do Vendedor que deseja atualizar: ");
        id = scanner.nextInt();
        scanner.nextLine();
        
        Vendedor vendedor = vendedorController.getVendedorById(id);
        
        if (vendedor != null) {
        	System.out.print("Digite o novo CPF: ");
            String novoCpf = scanner.nextLine();
            
            System.out.print("Digite o novo Nome: ");
            String novoNome = scanner.nextLine();
            
            vendedorController.updateVendedor(id, novoCpf, novoNome);
        } else {
            System.out.println("\nVendedor nao encontrado.");
        }
    }

    private void deleteVendedor() {
    	int id;
    	
        System.out.print("\nDigite o ID do Vendedor que deseja deletar: ");
        id = scanner.nextInt();
        scanner.nextLine();
        
        Vendedor vendedor = vendedorController.getVendedorById(id);
        
        if (vendedor != null) {
        	vendedorController.deleteVendedor(id);
        } else {
            System.out.println("\nVendedor não encontrado.");
        }
    }
}
