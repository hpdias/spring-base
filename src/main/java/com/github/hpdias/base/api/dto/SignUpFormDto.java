package com.github.hpdias.base.api.dto;

import java.util.Set;

import javax.validation.constraints.*;


public class SignUpFormDto {
    @NotBlank
    @Size(min = 3, max = 50)
    private String nome;

    @NotBlank
    @Size(min = 3, max = 50)
    private String usuario;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String senha;
    
    @NotBlank
    @Size(min=11, max = 11)
    private String cpf;
    
    @Size(min=14, max = 14)
    private String cnpj;
    
    @NotBlank
    @Size(min=6, max = 100)
    private String endBairro;
    
    @NotBlank
    @Size(min=1, max = 100)
    private String endRua;
    
    @NotBlank
    @Size(min=1, max = 10)
    private String endCep;
    
    @NotBlank
    @Size(min=1, max = 10)
    private String endNumero;
    
    @NotBlank
    private Integer idCidade;
    
    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	public String getEndBairro() {
		return endBairro;
	}

	public void setEndBairro(String endBairro) {
		this.endBairro = endBairro;
	}

	public String getEndRua() {
		return endRua;
	}

	public void setEndRua(String endRua) {
		this.endRua = endRua;
	}

	public String getEndCep() {
		return endCep;
	}

	public void setEndCep(String endCep) {
		this.endCep = endCep;
	}

	public Integer getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}

	public Set<String> getRole() {
    	return this.role;
    }
    
    public void setRole(Set<String> role) {
    	this.role = role;
    }

	public String getEndNumero() {
		return endNumero;
	}

	public void setEndNumero(String endNumero) {
		this.endNumero = endNumero;
	}
    
    
    
}