package com.alanfernandes.locadoraveiculos.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.alanfernandes.locadoraveiculos.enums.UF;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity(name = "endereco")
@Data
public class Endereco implements Serializable {

    public Endereco() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cep", nullable = false)
    @NotBlank
    private String cep;

    @Column(name = "logradouro", nullable = false)
    @NotBlank
    private String logradouro;

    @Column(name = "bairro", nullable = false)
    @NotBlank
    private String bairro;

    @Column(name = "cidade", nullable = false)
    @NotBlank
    private String cidade;

    @Column(name = "uf", nullable = false)
    @Enumerated(EnumType.STRING)
    private UF uf;

    @Column(name = "numero", nullable = false)
    private Long numero;
}
