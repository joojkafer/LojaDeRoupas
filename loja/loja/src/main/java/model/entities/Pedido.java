package model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    @Column(name = "valorTotal")
    private Double valorTotal;

    @Column(name = "valorDesconto")
    private Double valorDesconto;

    @Temporal(TemporalType.DATE)
    private Date dataPedido;

    @ManyToOne
    @JoinColumn(name = "fk_idVendedor")
    private Vendedor vendedor;

    @ManyToOne
    @JoinColumn(name = "fk_idCliente")
    private Cliente cliente;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Pagamento pagamento;

    @ManyToMany
    @JoinTable(
        name = "produtoPedido",
        joinColumns = @JoinColumn(name = "fk_idPedido"),
        inverseJoinColumns = @JoinColumn(name = "fk_idProduto")
    )
    private List<Produto> produtos;

	public Pedido() {
	}
	
	public Pedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

	public Pedido(Integer idPedido, Double valorTotal, Double valorDesconto, Date dataPedido, Vendedor vendedor, Cliente cliente, Pagamento pagamento) {
		super();
		this.idPedido = idPedido;
		this.valorTotal = valorTotal;
		this.valorDesconto = valorDesconto;
		this.dataPedido = dataPedido;
		this.vendedor = vendedor;
		this.cliente = cliente;
		this.pagamento = pagamento;
	}

	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	
}
