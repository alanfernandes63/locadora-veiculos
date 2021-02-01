package com.alanfernandes.locadoraveiculos.controllers;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alanfernandes.locadoraveiculos.dto.ClienteRequest;
import com.alanfernandes.locadoraveiculos.makeResponse.MakeResponse;
import com.alanfernandes.locadoraveiculos.model.Cliente;
import com.alanfernandes.locadoraveiculos.service.ClienteService;

@RestController
@RequestMapping(value = "api/v1")
public class ClienteController {

	private ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		super();
		this.clienteService = clienteService;
	}

	@PostMapping(value = "/cliente")
	public ResponseEntity<MakeResponse<Cliente>> save(@Valid @RequestBody ClienteRequest clienteRequest) {
		Cliente cliente = this.clienteService.save(clienteRequest.getCliente(clienteRequest));
		MakeResponse<Cliente> makeResponse = new MakeResponse<Cliente>(cliente, "Salvo com sucesso!");
		return new ResponseEntity<MakeResponse<Cliente>>(makeResponse, HttpStatus.CREATED);
	}

	@GetMapping(value = "/cliente")
	public List<Cliente> findAll() {
		return this.clienteService.findAll();
	}

}
