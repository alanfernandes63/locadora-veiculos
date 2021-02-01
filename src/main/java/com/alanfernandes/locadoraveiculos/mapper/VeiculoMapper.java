package com.alanfernandes.locadoraveiculos.mapper;

import org.mapstruct.Mapper;

import com.alanfernandes.locadoraveiculos.dto.VeiculoRequest;
import com.alanfernandes.locadoraveiculos.model.Veiculo;

@Mapper(componentModel = "spring")
public interface VeiculoMapper {
	Veiculo veiculoRequestToVeiculo(VeiculoRequest veiculoRequest);
}
