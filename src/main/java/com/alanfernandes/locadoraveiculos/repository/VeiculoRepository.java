package com.alanfernandes.locadoraveiculos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alanfernandes.locadoraveiculos.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

	public List<Veiculo> findByMarca(String marca);

	public List<Veiculo> findByAno(Long ano);

	public List<Veiculo> findByModelo(String modelo);

	public Optional<Veiculo> findByPlaca(String placa);
	
}
