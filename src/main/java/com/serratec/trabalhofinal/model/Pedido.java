package com.serratec.trabalhofinal.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity 
@Table(name = "pedido")
@SequenceGenerator(name ="generator_pedido", sequenceName = "sequence_pedido", initialValue = 1, allocationSize = 1)
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_pedido")
	private Integer id;
	
	@Column(nullable = false)
	private Integer numero;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "produtos_pedidos",
			   joinColumns = {@JoinColumn(name = "pedido_id")},
			   inverseJoinColumns = {@JoinColumn(name = "produto_id")})
	private List<Produto> produtos;
	
	private Date data;
	
	@Column(nullable = false)
	private Double valorTotal;
	
	@Column(nullable = false)
	private Boolean status;
	
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

	public Pedido() {
		this.data = new Date();
	}

	public Pedido(
			Integer id, 
			Integer numero, 
			List<Produto> produtos, 
			Date data,
			Double valorTotal, 
			Boolean status) {
		
		this.id = id;
		this.numero = numero;
		this.produtos = produtos;
		this.data = data;
		this.valorTotal = valorTotal;
		this.status = status;
	}
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getNumero() {
		return numero;
	}
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public Double getValorTotal() {
		return valorTotal;
	}
	
	public Boolean getStatus() {
		return status;
	}
	
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void relacionarCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Boolean validoParaCadastro(){
		return(!this.produtos.isEmpty());
	}  
	
	public void relacionarComProduto(Produto produto) {
		produtos.add(produto);
	}
}
