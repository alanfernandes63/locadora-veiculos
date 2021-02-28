package com.alanfernandes.locadoraveiculos.controllers;

import com.alanfernandes.locadoraveiculos.dto.LojaRequest;
import com.alanfernandes.locadoraveiculos.makeResponse.MakeResponse;
import com.alanfernandes.locadoraveiculos.mapper.EnderecoMapper;
import com.alanfernandes.locadoraveiculos.mapper.LojaMapper;
import com.alanfernandes.locadoraveiculos.model.Endereco;
import com.alanfernandes.locadoraveiculos.model.Loja;
import com.alanfernandes.locadoraveiculos.service.EnderecoService;
import com.alanfernandes.locadoraveiculos.service.LojaService;
import com.alanfernandes.locadoraveiculos.service.VeiculoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/v1/loja")
public class LojaController {

    private LojaService lojaService;

    private LojaMapper lojaMapper;

    private EnderecoMapper enderecoMapper;

    @Autowired
    public LojaController(LojaService lojaService, EnderecoService enderecoService, LojaMapper lojaMapper,
                          EnderecoMapper enderecoMapper, VeiculoService veiculoService) {
        super();
        this.lojaService = lojaService;
        this.lojaMapper = lojaMapper;
        this.enderecoMapper = enderecoMapper;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MakeResponse<Loja>> findById(@PathVariable Long id) {
        Loja loja = null;
        try {
            loja = this.lojaService.findById(id);
            return new ResponseEntity<MakeResponse<Loja>>(new MakeResponse<Loja>(loja, "success"), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<MakeResponse<Loja>>(new MakeResponse<Loja>(loja, e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<MakeResponse<Loja>> save(@Valid @RequestBody LojaRequest lojaRequest) {

        Loja loja = lojaMapper.lojaRequestToLoja(lojaRequest);
        Endereco endereco = enderecoMapper
                .enderecoRequestToEndereco(lojaRequest.getEnderecoRequest());
        loja.setEndereco(endereco);
        Loja newLoja = lojaService.save(loja);

        MakeResponse<Loja> makeResponse = new MakeResponse<Loja>(newLoja, "succsses");
        return new ResponseEntity<MakeResponse<Loja>>(makeResponse, HttpStatus.OK);

    }

}
