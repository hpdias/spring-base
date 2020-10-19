package com.github.hpdias.base.api.entity;

import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "usuario"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})
public class Usuario{
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=3, max = 50)
    private String nome;

    @NotBlank
    @Size(min=3, max = 50)
    private String usuario;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min=6, max = 100)
    private String senha;
    
    @NotBlank
    @Size(min=11, max = 11)
    private String cpf;
    
    @Size(min=14, max = 14)
    private String cnpj;
    
    @NotBlank
    @Size(min=6, max = 100)
    @Column(name = "end_bairro")
    private String endBairro;
    
    @NotBlank
    @Size(min=1, max = 100)
    @Column(name = "end_rua")
    private String endRua;
    
    @NotBlank
    @Size(min=1, max = 10)
    @Column(name = "end_numero")
    private String endNumero;
    
    @NotBlank
    @Size(min=1, max = 10)
    @Column(name = "end_cep")
    private String endCep;
 
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_role", 
    	joinColumns = @JoinColumn(name = "id_usuario"), 
    	inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles = new HashSet<>();
    
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "usuario_cliente", 
//    	joinColumns = @JoinColumn(name = "id_usuario"), 
//    	inverseJoinColumns = @JoinColumn(name = "id_cliente"))
//    private Set<Cliente> clientes = new HashSet<>();

    public Usuario() {}

	public Usuario( @NotBlank @Size(min = 3, max = 50) String nome,
			@NotBlank @Size(min = 3, max = 50) String usuario, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(min = 6, max = 100) String senha, @NotBlank @Size(min = 11, max = 11) String cpf,
			@Size(min = 14, max = 14) String cnpj, @Size(min = 6, max = 100) String endBairro,
			@Size(min = 1, max = 10) String endRua, @Size(min = 1, max = 10) String endCep, @Size(min = 1, max = 10) String endNumero) {
		this.nome = nome;
		this.usuario = usuario;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.endBairro = endBairro;
		this.endRua = endRua;
		this.endCep = endCep;
		this.endNumero = endNumero;
	}


}