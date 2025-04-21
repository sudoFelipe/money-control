package dev.programing.moneycontrol.implementation;

import dev.programing.moneycontrol.converter.CategoriaConverter;
import dev.programing.moneycontrol.dto.requests.CategoriaRequestDTO;
import dev.programing.moneycontrol.dto.responses.CategoriaResponseDTO;
import dev.programing.moneycontrol.repository.CategoriaRepository;
import dev.programing.moneycontrol.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoriaServiceImplementation implements CategoriaService {

    private final CategoriaRepository repository;
    private final CategoriaConverter converter;

    @Override
    public Mono<CategoriaResponseDTO> cadastrarCategoria(CategoriaRequestDTO requestDTO) {
        return this.repository.save(this.converter.toSaveCategoria(requestDTO))
                .map(this.converter::toResponseDTO);
    }

    @Override
    public Flux<CategoriaResponseDTO> recuperarCategorias() {
        return this.repository.findAll().map(this.converter::toResponseDTO);
    }

    @Override
    public Mono<Void> removerCategoria(String idCategoria) {
        return this.repository.findById(idCategoria).flatMap(this.repository::delete);
    }
}
