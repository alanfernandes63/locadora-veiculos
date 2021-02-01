package com.alanfernandes.locadoraveiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alanfernandes.locadoraveiculos.model.Loja;

public interface LojaRepository extends JpaRepository<Loja, Long> {

}
