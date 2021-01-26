package com.alanfernandes.locadoraveiculos.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.alanfernandes.locadoraveiculos.model.Veiculo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

	public Veiculo getVeiculo(VeiculoRequest veiculoRequest) {
		Veiculo veiculo = new Veiculo();
		veiculo.setAno(veiculoRequest.getAno());
		veiculo.setMarca(veiculoRequest.getMarca());
		veiculo.setModelo(veiculoRequest.getModelo());
		veiculo.setPlaca(veiculoRequest.getPlaca());
		return veiculo;
	}
}
