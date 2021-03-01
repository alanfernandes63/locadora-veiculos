package com.alanfernandes.locadoraveiculos.mapper;

import com.alanfernandes.locadoraveiculos.dto.AluguelResponse;
import com.alanfernandes.locadoraveiculos.model.Aluguel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AluguelMapper {

    AluguelResponse aluguelToAluguelResponse(Aluguel aluguel);
}
