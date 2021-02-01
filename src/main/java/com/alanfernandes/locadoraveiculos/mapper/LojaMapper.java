package com.alanfernandes.locadoraveiculos.mapper;

import org.mapstruct.Mapper;

import com.alanfernandes.locadoraveiculos.dto.LojaRequest;
import com.alanfernandes.locadoraveiculos.model.Loja;

@Mapper(componentModel = "spring")
public interface LojaMapper {
	Loja lojaRequestToLoja(LojaRequest lojaRequest);

}
