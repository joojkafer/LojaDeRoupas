package controller;

import java.util.List;

import model.entities.Produto;
import model.repositories.ProdutoRepository;

public class ProdutoController {

    private ProdutoRepository produtoRepository = new ProdutoRepository();

    public void createProduto(String nome, String tipo, String marca, String tamanho, String descProd, Double valorProd, Integer qtdProd) {
        Produto produto = new Produto(null, nome, tipo, marca, tamanho, descProd, valorProd, qtdProd);
        produtoRepository.create(produto);
    }

    public Produto getProdutoById(Integer id) {
        return produtoRepository.findById(id);
    }
    
    public List<Produto> findAllProdutos() {
        return produtoRepository.findAll();
    }

    public void updateProduto(Integer id, String nome, String tipo, String marca, String tamanho, String descProd, Double valorProd) {
        Produto produto = produtoRepository.findById(id);
        if (produto != null) {
            produto.setNome(nome);
            produto.setTipo(tipo);
            produto.setMarca(marca);
            produto.setTamanho(tamanho);
            produto.setDescProd(descProd);
            produto.setValorProd(valorProd);
            produtoRepository.updateById(produto);
        } else {
            System.out.println("Produto nao encontrado.");
        }
    }

    public void deleteProduto(Integer id) {
        Produto produto = produtoRepository.findById(id);
        if (produto != null) {
            produtoRepository.delete(id);
        } else {
            System.out.println("Produto nao encontrado.");
        }
    }
    
    public void addStorage(Integer id, int qtd) {
        Produto produto = produtoRepository.findById(id);
        if (produto != null) {
            produto.setQtdProd(produto.getQtdProd() + qtd);
            produtoRepository.updateById(produto);
        } else {
            throw new RuntimeException("Produto nao encontrado.");
        }
    }

    public void removeStorage(Integer id, int qtd) {
        Produto produto = produtoRepository.findById(id);
        if (produto != null) {
            if (produto.getQtdProd() >= qtd) {
                produto.setQtdProd(produto.getQtdProd() - qtd);
                produtoRepository.updateById(produto);
            } else {
                throw new RuntimeException("Quantidade a ser removida maior que a atual.");
            }
        } else {
            throw new RuntimeException("Produto nao encontrado.");
        }
    }
}
