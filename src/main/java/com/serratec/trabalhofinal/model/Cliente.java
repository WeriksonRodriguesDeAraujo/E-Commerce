package com.serratec.trabalhofinal.model;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "cliente")
@SequenceGenerator(name ="generator_cliente", sequenceName = "sequence_cliente", initialValue = 1, allocationSize = 1)
public class Cliente implements UserDetails {

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_cliente")
    private Integer id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String senha;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private Integer telefone;

    @Column(nullable = false)
    private String dataDeNascimento;
    
    public Cliente() {}
    
    public Cliente(Integer id, String email, String senha, String nome, String cpf, Integer telefone,
			String dataDeNascimento) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataDeNascimento = dataDeNascimento;
	}

    //#region Getters e Setters
	public Integer getId() {
        return id;
    }
	
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCpf() {
        return cpf;
    }

    public Integer getTelefone() {
        return telefone;
    }
    
    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }
    
    public String getDataDeNascimento() {
        return dataDeNascimento;
    }
    
    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }
    //#endregion
    
    
    /* User Details Implements */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return senha;
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		return email;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}
}
