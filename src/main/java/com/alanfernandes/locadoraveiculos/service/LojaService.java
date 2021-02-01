package com.alanfernandes.locadoraveiculos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alanfernandes.locadoraveiculos.model.Loja;
import com.alanfernandes.locadoraveiculos.repository.LojaRepository;

@Service
public class LojaService extends GenericService<Loja, LojaRepository> {

	@Autowired
	public LojaService(LojaRepository lojaRepository) {
		super();
	}

}
