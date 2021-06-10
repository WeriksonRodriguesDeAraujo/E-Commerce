package com.serratec.trabalhofinal.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity 
@Table(name = "pedido")
@SequenceGenerator(name ="generator_pedido", sequenceName = "sequence_pedido", initialValue = 1, allocationSize = 1)
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_pedido")
	private Integer id;
	
	@Column(nullable = false)
	private Integer numero;
	
	
	private ArrayList<Produto> produtos;
	
	@Column(nullable = false)
	private Integer clienteId;
	
	private Date data;
	
	@Column(nullable = false)
	private Double valorTotal;
	
	@Column(nullable = false)
	private String status;
	

	public Pedido() {}

	
	public Pedido(
			Integer id, 
			Integer numero, 
			ArrayList<Produto> produtos, 
			Integer clienteId, 
			Date data,
			Double valorTotal, 
			String status) {
		
		this.id = id;
		this.numero = numero;
		this.produtos = produtos;
		this.clienteId = clienteId;
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
	
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public Integer getClienteId() {
		return clienteId;
	}
	
	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
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
	
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public Boolean validoParaCadastro(){
        return(!this.produtos.isEmpty());
    }  
	
}
