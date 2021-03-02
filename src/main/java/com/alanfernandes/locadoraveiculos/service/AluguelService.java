package com.alanfernandes.locadoraveiculos.service;

import com.alanfernandes.locadoraveiculos.dto.AluguelResponse;
import com.alanfernandes.locadoraveiculos.mapper.AluguelMapper;
import com.alanfernandes.locadoraveiculos.model.Aluguel;
import com.alanfernandes.locadoraveiculos.model.Cliente;
import com.alanfernandes.locadoraveiculos.model.Loja;
import com.alanfernandes.locadoraveiculos.model.Veiculo;
import com.alanfernandes.locadoraveiculos.repository.AluguelRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AluguelService {

    private LojaService lojaService;

    private VeiculoService veiculoService;

    private ClienteService clienteService;

    private AluguelRepository aluguelRepository;

    AluguelMapper aluguelMapper;

    public AluguelResponse alugar(Long lojaId, Long veiculoId, Long clienteId, LocalDateTime entrega) throws NotFoundException {

        Cliente cliente = clienteService.findById(clienteId);

        Veiculo veiculo = veiculoService.findById(veiculoId);

        Assert.isTrue(veiculo.getDisponivel(), "O veículo com id " + veiculo.getId() + " não está disponível");

        Loja loja = lojaService.findById(lojaId);

        Aluguel aluguel = Aluguel.builder()
                .cliente(cliente)
                .veiculo(veiculo)
                .loja(loja)
                .inicio(LocalDateTime.now())
                .entrega(entrega)
                .build();

        aluguelRepository.save(aluguel);
        veiculoService.alterarDisponibilidadeVeiculo(veiculo);

        return aluguelMapper.aluguelToAluguelResponse(aluguel);
    }

    public Page<AluguelResponse> listarAtrasados(Long lojaId, Pageable pageable) throws NotFoundException {
        Loja loja = lojaService.findById(lojaId);
        LocalDateTime hoje = LocalDateTime.now();

        Page<AluguelResponse> alugueisAtrasados = aluguelRepository.findByLojaAndEntregaBefore(loja, hoje, pageable)
                .map(aluguelMapper::aluguelToAluguelResponse);
        return alugueisAtrasados;
    }
}
