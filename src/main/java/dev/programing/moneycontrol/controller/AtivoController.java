package dev.programing.moneycontrol.controller;

import dev.programing.moneycontrol.docs.AtivoControllerDocumentation;
import dev.programing.moneycontrol.dto.requests.AtivoRequestDTO;
import dev.programing.moneycontrol.dto.responses.AtivoResponseDTO;
import dev.programing.moneycontrol.service.AtivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/ativo")
@RequiredArgsConstructor
public class AtivoController implements AtivoControllerDocumentation {

    private final AtivoService ativoService;

    @Override
    public Mono<AtivoResponseDTO> cadastrarAtivo(AtivoRequestDTO requestDTO) {
        return this.ativoService.cadastrarNovoAtivo(requestDTO);
    }
}
