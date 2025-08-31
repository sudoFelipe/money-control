package dev.programing.moneycontrol.service;

import dev.programing.moneycontrol.dto.requests.AtivoRequestDTO;
import dev.programing.moneycontrol.dto.responses.AtivoResponseDTO;
import reactor.core.publisher.Mono;

public interface AtivoService {

    Mono<AtivoResponseDTO> adicionarAtivo(AtivoRequestDTO requestDTO);
}
