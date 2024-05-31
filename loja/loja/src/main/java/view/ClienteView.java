// ClienteView.java
package view;

import controller.ClienteController;
import model.entities.Cliente;

import java.util.Scanner;

public class ClienteView {

    private ClienteController clienteController = new ClienteController();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("________________________");
            System.out.println("     MENU CLIENTES");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Gerenciar Enderecos");
            System.out.println("3. Gerenciar Cartoes");
            System.out.println("4. Consultar Cliente por ID");
            System.out.println("5. Atualizar Cliente");
            System.out.println("6. Deletar Cliente");
            System.out.println("7. Sair");
            System.out.print("\nEscolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    createCliente();
                    break;
                case 2:
                	EnderecoView enderecoView = new EnderecoView();
                	enderecoView.menu();
                    break;
                case 3:
                	CartaoCreditoView cartaoCreditoView = new CartaoCreditoView();
                	cartaoCreditoView.menu();
                    break;
                case 4:
                    findClienteById();
                    break;
                case 5:
                    updateCliente();
                    break;
                case 6:
                    deleteCliente();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void createCliente() {
        System.out.println("\nCadastrar Cliente");
        
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        
        System.out.print("Digite o Nome: ");
        String nome = scanner.nextLine();
        
        clienteController.createCliente(cpf, nome);
        System.out.println("\nCliente cadastrado com sucesso!");
    }

    private void findClienteById() {
    	int idCliente;
    	
        System.out.print("\nDigite o ID do Cliente: ");
        idCliente = scanner.nextInt();
        scanner.nextLine();
        
        Cliente cliente = clienteController.getClienteById(idCliente);
        
        if (cliente != null) {
            System.out.println("Cliente encontrado!\n");
            System.out.println("ID: " + cliente.getIdCliente());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Data de Cadastro: " + cliente.getDataCadastro());
        } else {
            System.out.println("\nCliente não encontrado.");
        }
    }

    private void updateCliente() {
    	int idCliente;
    	
        System.out.print("\nDigite o ID do Cliente que deseja atualizar: ");
        idCliente = scanner.nextInt();
        scanner.nextLine();
        
        Cliente cliente = clienteController.getClienteById(idCliente);
        
        if (cliente != null) {
        	System.out.print("Digite o novo CPF: ");
            String novoCpf = scanner.nextLine();
            
            System.out.print("Digite o novo Nome: ");
            String novoNome = scanner.nextLine();
            
            clienteController.updateCliente(idCliente, novoCpf, novoNome);
        } else {
            System.out.println("\nCliente nao encontrado.");
        }
    }

    private void deleteCliente() {
    	int idCliente;
    	
        System.out.print("\nDigite o ID do Cliente que deseja deletar: ");
        idCliente = scanner.nextInt();
        scanner.nextLine();
        
        Cliente cliente = clienteController.getClienteById(idCliente);
        
        if (cliente != null) {
        	clienteController.deleteCliente(idCliente);
        } else {
            System.out.println("\nCliente nao encontrado.");
        }
    }
}
