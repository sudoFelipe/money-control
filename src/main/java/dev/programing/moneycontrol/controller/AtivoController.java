package dev.programing.moneycontrol.controller;

import dev.programing.moneycontrol.docs.AtivoControllerDocumentation;
import dev.programing.moneycontrol.dto.requests.AtivoRequestDTO;
import dev.programing.moneycontrol.dto.responses.AtivoResponseDTO;
import dev.programing.moneycontrol.service.AtivoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/ativo")
@RequiredArgsConstructor
public class AtivoController implements AtivoControllerDocumentation {

    private final AtivoService ativoService;

    @Override
    @PostMapping(value = "/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Mono<AtivoResponseDTO> AdicionarAtivo(@RequestBody @Valid AtivoRequestDTO requestDTO) {
        return this.ativoService.adicionarAtivo(requestDTO);
    }
}
