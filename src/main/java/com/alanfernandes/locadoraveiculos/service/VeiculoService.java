package com.alanfernandes.locadoraveiculos.service;

import com.alanfernandes.locadoraveiculos.dto.VeiculoRequest;
import com.alanfernandes.locadoraveiculos.dto.VeiculoResponse;
import com.alanfernandes.locadoraveiculos.mapper.VeiculoMapper;
import com.alanfernandes.locadoraveiculos.model.Loja;
import com.alanfernandes.locadoraveiculos.model.Veiculo;
import com.alanfernandes.locadoraveiculos.repository.VeiculoRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeiculoService extends GenericService<Veiculo, VeiculoRepository> {

    private VeiculoRepository veiculoRepository;

    private VeiculoMapper veiculoMapper;

    private LojaService lojaService;

    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository, VeiculoMapper veiculoMapper, LojaService lojaService) {
        super();
        this.veiculoRepository = veiculoRepository;
        this.veiculoMapper = veiculoMapper;
        this.lojaService = lojaService;
    }

    @Transactional
    public VeiculoResponse save(VeiculoRequest veiculoRequest, Long idLoja) throws NotFoundException {

        Loja loja = lojaService.findById(idLoja);

        Veiculo veiculo = veiculoMapper.veiculoRequestToVeiculo(veiculoRequest);

        boolean doesNotExist = !this.veiculoRepository.findByPlaca(veiculo.getPlaca()).isPresent();
        Assert.isTrue(doesNotExist, "Já existe um veiculo cadastrado com esta placa");
        veiculo.setLoja(loja);
        veiculoRepository.save(veiculo);
        return veiculoMapper.veiculoToVeiculoResponse(veiculo);
    }

    public List<Veiculo> findByMarca(String marca) {
        return this.veiculoRepository.findByMarca(marca);
    }

    public List<Veiculo> findByAno(Long ano) {
        return this.veiculoRepository.findByAno(ano);
    }

    public List<Veiculo> findByModelo(String modelo) {
        return this.veiculoRepository.findByModelo(modelo);
    }

    public Veiculo findByPlaca(String placa) throws NotFoundException {
        Veiculo veiculo = this.veiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new NotFoundException("Veículo com placa " + placa + " não encontrado."));
        return veiculo;
    }

    public List<VeiculoResponse> findByLoja(Long idLoja, Pageable pageable) throws NotFoundException {
        Loja loja = lojaService.findById(idLoja);

        List<VeiculoResponse> veiculos = veiculoRepository.findByLoja(loja, pageable)
                .stream()
                .map(veiculo -> veiculoMapper.veiculoToVeiculoResponse(veiculo)).collect(Collectors.toList());
        return veiculos;
    }
}
