package view;

import controller.NotaFiscalController;
import controller.PedidoController;
import model.entities.NotaFiscal;
import model.entities.Pedido;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class NotaFiscalView {
	
	private PedidoController pedidoController = new PedidoController();
    private NotaFiscalController notaFiscalController = new NotaFiscalController();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("________________________");
            System.out.println("      MENU NOTA FISCAL");
            System.out.println("1. Emitir Nota Fiscal");
            System.out.println("2. Consultar Nota Fiscal por ID");
            System.out.println("3. Consultar todos");
            System.out.println("4. Deletar Nota Fiscal");
            System.out.println("5. Sair");
            System.out.print("\nEscolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    createNotaFiscal();
                    break;
                case 2:
                    findNotaFiscalById();
                    break;
                case 3:
                    findAllNotaFiscal();
                    break;
                case 4:
                    deleteNotaFiscal();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private void createNotaFiscal() {
    	System.out.print("\nGerar Nota Fiscal\n");
        System.out.print("Digite o ID do Pedido: ");
        int idPedido = scanner.nextInt();
        scanner.nextLine();
        
        Pedido pedido = pedidoController.getPedidoById(idPedido);
        if (pedido == null) {
            System.out.println("\nPedido nao encontrado.");
            return;
        }

        double valorTotal = pedido.getValorTotal();
        Date dataEmissao = new Date();
        
        System.out.println("\nID do Pedido: " + pedido.getIdPedido());
        System.out.println("Valor do Pedido: " + valorTotal);

        notaFiscalController.createNotaFiscal(idPedido, valorTotal, dataEmissao);
        System.out.println("\nNota Fiscal criada com sucesso!");
    }

    private void findNotaFiscalById() {
        System.out.print("\nDigite o ID da Nota Fiscal: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        NotaFiscal notaFiscal = notaFiscalController.findNotaFiscalById(id);
        if (notaFiscal != null) {
            System.out.println("\nNota Fiscal encontrada:");
            System.out.println("ID: " + notaFiscal.getIdNotaFiscal());
            System.out.println("ID Pedido: " + notaFiscal.getPedido().getIdPedido());
            System.out.println("Valor Total: " + notaFiscal.getValorNF());
            System.out.println("Data de Emissao: " + notaFiscal.getDataEmissao());
        } else {
            System.out.println("\nNota Fiscal nao encontrada.");
        }
    }

    private void findAllNotaFiscal() {
        List<NotaFiscal> notasFiscais = notaFiscalController.findAllNotaFiscal();
        if (notasFiscais.isEmpty()) {
            System.out.println("\nNenhuma Nota Fiscal encontrada.");
        } else {
            System.out.println("\nLista de Notas Fiscais:\n");
            for (NotaFiscal notaFiscal : notasFiscais) {
                System.out.println("ID: " + notaFiscal.getIdNotaFiscal());
                System.out.println("ID Pedido: " + notaFiscal.getPedido().getIdPedido());
                System.out.println("Valor Total: " + notaFiscal.getValorNF());
                System.out.println("Data de Emissao: " + notaFiscal.getDataEmissao());
                System.out.println("________________________");
            }
        }
    }

    private void deleteNotaFiscal() {
        System.out.print("Digite o ID da Nota Fiscal: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        notaFiscalController.deleteNotaFiscal(id);
        System.out.println("Nota Fiscal deletada com sucesso!");
    }
}
