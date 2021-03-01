package com.alanfernandes.locadoraveiculos.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AluguelResponse {

    LocalDateTime inicio;

    LocalDateTime entrega;
}
