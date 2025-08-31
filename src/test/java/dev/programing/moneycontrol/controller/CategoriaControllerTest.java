package dev.programing.moneycontrol.controller;

import dev.programing.moneycontrol.dto.responses.CategoriaResponseDTO;
import dev.programing.moneycontrol.service.CategoriaService;
import dev.programing.moneycontrol.service.implementation.CategoriaServiceImplementation;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles(profiles = "test")
class CategoriaControllerTest {

    private static final String URL_DEFAULT = "/api/v1/categoria";

    @Autowired
    WebTestClient clientTest;

    @MockitoBean
    CategoriaService service;

    @Test
    void deveCriarCategoriaComSucesso() {
        final var requestDTO = CategoriaResponseDTO.builder().descricao("test").sigla("test").build();
        this.clientTest.post()
                .uri(URL_DEFAULT)
                .contentType(APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestDTO))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(CategoriaResponseDTO.class);
    }

    @Test
    void consultarCategorias() {
        this.clientTest.get()
                .uri(URL_DEFAULT)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void removerCategoria() {
        when(this.service.removerCategoria(anyString())).thenReturn(Mono.empty());
        this.clientTest.delete()
                .uri(URL_DEFAULT.concat("/{idCategoria}"), "IDteste01")
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }
}