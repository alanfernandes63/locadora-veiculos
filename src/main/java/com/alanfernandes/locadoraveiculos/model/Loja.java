package com.alanfernandes.locadoraveiculos.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "lojas")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "adress_id", referencedColumnName = "id")
    private Endereco endereco;
}
