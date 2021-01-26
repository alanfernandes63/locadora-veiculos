package com.alanfernandes.locadoraveiculos.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alanfernandes.locadoraveiculos.dto.VeiculoRequest;
import com.alanfernandes.locadoraveiculos.exception.VeiculoNotFoundException;
import com.alanfernandes.locadoraveiculos.model.Veiculo;
import com.alanfernandes.locadoraveiculos.repository.VeiculoRepository;

@Service
public class VeiculoService {

	private VeiculoRepository veiculoRepository;

	public VeiculoService(@Autowired VeiculoRepository veiculoRepository) {
		super();
		this.veiculoRepository = veiculoRepository;
	}

	@Transactional
	public Veiculo save(VeiculoRequest veiculoRequest) {
		Veiculo veiculo = veiculoRequest.getVeiculo(veiculoRequest);
		boolean doesNotExist = !this.veiculoRepository.findByPlaca(veiculo.getPlaca()).isPresent();
		Assert.isTrue(doesNotExist, "Já existe um veiculo cadastrado com esta placa");
		this.veiculoRepository.saveAndFlush(veiculo);
		return veiculo;
	}

	@Transactional
	public void deleteById(Long id) {
		Veiculo veiculo = this.findById(id);
		this.veiculoRepository.delete(veiculo);
	}

	public List<Veiculo> findByMarca(String marca) {
		return this.veiculoRepository.findByMarca(marca);
	}

	public List<Veiculo> findByAno(Long ano) {
		return this.veiculoRepository.findByAno(ano);
	}

	public List<Veiculo> findByModelo(String modelo) {
		return this.veiculoRepository.findByModelo(modelo);
	}

	public Veiculo findByPlaca(String placa) {
		Veiculo veiculo = this.veiculoRepository.findByPlaca(placa)
				.orElseThrow(() -> new VeiculoNotFoundException("Veículo com placa " + placa + " não encontrado."));
		return veiculo;
	}

	public List<Veiculo> findAll() {
		return this.veiculoRepository.findAll();
	}

	public Veiculo findById(Long id) {
		Veiculo veiculo = this.veiculoRepository.findById(id)
				.orElseThrow(() -> new VeiculoNotFoundException("Veículo com " + id + " não encontrado"));
		return veiculo;
	}

}
