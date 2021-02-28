package com.alanfernandes.locadoraveiculos.dto;

import com.alanfernandes.locadoraveiculos.model.Loja;
import lombok.Data;

@Data
public class VeiculoResponse {

    Long id;

    String modelo;

    Long ano;

    String placa;

    String marca;
}
