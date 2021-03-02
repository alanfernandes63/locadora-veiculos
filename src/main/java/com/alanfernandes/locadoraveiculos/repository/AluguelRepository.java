package com.alanfernandes.locadoraveiculos.repository;

import com.alanfernandes.locadoraveiculos.model.Aluguel;
import com.alanfernandes.locadoraveiculos.model.Loja;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AluguelRepository extends JpaRepository<Aluguel, Long> {

    //@Query("From Aluguel a Select a where a.veiculo.disponivel =:disponivel")
    Page<Aluguel> findByLojaAndEntregaBefore(Loja loja, LocalDateTime entrega, Pageable pageable);
}
