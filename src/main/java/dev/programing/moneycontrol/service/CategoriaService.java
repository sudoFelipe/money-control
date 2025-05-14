package dev.programing.moneycontrol.service;

import dev.programing.moneycontrol.dto.requests.CategoriaRequestDTO;
import dev.programing.moneycontrol.dto.responses.CategoriaResponseDTO;
import dev.programing.moneycontrol.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoriaService {

    Mono<CategoriaResponseDTO> cadastrarCategoria(CategoriaRequestDTO requestDTO);

    Flux<CategoriaResponseDTO> recuperarCategorias();

    Mono<Void> removerCategoria(String idCategoria);

    Mono<Categoria> recuperarCategoriaPorId(String idCategoria);
}
