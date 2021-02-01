package com.alanfernandes.locadoraveiculos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alanfernandes.locadoraveiculos.dto.LojaRequest;
import com.alanfernandes.locadoraveiculos.dto.VeiculoRequest;
import com.alanfernandes.locadoraveiculos.makeResponse.MakeResponse;
import com.alanfernandes.locadoraveiculos.mapper.EnderecoMapper;
import com.alanfernandes.locadoraveiculos.mapper.LojaMapper;
import com.alanfernandes.locadoraveiculos.model.Endereco;
import com.alanfernandes.locadoraveiculos.model.Loja;
import com.alanfernandes.locadoraveiculos.model.Veiculo;
import com.alanfernandes.locadoraveiculos.service.EnderecoService;
import com.alanfernandes.locadoraveiculos.service.LojaService;
import com.alanfernandes.locadoraveiculos.service.VeiculoService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "api/v1/lojas")
public class LojaController {

	private LojaService lojaService;

	private EnderecoService enderecoService;

	private LojaMapper lojaMapper;

	private EnderecoMapper enderecoMapper;

	private VeiculoService veiculoService;

	@Autowired
	public LojaController(LojaService lojaService, EnderecoService enderecoService, LojaMapper lojaMapper,
			EnderecoMapper enderecoMapper, VeiculoService veiculoService) {
		super();
		this.lojaService = lojaService;
		this.enderecoService = enderecoService;
		this.lojaMapper = lojaMapper;
		this.enderecoMapper = enderecoMapper;
		this.veiculoService = veiculoService;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<MakeResponse<Loja>> findById(@PathVariable Long id) {
		Loja loja = null;
		try {
			loja = this.lojaService.findById(id);
			return new ResponseEntity<MakeResponse<Loja>>(new MakeResponse<Loja>(loja, "success"), HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<MakeResponse<Loja>>(new MakeResponse<Loja>(loja, e.getMessage()),
					HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping()
	public ResponseEntity<MakeResponse<Loja>> save(@Valid @RequestBody LojaRequest lojaRequest) {

		Endereco endereco = enderecoMapper.enderecoRequestToEndereco(lojaRequest.getEndereco());

		enderecoService.save(endereco);

		Loja loja = lojaMapper.lojaRequestToLoja(lojaRequest);
		loja.setEndereco(endereco);

		Loja newLoja = lojaService.save(loja);

		MakeResponse<Loja> makeResponse = new MakeResponse<Loja>(newLoja, "succsses");
		return new ResponseEntity<MakeResponse<Loja>>(makeResponse, HttpStatus.OK);

	}

	@PostMapping(value = "/{idLoja}/veiculos")
	public ResponseEntity<MakeResponse<Veiculo>> save(@PathVariable(required = true) Long idLoja,
			@Valid @RequestBody VeiculoRequest veiculoRequest) {
		try {
			Veiculo veiculo =  this.veiculoService.save(veiculoRequest, idLoja);
			MakeResponse<Veiculo> makeResponse = new MakeResponse<Veiculo>(veiculo,"Veículo cadastrado com sucesso!");
			return new ResponseEntity<MakeResponse<Veiculo>>(makeResponse, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			MakeResponse<Veiculo> makeResponse = new MakeResponse<Veiculo>(null, e.getMessage());
			return new ResponseEntity<MakeResponse<Veiculo>>(makeResponse, HttpStatus.BAD_REQUEST);
		}catch (NotFoundException e) {
			return new ResponseEntity<MakeResponse<Veiculo>>(new MakeResponse<Veiculo>(null, "Loja não encontrada"), HttpStatus.BAD_REQUEST);
		}
	}

}
