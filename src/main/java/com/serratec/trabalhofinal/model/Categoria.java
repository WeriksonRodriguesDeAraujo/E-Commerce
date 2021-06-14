package com.serratec.trabalhofinal.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categoria")
@SequenceGenerator(name ="generator_categoria", sequenceName = "sequence_categoria", initialValue = 1, allocationSize = 1)
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_produto")
	private Integer id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String descricao;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
	@Autowired
	private List<Produto> produtos;
	
	public Categoria() {}

	
	public Categoria(Integer id, String nome, String descricao, List<Produto> produtos) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.produtos = produtos;
	}
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public boolean validoParaCadastro() {
		return (!this.nome.isEmpty());
	}


	public List<Produto> getProduto() {
		return produtos;
	}


	public void setProduto(List<Produto> produtos) {
		this.produtos = produtos;
	}	

}
