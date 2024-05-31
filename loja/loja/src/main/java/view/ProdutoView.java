package view;

import controller.ProdutoController;
import model.entities.Produto;

import java.util.List;
import java.util.Scanner;

public class ProdutoView {

    private ProdutoController produtoController = new ProdutoController();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("________________________");
            System.out.println("     MENU PRODUTOS");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Consultar Produto por ID");
            System.out.println("3. Consultar todos ");
            System.out.println("4. Adicionar estoque");
            System.out.println("5. Remover estoque");
            System.out.println("6. Atualizar Produto");
            System.out.println("7. Deletar Produto");
            System.out.println("8. Sair");
            System.out.print("\nEscolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    createProduto();
                    break;
                case 2:
                    findProdutoById();
                    break;
                case 3:
                    findAllProduto();
                    break;
                case 4:
                    addStorage();
                    break;
                case 5:
                	removeStorage();
                    break;
                case 6:
                    updateProduto();
                    break;
                case 7:
                    deleteProduto();
                    break;
                case 8:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void createProduto() {
        System.out.println("\nCadastrar Produto");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();
        
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        
        System.out.print("Tamanho: ");
        String tamanho = scanner.nextLine();
        
        System.out.print("Descricao: ");
        String descricaoProd = scanner.nextLine();
        
        System.out.print("Preco: ");
        Double valorProd = scanner.nextDouble();
        
        System.out.print("Quantidade em Estoque: ");
        int qtdProd = scanner.nextInt();
        
        produtoController.createProduto(nome, tipo, marca, tamanho, descricaoProd, valorProd, qtdProd);
        System.out.println("\nProduto cadastrado com sucesso!");
    }

    private void findProdutoById() {
        int id;
        
        System.out.print("\nDigite o ID do Produto: ");
        id = scanner.nextInt();
        scanner.nextLine();
        
        Produto produto = produtoController.getProdutoById(id);
        
        if (produto != null) {
            System.out.println("Produto encontrado!\n");
            System.out.println("ID: " + produto.getIdProduto());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Tipo: " + produto.getTipo());
            System.out.println("Marca: " + produto.getMarca());
            System.out.println("Tamanho: " + produto.getTamanho());
            System.out.println("Descricao: " + produto.getDescProd());
            System.out.println("Preço: " + produto.getValorProd());
            System.out.println("Quantidade em Estoque: " + produto.getQtdProd());
        } else {
            System.out.println("\nProduto não encontrado.");
        }
    }
    
    private void findAllProduto() {
        List<Produto> produtos = produtoController.findAllProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            System.out.println("Lista de Produtos:");
            for (Produto produto : produtos) {
                System.out.println("________________________");
                System.out.println("ID: " + produto.getIdProduto());
                System.out.println("Nome: " + produto.getNome());
                System.out.println("Tipo: " + produto.getTipo());
                System.out.println("Marca: " + produto.getMarca());
                System.out.println("Tamanho: " + produto.getTamanho());
                System.out.println("Descrição: " + produto.getDescProd());
                System.out.println("Valor: " + produto.getValorProd());
                System.out.println("Quantidade em Estoque: " + produto.getQtdProd());
            }
        }
    }
    
    private void addStorage() {
        System.out.print("\nDigite o ID do Produto: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite a quantidade a ser adicionada ao estoque: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        try {
            produtoController.addStorage(id, quantidade);
            System.out.println("\nEstoque atualizado com sucesso!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void removeStorage() {
        System.out.print("\nDigite o ID do Produto: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite a quantidade a ser removida do estoque: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        try {
            produtoController.removeStorage(id, quantidade);
            System.out.println("\nEstoque atualizado com sucesso!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateProduto() {
        int id;
        
        System.out.print("\nDigite o ID do Produto que deseja atualizar: ");
        id = scanner.nextInt();
        scanner.nextLine();
        
        Produto produto = produtoController.getProdutoById(id);
        
        if (produto != null) {
            System.out.print("Digite o novo Nome: ");
            String novoNome = scanner.nextLine();
            
            System.out.print("Digite a nova Tipo: ");
            String novoTipo = scanner.nextLine();
            
            System.out.print("Digite a nova Marca: ");
            String novaMarca = scanner.nextLine();

            System.out.print("Digite a nova Tamanho: ");
            String novoTamanho = scanner.nextLine();
            
            System.out.print("Digite a nova Descrição: ");
            String novaDescricao = scanner.nextLine();
            
            System.out.print("Digite o novo Preço: ");
            double novoPreco = scanner.nextDouble();
            scanner.nextLine();
            									//nome, tipo, marca, tamanho, descricaoProd, valorProd, qtdProd
            produtoController.updateProduto(id, novoNome, novoTipo, novaMarca, novoTamanho, novaDescricao, novoPreco);
        } else {
            System.out.println("\nProduto não encontrado.");
        }
    }

    private void deleteProduto() {
        int id;
        
        System.out.print("\nDigite o ID do Produto que deseja deletar: ");
        id = scanner.nextInt();
        scanner.nextLine();
        
        Produto produto = produtoController.getProdutoById(id);
        
        if (produto != null) {
            produtoController.deleteProduto(id);
            System.out.println("\nProduto deletado com sucesso!");
        } else {
            System.out.println("\nProduto não encontrado.");
        }
    }
}
