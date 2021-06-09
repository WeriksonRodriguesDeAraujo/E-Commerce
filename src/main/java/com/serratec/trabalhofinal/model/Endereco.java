package com.serratec.trabalhofinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "endereco")
@SequenceGenerator(name ="generator_endereco", sequenceName = "sequence_endereco", initialValue = 1, allocationSize = 1)
public class Endereco {

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_endereco")
    private Integer id;

    @Column(nullable = false)
    private Integer cep;

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private String complemento;

    @Column(nullable = false)
    private String Estado;
    
    public Endereco() {}
    
	public Endereco(Integer id, Integer cep, String rua, String bairro, String cidade, Integer numero,
			String complemento, String estado) {
		this.id = id;
		this.cep = cep;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.numero = numero;
		this.complemento = complemento;
		Estado = estado;
	}

	//#region Getters e Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getCep() {
        return cep;
    }
    
    public void setCep(Integer cep) {
        this.cep = cep;
    }
    
    public String getRua() {
        return rua;
    }
    
    public void setRua(String rua) {
        this.rua = rua;
    }
    
    public String getBairro() {
        return bairro;
    }
    
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    public String getCidade() {
        return cidade;
    }
    
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public Integer getNumero() {
        return numero;
    }
    
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    
    public String getComplemento() {
        return complemento;
    }
    
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
    public String getEstado() {
        return Estado;
    }
    
    public void setEstado(String estado) {
        Estado = estado;
    }
    //#endregion  
}
