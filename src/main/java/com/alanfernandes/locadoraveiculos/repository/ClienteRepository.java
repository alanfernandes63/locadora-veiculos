package com.alanfernandes.locadoraveiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alanfernandes.locadoraveiculos.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
