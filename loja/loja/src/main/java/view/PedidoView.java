package view;

import controller.PedidoController;
import controller.ProdutoController;
import controller.ProdutoPedidoController;
import model.entities.Cliente;
import model.entities.Pedido;
import model.entities.Produto;
import model.entities.ProdutoPedido;
import model.entities.Vendedor;
import model.repositories.ClienteRepository;
import model.repositories.VendedorRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PedidoView {

    private PedidoController pedidoController = new PedidoController();
    private ProdutoController produtoController = new ProdutoController();
    private ProdutoPedidoController produtoPedidoController = new ProdutoPedidoController();

    private VendedorRepository vendedorRepository = new VendedorRepository();
    private ClienteRepository clienteRepository = new ClienteRepository();
    
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("________________________");
            System.out.println("     MENU PEDIDOS\n");
            System.out.println("1. Cadastrar Pedido");
            System.out.println("2. Consultar Pedido por ID");
            System.out.println("3. Consultar todos ");
            System.out.println("4. Deletar Pedido");
            System.out.println("5. Sair");
            System.out.print("\nEscolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    createPedido();
                    break;
                case 2:
                    findPedidoById();
                    break;
                case 3:
                    findAllPedido();
                    break;
                case 4:
                    deletePedido();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void createPedido() {
        // Reiniciaa o valorTotal para cada novo pedido
        Double valorTotal = 0.0;

        Vendedor vendedor;
        do {
            System.out.print("\nDigite o ID do Vendedor: ");
            int idVendedor = scanner.nextInt();
            scanner.nextLine();

            vendedor = vendedorRepository.findById(idVendedor);
            if (vendedor == null) {
                System.out.println("Vendedor nao encontrado. Tente novamente.");
            }
        } while (vendedor == null);
        
        Cliente cliente;
        do {
            System.out.print("Digite o ID do Cliente: ");
            int idCliente = scanner.nextInt();
            scanner.nextLine();

            cliente = clienteRepository.findById(idCliente);
            if (cliente == null) {
                System.out.println("Cliente nao encontrado. Tente novamente.");
            }
        } while (cliente == null);
        
        int idPedido = pedidoController.createPedido(0.0, 0.0, new Date(), vendedor.getIdVendedor(), cliente.getIdCliente()); // Cria o pedido inicialmente com valor total e desconto zero

        do {
            System.out.print("\nDigite o ID do Produto: ");
            int idProduto = scanner.nextInt();
            scanner.nextLine();

            Produto produto = produtoController.getProdutoById(idProduto);
            if (produto == null) {
                System.out.println("Produto nao encontrado. Tente novamente.");
                continue;
            }

            System.out.print("Digite a quantidade desejada: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();

            try {
                produtoController.removeStorage(idProduto, quantidade);
                valorTotal += produto.getValorProd() * quantidade;
                produtoPedidoController.createProdutoPedido(idPedido, idProduto, quantidade, produto.getValorProd());
            } catch (RuntimeException e) {
                System.out.println("Erro ao adicionar produto ao pedido: " + e.getMessage());
                continue;
            }
            
            System.out.print("Deseja adicionar mais um produto? (S/N): ");
            String continuar = scanner.nextLine();
            
            if (!continuar.equalsIgnoreCase("S")) {
                break;
            }
        } while (true);
        
        System.out.print("Digite o valor do desconto: ");
        Double valorDesconto = scanner.nextDouble();
        scanner.nextLine();

        // Calcula o valor total com o desconto aplicado
        Double valorTotalComDesconto = valorTotal - valorDesconto;

        // Atualiza o pedido com o valor total e o valor de desconto
        pedidoController.updatePedido(idPedido, valorTotalComDesconto, valorDesconto, new Date(), vendedor.getIdVendedor(), cliente.getIdCliente());
        
        System.out.println("\nPedido cadastrado com sucesso!");
    }
    
    private void findPedidoById() {
        System.out.print("\nDigite o ID do Pedido: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Pedido pedido = pedidoController.getPedidoById(id);

        if (pedido != null) {
            System.out.println("Pedido encontrado!\n");
            System.out.println("ID: " + pedido.getIdPedido());
            System.out.println("Valor Total: " + pedido.getValorTotal());
            System.out.println("Valor Desconto: " + pedido.getValorDesconto());
            System.out.println("Data Pedido: " + pedido.getDataPedido());
            System.out.println("ID Vendedor: " + pedido.getVendedor().getIdVendedor());
            System.out.println("ID Cliente: " + pedido.getCliente().getIdCliente());

            List<ProdutoPedido> produtoPedidos = (List<ProdutoPedido>) produtoPedidoController.getProdutoPedidoById(pedido.getIdPedido());
            System.out.println("Produtos do Pedido:");
            for (ProdutoPedido pp : produtoPedidos) {
                System.out.println("ID Produto: " + pp.getProduto().getIdProduto());
                System.out.println("Quantidade: " + pp.getQuantidade());
                System.out.println("Valor Unitário: " + pp.getValorUnitario());
            }
        } else {
            System.out.println("\nPedido nao encontrado.");
        }
    }
    
    private void findAllPedido() {
        List<Pedido> pedidos = pedidoController.getAllPedido();
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
        } else {
            System.out.println("Lista de Pedidos:");
            for (Pedido pedido : pedidos) {
                System.out.println("________________________");
                System.out.println("ID: " + pedido.getIdPedido());
                System.out.println("Valor Total: " + pedido.getValorTotal());
                System.out.println("Valor Desconto: " + pedido.getValorDesconto());
                System.out.println("Data Pedido: " + pedido.getDataPedido());
                System.out.println("ID Vendedor: " + pedido.getVendedor().getIdVendedor());
                System.out.println("ID Cliente: " + pedido.getCliente().getIdCliente());

                List<ProdutoPedido> produtoPedidos = produtoPedidoController.getProdutoPedidoById(pedido.getIdPedido());
                System.out.println("Produtos do Pedido:");
                for (ProdutoPedido pp : produtoPedidos) {
                    System.out.println("ID Produto: " + pp.getProduto().getIdProduto());
                    System.out.println("Quantidade: " + pp.getQuantidade());
                    System.out.println("Valor Unitário: " + pp.getValorUnitario());
                }
            }
        }
    }
    
    private void deletePedido() {
        System.out.print("\nDigite o ID do Pedido que deseja deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            pedidoController.deletePedido(id);
            System.out.println("Pedido deletado com sucesso.");
        } catch (RuntimeException e) {
            System.out.println("Erro ao deletar pedido: " + e.getMessage());
        }
    }
}
