package view;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\nESCOLHA UMA OPCAO\n");
            System.out.println("1. Cliente");
            System.out.println("2. Vendedor");
            System.out.println("3. Produto");
            System.out.println("4. Pedido");
            System.out.println("5. Pagamentos");
            System.out.println("6. Nota Fiscal");
            System.out.println("7. Sair");
            System.out.print("\nEscolha uma opçao: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                	ClienteView clienteView = new ClienteView();
                    clienteView.menu();
                    break;
                case 2:
                    VendedorView vendedorView = new VendedorView();
                    vendedorView.menu();
                    break;
                case 3:
                    ProdutoView produtoView = new ProdutoView();
                    produtoView.menu();
                    break;
                case 4:
                    PedidoView pedidoView = new PedidoView();
                    pedidoView.menu();
                    break;
                case 5:
                    PagamentoView pagamentoView = new PagamentoView();
                    pagamentoView.menu();
                    break;
                case 6:
                    NotaFiscalView notaFiscalView = new NotaFiscalView();
                    notaFiscalView.menu();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (opcao != 7);
        scanner.close();
    }
}
