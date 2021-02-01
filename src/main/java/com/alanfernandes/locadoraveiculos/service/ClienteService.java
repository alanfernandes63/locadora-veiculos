package com.alanfernandes.locadoraveiculos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alanfernandes.locadoraveiculos.model.Cliente;
import com.alanfernandes.locadoraveiculos.repository.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	public ClienteService(@Autowired ClienteRepository clienteRepository) {
		super();
		this.clienteRepository = clienteRepository;
	}

	public Cliente save(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	public List<Cliente> findAll() {
		return this.clienteRepository.findAll();
	}
}
