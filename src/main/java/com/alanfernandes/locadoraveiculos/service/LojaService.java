package com.alanfernandes.locadoraveiculos.service;

import com.alanfernandes.locadoraveiculos.model.Loja;
import com.alanfernandes.locadoraveiculos.model.Veiculo;
import com.alanfernandes.locadoraveiculos.repository.LojaRepository;
import com.alanfernandes.locadoraveiculos.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LojaService extends GenericService<Loja, LojaRepository> {

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    public LojaService(LojaRepository lojaRepository) {
        super();
    }

    public Page<Loja> list(Pageable pageable) {
        return lojaRepository.findAll(pageable);
    }
}
