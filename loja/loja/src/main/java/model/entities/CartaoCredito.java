package model.entities;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "cartaoCredito")
public class CartaoCredito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCartaoCredito;
	
	@Column(name = "nomeCartao")
	private String nomeCartao;
	
	@Column(name = "numero")
	private String nmrCartao;
	
	@Column(name = "cvv")
	private String cvv;
	
	@Temporal(TemporalType.DATE)
	private Date dataValidade;
	
	@ManyToOne
    @JoinColumn(name = "fk_idCliente")
    private Cliente cliente;

	public CartaoCredito(){
	}

	public CartaoCredito(Integer idCartaoCredito, String nomeCartao, String nmrCartao, String cvv, Date dataValidade, Cliente cliente) {
	    super();
	    this.idCartaoCredito = idCartaoCredito;
	    this.nomeCartao = nomeCartao;
	    this.nmrCartao = nmrCartao;
	    this.cvv = cvv;
	    this.dataValidade = dataValidade;
	    this.cliente = cliente;
	}

	//new CartaoCredito(null, nmrCartao, cvv, dataValidade, nome, idCliente);

	public Integer getIdCartaoCredito() {
		return idCartaoCredito;
	}
	public void setIdCartaoCredito(Integer idCartaoCredito) {
		this.idCartaoCredito = idCartaoCredito;
	}

	public String getNomeCartao() {
		return nomeCartao;
	}
	public void setNomeCartao(String nomeCartao) {
		this.nomeCartao = nomeCartao;
	}

	public String getNmrCartao() {
		return nmrCartao;
	}
	public void setNmrCartao(String nmrCartao) {
		this.nmrCartao = nmrCartao;
	}

	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Date getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}

