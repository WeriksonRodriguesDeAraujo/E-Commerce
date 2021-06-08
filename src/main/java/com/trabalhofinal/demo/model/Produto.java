package com.trabalhofinal.demo.model;

import java.util.Date;

public class Produto {
    private Integer id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidadeEmEstoque;
    private Date dataDeCadastroDoProtudo;
    // tem que fazer a imagem aqui

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
    public Date getDataDeCadastroDoProtudo() {
        return dataDeCadastroDoProtudo;
    }
    public void setDataDeCadastroDoProtudo(Date dataDeCadastroDoProtudo) {
        this.dataDeCadastroDoProtudo = dataDeCadastroDoProtudo;
    }
    //#endregion
    

}
