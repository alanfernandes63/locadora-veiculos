package com.alanfernandes.locadoraveiculos.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.alanfernandes.locadoraveiculos.model.Loja;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VeiculoRequest {

	@NotNull
	@NotBlank
	String modelo;

	@NotNull
	Long ano;

	@NotNull
	@NotBlank
	String placa;

	@NotNull
	@NotBlank
	String marca;

}
