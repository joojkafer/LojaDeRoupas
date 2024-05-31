package model.entities;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "pagamento")
public class Pagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPagamento;
	
	@Column(name = "metodoPagamento")
	private Integer metodoPagamento;
	
	@Column(name = "statusPagamento")
	private Boolean statusPagamento;
	
	@Column(name = "dataPagamento")
	private Date dataPagamento;
	
	@Column(name = "valorFinal")
	private Double valorFinal;
	
	@OneToOne
    @JoinColumn(name = "fk_idPedido")
    private Pedido pedido;
	
	public Pagamento() {
	}

	public Pagamento(Integer idPagamento, Integer metodoPagamento, Boolean statusPagamento, Date dataPagamento, Double valorFinal, Pedido pedido) {
		super();
		this.idPagamento = idPagamento;
		this.metodoPagamento = metodoPagamento;
		this.statusPagamento = statusPagamento;
		this.dataPagamento = dataPagamento;
		this.valorFinal = valorFinal;
		this.pedido = pedido;
	}

	public Integer getIdPagamento() {
		return idPagamento;
	}
	public void setIdPagamento(Integer idPagamento) {
		this.idPagamento = idPagamento;
	}

	public Integer getMetodoPagamento() {
		return metodoPagamento;
	}
	public void setMetodoPagamento(Integer metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public Boolean getStatusPagamento() {
		return statusPagamento;
	}
	public void setStatusPagamento(Boolean statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Double getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
