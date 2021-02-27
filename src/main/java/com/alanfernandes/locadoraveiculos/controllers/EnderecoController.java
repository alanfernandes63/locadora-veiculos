package com.alanfernandes.locadoraveiculos.controllers;

import javax.validation.Valid;

import com.alanfernandes.locadoraveiculos.enums.UF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alanfernandes.locadoraveiculos.dto.EnderecoRequest;
import com.alanfernandes.locadoraveiculos.makeResponse.MakeResponse;
import com.alanfernandes.locadoraveiculos.model.Endereco;
import com.alanfernandes.locadoraveiculos.service.EnderecoService;

@RestController
@RequestMapping(value = "api/v1")
public class EnderecoController {

	private EnderecoService enderecoService;

	public EnderecoController(@Autowired EnderecoService enderecoService) {
		super();
		this.enderecoService = enderecoService;
	}

	@PostMapping(value = "/endereco")
	public ResponseEntity<MakeResponse<Endereco>> save(@Valid @RequestBody EnderecoRequest enderecoRequest) {
		Endereco endereco = this.enderecoService.save(enderecoRequest.enderecoRequestToEndereco(enderecoRequest));
		MakeResponse<Endereco> makeEndereco = new MakeResponse<Endereco>(endereco, "Salvo com sucesso!");
		return new ResponseEntity<MakeResponse<Endereco>>(makeEndereco, HttpStatus.CREATED);
	}

}
