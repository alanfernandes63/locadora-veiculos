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
import com.alanfernandes.locadoraveiculos.makeResponse.MakeResponse;
import com.alanfernandes.locadoraveiculos.model.Veiculo;
import com.alanfernandes.locadoraveiculos.service.VeiculoService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "api/v1")
public class VeiculoController {

	private VeiculoService veiculoService;

	public VeiculoController(@Autowired VeiculoService veiculoService) {
		super();
		this.veiculoService = veiculoService;
	}

	@GetMapping(value = "/veiculo")
	public ResponseEntity<List<Veiculo>> findAll() {
		return new ResponseEntity<List<Veiculo>>(this.veiculoService.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/veiculo/{id}")
	public ResponseEntity<MakeResponse<Veiculo>> findById(@PathVariable Long id) {
		Veiculo veiculo = null;
		try {
			veiculo = this.veiculoService.findById(id);
			return new ResponseEntity<MakeResponse<Veiculo>>(new MakeResponse<Veiculo>(veiculo, "success"),
					HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<MakeResponse<Veiculo>>(new MakeResponse<Veiculo>(veiculo, e.getMessage()),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/veiculo/findByMarca")
	public ResponseEntity<List<Veiculo>> findByMarca(@RequestParam(name = "marca", required = true) String marca) {
		return new ResponseEntity<List<Veiculo>>(this.veiculoService.findByMarca(marca), HttpStatus.OK);
	}

	@GetMapping(value = "/veiculo/findByAno")
	public ResponseEntity<List<Veiculo>> findByAno(@RequestParam(name = "ano", required = true) Long ano) {
		return new ResponseEntity<List<Veiculo>>(this.veiculoService.findByAno(ano), HttpStatus.OK);
	}

	@GetMapping(value = "/veiculo/findByModelo")
	public ResponseEntity<List<Veiculo>> findByModelo(@RequestParam(name = "modelo", required = true) String modelo) {
		return new ResponseEntity<List<Veiculo>>(this.veiculoService.findByModelo(modelo), HttpStatus.OK);
	}

	public ResponseEntity<MakeResponse<Veiculo>> findByPlaca(
			@RequestParam(name = "placa", required = true) String placa) {
		Veiculo veiculo = null;
		try {
			veiculo = this.veiculoService.findByPlaca(placa);
			return new ResponseEntity<MakeResponse<Veiculo>>(
					new MakeResponse<Veiculo>(veiculo, "veículo consultado com sucesso!"), HttpStatus.ACCEPTED);
		} catch (NotFoundException e) {
			return new ResponseEntity<MakeResponse<Veiculo>>(new MakeResponse<Veiculo>(veiculo, e.getMessage()),
					HttpStatus.ACCEPTED);
		}

	}

	@DeleteMapping(value = "/veiculo/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		try {
			this.veiculoService.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		} catch (NotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
