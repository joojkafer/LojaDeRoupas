package model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "nome")
	private String nome;
	
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Pedido> pedidos;
	 
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Endereco> endereco;
	    
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<CartaoCredito> cartaoCredito;
	
	public Cliente(){
	}

	public Cliente(Integer idCliente, String cpf, String nome, Date dataCadastro, List<Pedido> pedidos,List<Endereco> endereco, List<CartaoCredito> cartaoCredito) {
		super();
		this.idCliente = idCliente;
		this.cpf = cpf;
		this.nome = nome;
		this.dataCadastro = dataCadastro;
		this.pedidos = pedidos;
		this.endereco = endereco;
		this.cartaoCredito = cartaoCredito;
	}

	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}
	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public List<CartaoCredito> getCartaoCredito() {
		return cartaoCredito;
	}
	public void setCartaoCredito(List<CartaoCredito> cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
	
	
	
}
