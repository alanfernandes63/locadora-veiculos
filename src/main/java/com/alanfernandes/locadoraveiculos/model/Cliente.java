package com.alanfernandes.locadoraveiculos.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente",uniqueConstraints = @UniqueConstraint(columnNames = "cpf"))
@Getter
@Setter
public class Cliente {

	public Cliente() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome", nullable = false)
	@NotBlank
	private String nome;

	@Column(name = "sobrenome", nullable = false)
	@NotBlank
	private String sobrenome;

	@Column(name = "cpf")
	@CPF
	@NotBlank
	private String cpf;

	@Column(name = "idade", nullable = false)
	private Integer idade;

	@Column(name = "telefone", nullable = false)
	@NotBlank
	private String telefone;

	@OneToOne(cascade = CascadeType.ALL)
	@NotNull
	private Endereco endereco;
}
