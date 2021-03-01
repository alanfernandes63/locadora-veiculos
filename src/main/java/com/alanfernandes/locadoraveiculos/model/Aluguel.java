package com.alanfernandes.locadoraveiculos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Table(name = "alugueis")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "a data de inicio não pode ser nulo")
    @Column(name = "inicio")
    LocalDateTime inicio;

    @NotNull
    @Column(name = "entrega")
    LocalDateTime entrega;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    @NotNull
    Cliente cliente;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loja_id")
    @NotNull
    @JsonIgnore
    Loja loja;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "veiculo_id")
    Veiculo veiculo;
}
