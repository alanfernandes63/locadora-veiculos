package com.alanfernandes.locadoraveiculos.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.alanfernandes.locadoraveiculos.enums.UF;
import com.alanfernandes.locadoraveiculos.model.Endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequest {

	@NotNull
	@NotBlank
	private String cep;

	@NotNull
	@NotBlank
	private String logradouro;

	@NotNull
	@NotBlank
	private String bairro;

	@NotNull
	@NotBlank
	private String cidade;

	@NotNull
	private UF uf;

	@NotNull
	private Long numero;

	public Endereco enderecoRequestToEndereco(EnderecoRequest enderecoRequest) {
		Endereco endereco = new Endereco();

		endereco.setCep(enderecoRequest.getCep());
		endereco.setLogradouro(enderecoRequest.getLogradouro());
		endereco.setCidade(enderecoRequest.getCidade());
		endereco.setBairro(enderecoRequest.getBairro());
		endereco.setUf(enderecoRequest.getUf());
		endereco.setNumero(enderecoRequest.getNumero());

		return endereco;
	}
}
