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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "produto")
@SequenceGenerator(name ="generator_produto", sequenceName = "sequence_produto", initialValue = 1, allocationSize = 1)
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_produto")
    private Integer id;
	
	@Column(nullable = false)
    private String nome;
	
	@Column(nullable = false)
    private String descricao;
	
	@Column(nullable = false)
    private Double preco;
	
	@Column(nullable = false)
    private Integer quantidadeEmEstoque;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "produtos")
	@Autowired
	private List<Pedido> pedidos;
	
	@JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoria;
  
  // tem que fazer a imagem aqui
  
	private Date dataDeCadastro;
	
	public Produto() {
		this.dataDeCadastro = new Date();
	}

	public Produto(Integer id, String nome, String descricao, Double preco, Integer quantidadeEmEstoque,
			Date dataDeCadastro, List<Pedido> pedidos, Categoria categoria) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidadeEmEstoque = quantidadeEmEstoque;
		this.dataDeCadastro = new Date();
		this.pedidos = pedidos;
		this.categoria = categoria;
	}

	//#region Getters e Setters
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
    
    public Double getPreco() {
        return preco;
    }
    
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    
    public Integer getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }
    
    public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }
    
    public Date getDataDeCadastro() {
        return dataDeCadastro;
    }
    
    public void setDataDeCadastro(Date dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
    
    public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void relacionarComCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
  //#endregion
}
