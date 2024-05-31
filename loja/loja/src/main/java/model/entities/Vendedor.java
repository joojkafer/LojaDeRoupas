package model.entities;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "vendedor")
public class Vendedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVendedor;
	
	@Column (name = "cpf")
	private String cpf;
	
	@Column (name = "nome")
	private String nome;
	
	@OneToMany(mappedBy = "vendedor")
    private List<Pedido> pedidos;
	
	public Vendedor() {
	}

	public Vendedor(Integer idVendedor, String cpf, String nome, List<Pedido> pedidos) {
		super();
		this.idVendedor = idVendedor;
		this.cpf = cpf;
		this.nome = nome;
		this.pedidos = pedidos;
	}

	public Integer getIdVendedor() {
		return idVendedor;
	}
	public void setIdVendedor(Integer idVendedor) {
		this.idVendedor = idVendedor;
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

	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
