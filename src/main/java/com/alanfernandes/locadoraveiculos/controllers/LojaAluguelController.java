package com.alanfernandes.locadoraveiculos.controllers;

import com.alanfernandes.locadoraveiculos.dto.AluguelResponse;
import com.alanfernandes.locadoraveiculos.service.AluguelService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/loja")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LojaAluguelController {

    private AluguelService aluguelService;

    @PostMapping("/{lojaId}/aluguel")
    public AluguelResponse alugar(@PathVariable Long lojaId, @RequestParam(name = "cliente", required = true)
            Long clienteId, @RequestParam(name = "veiculo", required = true) Long veiculoId,
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                          @RequestParam(name = "entrega", required = true) LocalDateTime entrega) throws NotFoundException {
        return aluguelService.alugar(lojaId, veiculoId, clienteId, entrega);
    }

    @GetMapping("/{lojaId}/alugueisVencidos")
    public Page<AluguelResponse> listarAlugueisAtrasados(@PathVariable Long lojaId, Pageable pageable) throws NotFoundException {
        return aluguelService.listarAtrasados(lojaId, pageable);
    }
}
