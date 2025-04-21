package dev.programing.moneycontrol.controller;

import dev.programing.moneycontrol.docs.CategoriaDocumentation;
import dev.programing.moneycontrol.dto.requests.CategoriaRequestDTO;
import dev.programing.moneycontrol.dto.responses.CategoriaResponseDTO;
import dev.programing.moneycontrol.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/categoria")
@RequiredArgsConstructor
public class CategoriaController implements CategoriaDocumentation {

    private final CategoriaService service;

    @Override
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Mono<CategoriaResponseDTO> criarCategoria(@RequestBody CategoriaRequestDTO requestDTO) {
        return this.service.cadastrarCategoria(requestDTO);
    }

    @Override
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Flux<CategoriaResponseDTO> consultarCategorias() {
        return this.service.recuperarCategorias();
    }

    @Override
    @DeleteMapping("/{id}")
    public Mono<Void> removerCategoria(@PathVariable(value = "id") String idCategoria) {
        return this.service.removerCategoria(idCategoria);
    }
}
