package com.alanfernandes.locadoraveiculos.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.alanfernandes.locadoraveiculos.model.Endereco;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LojaRequest {

	@NotBlank
	@NotNull
	private String nome;

	@NotNull
	@Valid
	private EnderecoRequest enderecoRequest;

}
