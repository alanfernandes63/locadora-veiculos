package com.alanfernandes.locadoraveiculos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "endereco")
@Getter
@Setter
public class Endereco {

	public Endereco() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "cep", nullable = false)
	@NotBlank
	private String cep;

	@Column(name = "logradouro", nullable = false)
	@NotBlank
	private String logradouro;

	@Column(name = "bairro", nullable = false)
	@NotBlank
	private String bairro;

	@Column(name = "cidade", nullable = false)
	@NotBlank
	private String cidade;

	@Column(name = "uf", nullable = false)
	@NotBlank
	private String uf;

	@Column(name = "numero", nullable = false)
	private Long numero;

	@OneToOne(mappedBy = "endereco")
	@JsonBackReference
	private Loja loja;
}
