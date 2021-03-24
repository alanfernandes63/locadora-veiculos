package com.alanfernandes.locadoraveiculos;

import com.alanfernandes.locadoraveiculos.dto.VeiculoRequest;
import com.alanfernandes.locadoraveiculos.dto.VeiculoResponse;
import com.alanfernandes.locadoraveiculos.enums.UF;
import com.alanfernandes.locadoraveiculos.model.Endereco;
import com.alanfernandes.locadoraveiculos.model.Loja;
import com.alanfernandes.locadoraveiculos.model.Veiculo;
import com.alanfernandes.locadoraveiculos.service.LojaService;
import com.alanfernandes.locadoraveiculos.service.VeiculoService;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
@SpringBootTest
public class VeiculoServiceTest {

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private LojaService lojaService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    // test success
    @Test
    public void saveShouldPersiste() {

        Endereco endereco = Endereco.builder()
                .bairro("bairro test")
                .cep("00000000")
                .cidade("cidade test")
                .uf(UF.RN)
                .numero(60L)
                .logradouro("Rua test")
                .build();

        Loja loja = Loja.builder()
                .nome("loja test")
                .endereco(endereco)
                .build();

        Long lojaId = lojaService.save(loja).getId();

        VeiculoRequest veiculoRequest = VeiculoRequest.builder()
                .ano(2012L)
                .marca("chevrolet")
                .modelo("celta")
                .placa("NNN7894")
                .build();

        /*try {
           VeiculoResponse veiculoResponse = veiculoService.save(veiculoRequest, lojaId);
            Assert.assertNotNull(veiculoResponse.getId());
        } catch (NotFoundException e) {
            e.printStackTrace();
        }*/

    }
}
