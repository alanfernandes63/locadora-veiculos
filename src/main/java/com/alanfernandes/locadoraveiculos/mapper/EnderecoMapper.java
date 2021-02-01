package com.alanfernandes.locadoraveiculos.mapper;

import org.mapstruct.Mapper;

import com.alanfernandes.locadoraveiculos.dto.EnderecoRequest;
import com.alanfernandes.locadoraveiculos.model.Endereco;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

	Endereco enderecoRequestToEndereco(EnderecoRequest enderecoRequest);
}
