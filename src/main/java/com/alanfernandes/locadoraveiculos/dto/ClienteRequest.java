package com.alanfernandes.locadoraveiculos.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.alanfernandes.locadoraveiculos.model.Cliente;
import com.alanfernandes.locadoraveiculos.model.Endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequest {

	@NotNull
	@NotBlank
	private String nome;

	@NotNull
	@NotBlank
	private String sobrenome;

	@NotNull
	@NotBlank
	private String cpf;

	@NotNull
	private Integer idade;

	@NotNull
	@NotBlank
	private String telefone;

	@NotNull
	private Endereco endereco;

	public Cliente getCliente(ClienteRequest clienteRequest) {
		Cliente cliente = new Cliente();

		cliente.setNome(clienteRequest.getNome());
		cliente.setSobrenome(clienteRequest.getSobrenome());
		cliente.setCpf(clienteRequest.getCpf());
		cliente.setIdade(clienteRequest.getIdade());
		cliente.setTelefone(clienteRequest.getTelefone());
		cliente.setEndereco(clienteRequest.getEndereco());

		return cliente;
	}
}
