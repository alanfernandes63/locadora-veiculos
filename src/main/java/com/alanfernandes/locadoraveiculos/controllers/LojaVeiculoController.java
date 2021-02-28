package com.alanfernandes.locadoraveiculos.controllers;

import com.alanfernandes.locadoraveiculos.dto.VeiculoRequest;
import com.alanfernandes.locadoraveiculos.dto.VeiculoResponse;
import com.alanfernandes.locadoraveiculos.makeResponse.MakeResponse;
import com.alanfernandes.locadoraveiculos.service.VeiculoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/loja")
public class LojaVeiculoController {

    private VeiculoService veiculoService;

    @Autowired
    public LojaVeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping(value = "/{idLoja}/veiculo")
    public ResponseEntity<MakeResponse<VeiculoResponse>> save(@PathVariable(required = true) Long idLoja,
                                                              @Valid @RequestBody VeiculoRequest veiculoRequest) {
        MakeResponse<VeiculoResponse> makeResponse;
        try {
            VeiculoResponse veiculo = this.veiculoService.save(veiculoRequest, idLoja);
            makeResponse = new MakeResponse<VeiculoResponse>(veiculo, "Veículo cadastrado com sucesso!");
            return new ResponseEntity<MakeResponse<VeiculoResponse>>(makeResponse, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            makeResponse = new MakeResponse<VeiculoResponse>(null, e.getMessage());
            return new ResponseEntity<MakeResponse<VeiculoResponse>>(makeResponse, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            makeResponse = new MakeResponse<VeiculoResponse>(null, "Loja não encontrada");
            return new ResponseEntity<MakeResponse<VeiculoResponse>>(makeResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{idLoja}/veiculo")
    public ResponseEntity<MakeResponse<List<VeiculoResponse>>> listarVeiculosPorLoja(@PathVariable Long idLoja, Pageable pageable) {
        MakeResponse<List<VeiculoResponse>> makeResponse;
        try {
            List<VeiculoResponse> veiculos = veiculoService.findByLoja(idLoja, pageable);
            makeResponse = new MakeResponse<List<VeiculoResponse>>(veiculos, "listado com sucesso!");
            return new ResponseEntity<MakeResponse<List<VeiculoResponse>>>(makeResponse, HttpStatus.OK);
        } catch (NotFoundException e) {
            makeResponse = new MakeResponse<List<VeiculoResponse>>(null, e.getMessage());
            return new ResponseEntity<MakeResponse<List<VeiculoResponse>>>(makeResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
