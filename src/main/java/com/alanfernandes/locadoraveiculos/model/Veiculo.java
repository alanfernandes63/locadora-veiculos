package com.alanfernandes.locadoraveiculos.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "veiculos", uniqueConstraints = @UniqueConstraint(columnNames = "placa"))
@Data
public class Veiculo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(name = "modelo", nullable = false)
    @NotBlank(message = "O modelo do veículo não estar em branco")
    @NotNull
    @NotBlank
    String modelo;

    @Column(name = "ano", nullable = false, length = 4)
    @NotNull
    Long ano;

    @Column(name = "placa", nullable = false)
    @NotNull
    @NotBlank
    String placa;

    @Column(name = "marca", nullable = false)
    @NotNull
    @NotBlank
    String marca;

    @Column(name = "disponivel")
    @NotNull
    Boolean disponivel = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loja_id")
    @NotNull
    Loja loja;

    public Veiculo() {
        super();
    }

}
