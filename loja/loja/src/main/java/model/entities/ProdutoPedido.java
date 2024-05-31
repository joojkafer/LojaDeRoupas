package model.entities;

import javax.persistence.*;

@Entity
@Table(name = "produtoPedido")
public
class ProdutoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProdutoPedido;
    
    @Column(name = "qtdProd")
    private int quantidade;
    
    @Column(name = "valorUnitario")
    private double valorUnitario;
    
    @ManyToOne
    @JoinColumn(name = "fk_idPedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "fk_idProduto")
    private Produto produto;
    
    public ProdutoPedido() {
	}

	public ProdutoPedido(int idProdutoPedido, Pedido pedido, Produto produto, int quantidade) {
		super();
		this.idProdutoPedido = idProdutoPedido;
		this.pedido = pedido;
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public int getIdProdutoPedido() {
		return idProdutoPedido;
	}
	public void setIdProdutoPedido(int idProdutoPedido) {
		this.idProdutoPedido = idProdutoPedido;
	}

	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
