package com.alanfernandes.locadoraveiculos.mapper;

import com.alanfernandes.locadoraveiculos.dto.VeiculoResponse;
import org.mapstruct.Mapper;

import com.alanfernandes.locadoraveiculos.dto.VeiculoRequest;
import com.alanfernandes.locadoraveiculos.model.Veiculo;

@Mapper(componentModel = "spring")
public interface VeiculoMapper {
    Veiculo veiculoRequestToVeiculo(VeiculoRequest veiculoRequest);

    VeiculoResponse veiculoToVeiculoResponse(Veiculo veiculo);
}
