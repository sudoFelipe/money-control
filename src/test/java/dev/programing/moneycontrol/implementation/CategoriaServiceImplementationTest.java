package dev.programing.moneycontrol.implementation;

import dev.programing.moneycontrol.converter.CategoriaConverter;
import dev.programing.moneycontrol.dto.requests.CategoriaRequestDTO;
import dev.programing.moneycontrol.dto.responses.CategoriaResponseDTO;
import dev.programing.moneycontrol.model.Categoria;
import dev.programing.moneycontrol.repository.CategoriaRepository;
import dev.programing.moneycontrol.utils.MockUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoriaServiceImplementationTest extends MockUnitTest {

    @InjectMocks
    private CategoriaServiceImplementation service;

    @Mock
    private CategoriaRepository repository;

    @Mock
    private CategoriaConverter converter;

    @Test
    void deveCadastrarCategoriaComSucesso() {
        final var categoria = Categoria.builder().id("1").descricao("Teste").sigla("TT").build();
        final var requestDTO = CategoriaRequestDTO.builder().descricao("Teste").sigla("TT").build();
        final var responseDTO = CategoriaResponseDTO.builder().descricao("Teste").sigla("TT").build();
        when(this.converter.toSaveCategoria(any())).thenReturn(categoria);
        when(this.converter.toResponseDTO(any())).thenReturn(responseDTO);
        when(this.repository.save(any())).thenReturn(Mono.just(categoria));

        StepVerifier.create(this.service.cadastrarCategoria(requestDTO))
                .expectSubscription()
                .consumeNextWith(result -> {
                    assertThat(result.getId()).isNotNull();
                    assertThat(result.getDescricao()).isNotNull();
                    assertThat(result.getSigla()).isNotNull();
                });

        verify(this.repository, times(1)).save(any());
    }

    @Test
    void deveRecuperarCategoriasComSucesso() {
        final var categoria = Categoria.builder().id("1").descricao("Teste").sigla("TT").build();
        final var responseDTO = CategoriaResponseDTO.builder().descricao("Teste").sigla("TT").build();
        when(this.repository.findAll()).thenReturn(Flux.just(categoria));
        when(this.converter.toResponseDTO(any())).thenReturn(responseDTO);

        StepVerifier.create(this.service.recuperarCategorias())
                .expectSubscription()
                .consumeNextWith(result -> {
                    assertThat(result.getId()).isNotNull();
                    assertThat(result.getDescricao()).isNotNull();
                    assertThat(result.getSigla()).isNotNull();
                })
                .expectComplete();
    }

    @Test
    void deveRemoverCategoriaComSucesso() {
        final var categoria = Categoria.builder().id("1").descricao("Teste").sigla("TT").build();
        when(this.repository.findById(anyString())).thenReturn(Mono.just(categoria));
        StepVerifier.create(this.service.removerCategoria("1")).expectComplete();
    }
}