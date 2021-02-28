package com.alanfernandes.locadoraveiculos.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

@Entity
@Table(name = "veiculos", uniqueConstraints = @UniqueConstraint(columnNames = "placa"))
@Data
public class Veiculo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(name = "modelo", nullable = false)
    @NotBlank(message = "O modelo do veículo não estar em branco")
    String modelo;

    @Column(name = "ano", nullable = false, length = 4)
    Long ano;

    @Column(name = "placa", nullable = false)
    String placa;

    @Column(name = "marca", nullable = false)
    String marca;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loja_id")
    Loja loja;

    public Veiculo() {
        super();
    }

}
