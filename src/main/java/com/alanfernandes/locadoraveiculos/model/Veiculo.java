package com.alanfernandes.locadoraveiculos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "veiculo",uniqueConstraints = @UniqueConstraint(columnNames = "placa"))
@Getter
@Setter
public class Veiculo {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	Long id;

	@Column(name = "modelo", nullable = false)
	@NotBlank
	String modelo;

	@Column(name = "ano", nullable = false, length = 4)
	Long ano;

	@Column(name = "placa", nullable = false)
	String placa;

	@Column(name = "marca", nullable = false)
	String marca;

	public Veiculo() {
		super();
	}

}
