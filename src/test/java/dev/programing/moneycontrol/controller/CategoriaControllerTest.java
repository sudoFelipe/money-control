package dev.programing.moneycontrol.controller;

import dev.programing.moneycontrol.dto.responses.CategoriaResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureWebTestClient
class CategoriaControllerTest {

    private static String URL_DEFAULT = "/api/v1/categoria";

    @Autowired
    private WebTestClient clientTest;

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
        this.clientTest.delete()
                .uri(URL_DEFAULT.concat("/{id}"), 1)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }
}