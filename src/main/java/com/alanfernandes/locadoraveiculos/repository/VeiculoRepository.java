package com.alanfernandes.locadoraveiculos.repository;

import com.alanfernandes.locadoraveiculos.model.Loja;
import com.alanfernandes.locadoraveiculos.model.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    public List<Veiculo> findByMarca(String marca);

    public List<Veiculo> findByAno(Long ano);

    public List<Veiculo> findByModelo(String modelo);

    public Optional<Veiculo> findByPlaca(String placa);

    //@Query(value = "select * from veiculos v where v.loja_id =:idLoja", nativeQuery = true)
    //Page<Veiculo> listarVeiculos(@Param("idLoja") Long idLoja, Pageable pageable);
    Page<Veiculo> findByLoja(Loja loja, Pageable pageable);

}
