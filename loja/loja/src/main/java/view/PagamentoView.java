package view;

import controller.PagamentoController;
import controller.PedidoController;
import model.entities.Pagamento;
import model.entities.Pedido;
import java.util.Date;
import java.util.Scanner;

public class PagamentoView {

    private PagamentoController pagamentoController = new PagamentoController();
    private PedidoController pedidoController = new PedidoController();
    private Scanner scanner = new Scanner(System.in);
    
    public void menu() {
        while (true) {
            System.out.println("________________________");
            System.out.println("       MENU PAGAMENTO");
            System.out.println("1. Realizar Pagamento");
            System.out.println("2. Consultar Pagamento por ID");
            System.out.println("3. Deletar Pagamento");
            System.out.println("4. Sair");
            System.out.print("\nEscolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    doPagamento();
                    break;
                case 2:
                    findPagamentoById();
                    break;
                case 3:
                    deletePagamento();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void doPagamento() {
        System.out.println("\nGerar novo pagamento");

        System.out.print("\nDigite o ID do Pedido: ");
        int idPedido = scanner.nextInt();
        scanner.nextLine();
        
        Pedido pedido = pedidoController.getPedidoById(idPedido);
        if (pedido == null) {
            System.out.println("Pedido não encontrado.");
            return;
        } else {
            System.out.println("Pedido encontrado!\n");

            double valorDoPedido = pedido.getValorTotal();
            
            System.out.println("ID do Pedido: " + pedido.getIdPedido());
            System.out.println("Valor do Pedido: " + valorDoPedido);
            
            Date dataPagamento = new Date();
            
            int metodoPagamento;
            metodoPagamento = payPagamento(idPedido, valorDoPedido);
            
            boolean status = true;

            pagamentoController.createPagamento(idPedido, metodoPagamento, dataPagamento, status, valorDoPedido);
            System.out.println("\nPagamento efetuado com sucesso!");
        }
    }

    private int payPagamento(int idPedido, double valorPedido) {
        System.out.println("\n1 - Dinheiro");
        System.out.println("2 - PIX");
        System.out.println("3 - Cartão de Crédito");
        System.out.print("Escolha o método de pagamento: ");
        int metodoPagamento = scanner.nextInt();
        scanner.nextLine();

        switch (metodoPagamento) {
            case 1:
                System.out.println("\nMetodo de pagamento escolhido: Dinheiro");

                double valorRecebido;
                do {
                    System.out.print("Digite o valor recebido: ");
                    valorRecebido = scanner.nextDouble();
                    scanner.nextLine();

                    if (valorRecebido < valorPedido) {
                        System.out.println("\nValor recebido e menor que o valor do pedido.");
                    }
                } while (valorRecebido < valorPedido);

                if (valorRecebido == valorPedido) {
                    System.out.println("\nPagamento concluido com sucesso!");
                } else {
                    double troco = valorRecebido - valorPedido;
                    System.out.println("\nTroco: " + troco);
                }
                break;

            case 2:
                System.out.println("\nMetodo de pagamento escolhido: PIX");
                break;

            case 3:
                System.out.println("\nMetodo de pagamento escolhido: Cartão de Crédito");

                int numParcelas;
                do {
                    System.out.print("Digite o numero de parcelas: ");
                    numParcelas = scanner.nextInt();
                    scanner.nextLine();

                    if (numParcelas < 1) {
                        System.out.println("O numero minimo de parcelas e 1.\n");
                    }
                } while (numParcelas < 1);

                double valorParcela = valorPedido / numParcelas;
                System.out.println("Valor da parcela: " + valorParcela);
                break;

            default:
                System.out.println("Opcao invalida!");
        }

        return metodoPagamento;
    }
    
    private void findPagamentoById() {
        System.out.print("\nDigite o ID do Pagamento: ");
        int idPagamento = scanner.nextInt();
        scanner.nextLine();
        
        Pagamento pagamento = pagamentoController.findPagamentoById(idPagamento);
        
        if (pagamento != null) {
            System.out.println("\nPagamento encontrado!\n");
            System.out.println("ID do Pagamento: " + pagamento.getIdPagamento());
            System.out.println("Metodo de Pagamento: " + pagamento.getMetodoPagamento());
            System.out.println("Data do Pagamento: " + pagamento.getDataPagamento());
            System.out.println("Valor Final: " + pagamento.getValorFinal());
            System.out.println("Status do Pagamento: " + pagamento.getStatusPagamento());
        } else {
            System.out.println("\nPagamento não encontrado.");
        }
    }

    private void deletePagamento() {
        System.out.print("\nDigite o ID do Pagamento: ");
        int idPagamento = scanner.nextInt();
        scanner.nextLine();
        
        pagamentoController.deletePagamento(idPagamento);
    }
}
