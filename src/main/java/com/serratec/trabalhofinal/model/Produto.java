package com.serratec.trabalhofinal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	
    private Date dataDeCadastro;
	
    // tem que fazer a imagem aqui
	
	public Produto() {}

	public Produto(Integer id, String nome, String descricao, Double preco, Integer quantidadeEmEstoque,
			Date dataDeCadastro) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidadeEmEstoque = quantidadeEmEstoque;
		this.dataDeCadastro = new Date();
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
    //#endregion
}
