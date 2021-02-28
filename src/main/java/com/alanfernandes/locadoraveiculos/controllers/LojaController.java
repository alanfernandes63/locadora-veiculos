package com.alanfernandes.locadoraveiculos.controllers;

import com.alanfernandes.locadoraveiculos.dto.LojaRequest;
import com.alanfernandes.locadoraveiculos.dto.LojaResponse;
import com.alanfernandes.locadoraveiculos.makeResponse.MakeResponse;
import com.alanfernandes.locadoraveiculos.mapper.EnderecoMapper;
import com.alanfernandes.locadoraveiculos.mapper.LojaMapper;
import com.alanfernandes.locadoraveiculos.model.Endereco;
import com.alanfernandes.locadoraveiculos.model.Loja;
import com.alanfernandes.locadoraveiculos.service.EnderecoService;
import com.alanfernandes.locadoraveiculos.service.LojaService;
import com.alanfernandes.locadoraveiculos.service.VeiculoService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/v1/loja")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LojaController {

    private LojaService lojaService;

    private LojaMapper lojaMapper;

    private EnderecoMapper enderecoMapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<MakeResponse<LojaResponse>> findById(@PathVariable Long id) {
        LojaResponse lojaResponse = null;
        MakeResponse<LojaResponse> makeResponse;
        try {
            lojaResponse = lojaMapper.lojaToLojaResponse(this.lojaService.findById(id));
            makeResponse = new MakeResponse<LojaResponse>(lojaResponse, "success");
            return new ResponseEntity<MakeResponse<LojaResponse>>(makeResponse, HttpStatus.OK);
        } catch (NotFoundException e) {
            makeResponse = new MakeResponse<LojaResponse>(lojaResponse, e.getMessage());
            return new ResponseEntity<MakeResponse<LojaResponse>>(makeResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<MakeResponse<LojaResponse>> save(@Valid @RequestBody LojaRequest lojaRequest) {

        Loja loja = lojaMapper.lojaRequestToLoja(lojaRequest);
        Endereco endereco = enderecoMapper
                .enderecoRequestToEndereco(lojaRequest.getEnderecoRequest());
        loja.setEndereco(endereco);
        LojaResponse newLoja = lojaMapper.lojaToLojaResponse(lojaService.save(loja));

        MakeResponse<LojaResponse> makeResponse = new MakeResponse<LojaResponse>(newLoja, "succsses");
        return new ResponseEntity<MakeResponse<LojaResponse>>(makeResponse, HttpStatus.OK);

    }

}
