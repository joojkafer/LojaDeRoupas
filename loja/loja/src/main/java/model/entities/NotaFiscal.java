package model.entities;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "notaFiscal")
public class NotaFiscal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idNotaFiscal;
	
	@Column(name = "valorTotal")
	private Double valorNF;
	
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;
	
	@OneToOne
    @JoinColumn(name = "fk_idPedido")
    private Pedido pedido;
	
	public NotaFiscal() {
	}

	public NotaFiscal(Integer idNotaFiscal, Double valorNF, Date dataEmissao, Pedido pedido) {
		this.idNotaFiscal = idNotaFiscal;
		this.valorNF = valorNF;
		this.dataEmissao = dataEmissao;
		this.pedido = pedido;
	}

	public Integer getIdNotaFiscal() {
		return idNotaFiscal;
	}
	public void setIdNotaFiscal(Integer idNotaFiscal) {
		this.idNotaFiscal = idNotaFiscal;
	}

	public Double getValorNF() {
		return valorNF;
	}
	public void setValorNF(Double valorNF) {
		this.valorNF = valorNF;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
