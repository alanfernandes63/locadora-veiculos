package com.alanfernandes.locadoraveiculos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alanfernandes.locadoraveiculos.dto.VeiculoRequest;
import com.alanfernandes.locadoraveiculos.exception.VeiculoNotFoundException;
import com.alanfernandes.locadoraveiculos.makeResponse.MakeResponse;
import com.alanfernandes.locadoraveiculos.model.Veiculo;
import com.alanfernandes.locadoraveiculos.service.VeiculoService;

@RestController
@RequestMapping(value = "api/v1")
public class VeiculoController {

	private VeiculoService veiculoService;

	public VeiculoController(@Autowired VeiculoService veiculoService) {
		super();
		this.veiculoService = veiculoService;
	}

	@PostMapping(value = "/veiculos")
	public ResponseEntity<MakeResponse<Veiculo>> save(@Valid @RequestBody VeiculoRequest veiculoRequest) {
		try {
			MakeResponse<Veiculo> makeResponse = new MakeResponse<Veiculo>(this.veiculoService.save(veiculoRequest),
					"Veículo cadastrado com sucesso!");
			return new ResponseEntity<MakeResponse<Veiculo>>(makeResponse, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			MakeResponse<Veiculo> makeResponse = new MakeResponse<Veiculo>(null, e.getMessage());
			return new ResponseEntity<MakeResponse<Veiculo>>(makeResponse, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/veiculos")
	public ResponseEntity<List<Veiculo>> findAll() {
		return new ResponseEntity<List<Veiculo>>(this.veiculoService.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/veiculos/{id}")
	public ResponseEntity<Veiculo> findById(@PathVariable Long id) {
		try {
			return new ResponseEntity<Veiculo>(this.veiculoService.findById(id), HttpStatus.OK);
		} catch (VeiculoNotFoundException e) {
			return new ResponseEntity<Veiculo>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/veiculos/findByMarca")
	public ResponseEntity<List<Veiculo>> findByMarca(@RequestParam(name = "marca", required = true) String marca) {
		return new ResponseEntity<List<Veiculo>>(this.veiculoService.findByMarca(marca), HttpStatus.OK);
	}

	@GetMapping(value = "/veiculos/findByAno")
	public ResponseEntity<List<Veiculo>> findByAno(@RequestParam(name = "ano", required = true) Long ano) {
		return new ResponseEntity<List<Veiculo>>(this.veiculoService.findByAno(ano), HttpStatus.OK);
	}

	@GetMapping(value = "/veiculos/findByModelo")
	public ResponseEntity<List<Veiculo>> findByModelo(@RequestParam(name = "modelo", required = true) String modelo) {
		return new ResponseEntity<List<Veiculo>>(this.veiculoService.findByModelo(modelo), HttpStatus.OK);
	}

	public ResponseEntity<Veiculo> findByPlaca(@RequestParam(name = "placa", required = true) String placa) {
		try {
			return new ResponseEntity<Veiculo>(this.veiculoService.findByPlaca(placa), HttpStatus.OK);
		} catch (VeiculoNotFoundException e) {
			return new ResponseEntity<Veiculo>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "/veiculos/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		try {
			this.veiculoService.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		} catch (VeiculoNotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
