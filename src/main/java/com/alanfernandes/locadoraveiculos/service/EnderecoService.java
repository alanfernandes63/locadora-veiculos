package com.alanfernandes.locadoraveiculos.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alanfernandes.locadoraveiculos.model.Endereco;
import com.alanfernandes.locadoraveiculos.repository.EnderecoRepository;

@Service
public class EnderecoService {

	private EnderecoRepository enderecoRepository;

	public EnderecoService(@Autowired EnderecoRepository enderecoRepository) {
		super();
		this.enderecoRepository = enderecoRepository;
	}

	@Transactional
	public Endereco save(Endereco endereco) {
		return this.enderecoRepository.save(endereco);
	}

}
