package com.alanfernandes.locadoraveiculos.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "lojas")
public class Loja {

    public Loja() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "adress_id", referencedColumnName = "id")
    @JsonManagedReference
    private Endereco endereco;
/*
    @OneToMany(targetEntity = Veiculo.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "loja_id")
    private Set<Veiculo> veiculos = new HashSet<>();
*/
}
