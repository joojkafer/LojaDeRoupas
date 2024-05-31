package model.entities;

import javax.persistence.*;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduto;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "marca")
    private String marca;

    @Column(name = "tamanho")
    private String tamanho;

    @Column(name = "descricaoProd")
    private String descProd;

    @Column(name = "valorProd")
    private Double valorProd;

    @Column(name = "qtdEstoqueProd")
    private Integer qtdProd;

    public Produto() {
    }

    public Produto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Produto(Integer idProduto, String nome, String tipo, String marca, String tamanho, String descProd,
                   Double valorProd, Integer qtdProd) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.tipo = tipo;
        this.marca = marca;
        this.tamanho = tamanho;
        this.descProd = descProd;
        this.valorProd = valorProd;
        this.qtdProd = qtdProd;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getDescProd() {
        return descProd;
    }

    public void setDescProd(String descProd) {
        this.descProd = descProd;
    }

    public Double getValorProd() {
        return valorProd;
    }

    public void setValorProd(Double valorProd) {
        this.valorProd = valorProd;
    }

    public Integer getQtdProd() {
        return qtdProd;
    }

    public void setQtdProd(Integer qtdProd) {
        this.qtdProd = qtdProd;
    }

}
