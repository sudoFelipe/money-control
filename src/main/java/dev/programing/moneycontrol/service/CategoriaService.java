package dev.programing.moneycontrol.service;

import dev.programing.moneycontrol.dto.requests.CategoriaRequestDTO;
import dev.programing.moneycontrol.dto.responses.CategoriaResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoriaService {

    Mono<CategoriaResponseDTO> cadastrarCategoria(CategoriaRequestDTO requestDTO);

    Flux<CategoriaResponseDTO> recuperarCategorias();

    Mono<Void> removerCategoria(String idCategoria);
}
