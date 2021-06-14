package com.serratec.trabalhofinal.model;

import java.util.List;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;



import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect
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

    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String dataDeNascimento;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "cliente")
    @Autowired
    private List<Pedido> pedidos;
    
    public Cliente() {}
    
    public Cliente(Integer id, String email, String senha, String nome, String cpf, String telefone,
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

    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getDataDeNascimento() {
        return dataDeNascimento;
    }
    
    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
	
	public class CpfCnpjUtils {
        private final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        private final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		private String cpf;
		private String cnpj;
		
		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public String getCnpj() {
			return cnpj;
		}

		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}

        public boolean isValid(String cpfCnpj) {
        	this.cpf=cnpj;
            return (isValidCPF(cpfCnpj) || isValidCNPJ(cpfCnpj));
        }

        private int calcularDigito(String str, int[] peso) {
            int soma = 0;
            for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
                digito = Integer.parseInt(str.substring(indice,indice+1));
                soma += digito*peso[peso.length-str.length()+indice];
            }
            soma = 11 - soma % 11;
            return soma > 9 ? 0 : soma;
        }

        private String padLeft(String text, char character) {
            return String.format("%11s", text).replace(' ', character);
        }

        private boolean isValidCPF(String cpf) {
        	this.cpf=cpf;
            cpf = cpf.trim().replace(".", "").replace("-", "");
            if ((cpf==null) || (cpf.length()!=11)) return false;

            for (int j = 0; j < 10; j++)
                if (padLeft(Integer.toString(j), Character.forDigit(j, 10)).equals(cpf))
                    return false;

            Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
            Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
            return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
        }

        private boolean isValidCNPJ(String cnpj) {
        	this.cpf=cnpj;
            cnpj = cnpj.trim().replace(".", "").replace("-", "");
            if ((cnpj==null)||(cnpj.length()!=14)) return false;

            Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
            Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
            return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
        }

		
    }
    
}
