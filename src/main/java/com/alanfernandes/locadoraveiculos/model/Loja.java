package com.alanfernandes.locadoraveiculos.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
public class Loja {

	public Loja() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@OneToOne
	@JoinColumn(name = "adress_id", referencedColumnName = "id")
	@JsonManagedReference
	private Endereco endereco;

	@OneToMany(mappedBy = "loja", fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<Veiculo> veiculos;

}
