package dev.programing.moneycontrol.service.implementation;


import dev.programing.moneycontrol.converter.AtivoConverter;
import dev.programing.moneycontrol.dto.requests.AtivoRequestDTO;
import dev.programing.moneycontrol.dto.responses.AtivoResponseDTO;
import dev.programing.moneycontrol.exception.CategoriaNotFoundException;
import dev.programing.moneycontrol.repository.AtivoRepository;
import dev.programing.moneycontrol.service.AtivoService;
import dev.programing.moneycontrol.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class AtivoServiceImplementation implements AtivoService {

    private final CategoriaService categoriaService;
    private final AtivoRepository ativoRepository;
    private final AtivoConverter converter;

    @Override
    public Mono<AtivoResponseDTO> cadastrarNovoAtivo(AtivoRequestDTO requestDTO) {
        return this.categoriaService.recuperarCategoriaPorId(requestDTO.idCategoria())
                .flatMap(categoria -> this.ativoRepository.save(this.converter.toAtivoDTO(requestDTO, categoria)))
                .map(this.converter::toAtivoResponseDTO)
                .switchIfEmpty(Mono.error(CategoriaNotFoundException::new));
    }
}