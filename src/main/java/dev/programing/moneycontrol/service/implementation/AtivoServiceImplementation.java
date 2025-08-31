package dev.programing.moneycontrol.service.implementation;


import dev.programing.moneycontrol.converter.AtivoConverter;
import dev.programing.moneycontrol.dto.requests.AtivoRequestDTO;
import dev.programing.moneycontrol.dto.responses.AtivoResponseDTO;
import dev.programing.moneycontrol.exception.CategoriaNotFoundException;
import dev.programing.moneycontrol.handler.signature.FinanceHandler;
import dev.programing.moneycontrol.model.Ativo;
import dev.programing.moneycontrol.model.Categoria;
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
    private final FinanceHandler handler;

    @Override
    public Mono<AtivoResponseDTO> adicionarAtivo(AtivoRequestDTO requestDTO) {
        return this.categoriaService.recuperarCategoriaPorId(requestDTO.idCategoria())
                .flatMap(categoria -> recuperarDadosAtivo(requestDTO, categoria))
                .flatMap(this.ativoRepository::save)
                .map(this.converter::toAtivoResponseDTO)
                .switchIfEmpty(Mono.error(CategoriaNotFoundException::new));
    }

    private Mono<Ativo> recuperarDadosAtivo(AtivoRequestDTO request, Categoria categoria) {
        return this.handler.recuperarAtivo(request.ticker()).map(atv -> this.converter.toAtivo(request, atv, categoria));
    }
}