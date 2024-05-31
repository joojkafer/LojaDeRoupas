package view;

import java.util.Scanner;

import controller.EnderecoController;
import controller.ClienteController;
import model.entities.Cliente;
import model.entities.Endereco;

public class EnderecoView {
    
    private Scanner scanner = new Scanner(System.in);
    private EnderecoController enderecoController = new EnderecoController();
    private ClienteController clienteController = new ClienteController();

    public void menu() {
        while (true) {
            System.out.println("________________________");
            System.out.println("     MENU ENDERECO");
            System.out.println("1. Cadastrar Endereco");
            System.out.println("2. Consultar Endereco por ID");
            System.out.println("3. Editar Endereco");
            System.out.println("4. Deletar Endereco");
            System.out.println("5. Sair");
            System.out.print("\nEscolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    createEndereco();
                    break;
                case 2:
                    findEnderecoById();
                    break;
                case 3:
                    updateEndereco();
                    break;
                case 4:
                    deleteEndereco();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opcao inválida!");
            }
        }
    }

    private void createEndereco(){
        System.out.println("\nCadastrar Endereco");
        
        System.out.print("CEP: ");
        String cep = scanner.nextLine();
        
        System.out.print("Numero: ");
        String nmr = scanner.nextLine();
        
        System.out.print("Rua: ");
        String rua = scanner.nextLine();
        
        System.out.print("Bairro: ");
        String bairro= scanner.nextLine();
        
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        
        System.out.print("Pais: ");
        String pais = scanner.nextLine();
        
        System.out.print("Complemento: ");
        String complemento = scanner.nextLine();
        
        System.out.print("Digite o ID do cliente associado: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = clienteController.getClienteById(idCliente);
        if (cliente == null) {
            System.out.println("Cliente ao encontrado!");
            return;
        }

        enderecoController.createEndereco(cep, nmr, rua, bairro, cidade, estado, pais, complemento, idCliente);
        System.out.println("\nEndereco cadastrado com sucesso!");
    }

    private void findEnderecoById() {
        System.out.print("\nDigite o ID do Endereco: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Endereco endereco = enderecoController.getEnderecoById(id);

        if (endereco != null) {
            System.out.println("\nEndereço encontrado!\n");
            System.out.println("ID: " + endereco.getIdEndereco());
            System.out.println("CEP: " + endereco.getCep());
            System.out.println("Numero: " + endereco.getNumero());
            System.out.println("Rua: " + endereco.getRua());
            System.out.println("Bairro: " + endereco.getBairro());
            System.out.println("Cidade: " + endereco.getCidade());
            System.out.println("Estado: " + endereco.getEstado());
            System.out.println("Pais: " + endereco.getPais());
            System.out.println("Complemento: " + endereco.getComplemento());
            System.out.println("Cliente associado: " + endereco.getCliente().getNome());
        } else {
            System.out.println("\nEndereco nao encontrado.");
        }
    }
    
    private void updateEndereco() {
        System.out.print("\nDigite o ID do Endereco que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Endereco endereco = enderecoController.getEnderecoById(id);

        if (endereco != null) {
            System.out.println("Endereço encontrado!\n");

            System.out.print("Novo CEP: ");
            String cep = scanner.nextLine();
            endereco.setCep(cep);

            System.out.print("Novo Numero: ");
            String numero = scanner.nextLine();
            endereco.setNumero(numero);

            System.out.print("Nova Rua: ");
            String rua = scanner.nextLine();
            endereco.setRua(rua);

            System.out.print("Novo Bairro: ");
            String bairro = scanner.nextLine();
            endereco.setBairro(bairro);

            System.out.print("Nova Cidade: ");
            String cidade = scanner.nextLine();
            endereco.setCidade(cidade);

            System.out.print("Novo Estado: ");
            String estado = scanner.nextLine();
            endereco.setEstado(estado);

            System.out.print("Novo Pais: ");
            String pais = scanner.nextLine();
            endereco.setPais(pais);

            System.out.print("Novo Complemento: ");
            String complemento = scanner.nextLine();
            endereco.setComplemento(complemento);

            enderecoController.updateEndereco(endereco);
            System.out.println("\nEndereço atualizado com sucesso!");
        } else {
            System.out.println("\nEndereço nao encontrado.");
        }
    }

    private void deleteEndereco() {
        System.out.print("\nDigite o ID do Endereço que deseja deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Endereco endereco = enderecoController.getEnderecoById(id);

        if (endereco != null) {
            enderecoController.deleteEndereco(id);
            System.out.println("\nEndereco deletado com sucesso!");
        } else {
            System.out.println("\nEndereco nao encontrado.");
        }
    }
}
