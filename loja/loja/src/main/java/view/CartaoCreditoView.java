package view;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Scanner;

import controller.CartaoCreditoController;
import controller.ClienteController;
import model.entities.CartaoCredito;
import model.entities.Cliente;

public class CartaoCreditoView {
    
    private Scanner scanner = new Scanner(System.in);
    private CartaoCreditoController cartaoCreditoController = new CartaoCreditoController();
    private ClienteController clienteController = new ClienteController();

    public void menu() {
        while (true) {
            System.out.println("________________________");
            System.out.println("     MENU CARTAO");
            System.out.println("1. Cadastrar Cartao de Credito");
            System.out.println("2. Consultar Cartao de Credito por ID");
            System.out.println("3. Deletar Cartao de Credito");
            System.out.println("4. Sair");
            System.out.print("\nEscolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    createCartaoCredito();
                    break;
                case 2:
                    findCartaoCreditoById();
                    break;
                case 3:
                    deleteCartaoCredito();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void createCartaoCredito(){
        System.out.println("\nCadastrar Cartao de Credito");

        System.out.print("Digite o nome do cartao: ");
        String nomeCartao = scanner.nextLine();

        System.out.print("Digite o numero do cartao: ");
        String nmrCartao = scanner.nextLine();

        System.out.print("Digite o CVV: ");
        String cvv = scanner.nextLine();

        System.out.print("Digite a data de validade (DD/MM/AAAA): ");
        String dataValidade = scanner.nextLine();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataValidade1 = null;
        try {
            dataValidade1 = dateFormat.parse(dataValidade);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido.");
            return;
        }

        System.out.print("Digite o ID do cliente associado: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = clienteController.getClienteById(idCliente);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado!");
            return;
        }

        cartaoCreditoController.createCartaoCredito(nomeCartao, nmrCartao, cvv, dataValidade1, idCliente);
        System.out.println("\nCartao de Credito cadastrado com sucesso!");
    }

    private void findCartaoCreditoById() {
        System.out.print("\nDigite o ID do Cartao de Credito: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        CartaoCredito cartaoCredito = cartaoCreditoController.getCartaoCreditoById(id);

        if (cartaoCredito != null) {
            System.out.println("Cartao de Credito encontrado!\n");
            System.out.println("ID: " + cartaoCredito.getIdCartaoCredito());
            System.out.println("Nome: " + cartaoCredito.getNomeCartao());
            System.out.println("Numero: " + cartaoCredito.getNmrCartao());
            System.out.println("CVV: " + cartaoCredito.getCvv());
            System.out.println("Data de Validade: " + cartaoCredito.getDataValidade());
            System.out.println("Cliente associado: " + cartaoCredito.getCliente().getNome());
        } else {
            System.out.println("\nCartao de Credito não encontrado.");
        }
    }

    private void deleteCartaoCredito() {
        System.out.print("\nDigite o ID do Cartao de Credito que deseja deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        CartaoCredito cartaoCredito = cartaoCreditoController.getCartaoCreditoById(id);

        if (cartaoCredito != null) {
            cartaoCreditoController.deleteCartaoCredito(id);
            System.out.println("\nCartao de Credito deletado com sucesso!");
        } else {
            System.out.println("\nCartao de Credito não encontrado.");
        }
    }
}
