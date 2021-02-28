package com.alanfernandes.locadoraveiculos.controllers;

import com.alanfernandes.locadoraveiculos.dto.LojaRequest;
import com.alanfernandes.locadoraveiculos.dto.VeiculoRequest;
import com.alanfernandes.locadoraveiculos.makeResponse.MakeResponse;
import com.alanfernandes.locadoraveiculos.mapper.EnderecoMapper;
import com.alanfernandes.locadoraveiculos.mapper.LojaMapper;
import com.alanfernandes.locadoraveiculos.model.Endereco;
import com.alanfernandes.locadoraveiculos.model.Loja;
import com.alanfernandes.locadoraveiculos.model.Veiculo;
import com.alanfernandes.locadoraveiculos.service.EnderecoService;
import com.alanfernandes.locadoraveiculos.service.LojaService;
import com.alanfernandes.locadoraveiculos.service.VeiculoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    private VeiculoService veiculoService;

    @Autowired
    public LojaController(LojaService lojaService, EnderecoService enderecoService, LojaMapper lojaMapper,
                          EnderecoMapper enderecoMapper, VeiculoService veiculoService) {
        super();
        this.lojaService = lojaService;
        this.lojaMapper = lojaMapper;
        this.enderecoMapper = enderecoMapper;
        this.veiculoService = veiculoService;
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

    @PostMapping(value = "/{idLoja}/veiculo")
    public ResponseEntity<MakeResponse<Veiculo>> save(@PathVariable(required = true) Long idLoja,
                                                      @Valid @RequestBody VeiculoRequest veiculoRequest) {
        try {
            Veiculo veiculo = this.veiculoService.save(veiculoRequest, idLoja);
            MakeResponse<Veiculo> makeResponse = new MakeResponse<Veiculo>(veiculo, "Veículo cadastrado com sucesso!");
            return new ResponseEntity<MakeResponse<Veiculo>>(makeResponse, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            MakeResponse<Veiculo> makeResponse = new MakeResponse<Veiculo>(null, e.getMessage());
            return new ResponseEntity<MakeResponse<Veiculo>>(makeResponse, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<MakeResponse<Veiculo>>(new MakeResponse<Veiculo>(null, "Loja não encontrada"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{idLoja}/veiculo")
    public ResponseEntity<MakeResponse<Page<Veiculo>>> listarVeiculosPorLoja(@PathVariable Long idLoja, Pageable pageable) {
        MakeResponse<Page<Veiculo>> makeResponse;
        try {
            Page<Veiculo> veiculos = veiculoService.findByLoja(idLoja, pageable);
            makeResponse = new MakeResponse<>(veiculos, "listado com sucesso!");
            return new ResponseEntity<MakeResponse<Page<Veiculo>>>(makeResponse, HttpStatus.OK);
        } catch (NotFoundException e) {
            makeResponse = new MakeResponse<Page<Veiculo>>(null, e.getMessage());
            return new ResponseEntity<MakeResponse<Page<Veiculo>>>(makeResponse, HttpStatus.BAD_REQUEST);
        }
    }

}
