package dev.programing.moneycontrol.handler.implementation;

import dev.programing.moneycontrol.client.finance.FinanceClient;
import dev.programing.moneycontrol.client.finance.dto.AtivoFinanceResponseDTO;
import dev.programing.moneycontrol.dto.AtivoFinanceDTO;
import dev.programing.moneycontrol.exception.AtivoInvalidoException;
import dev.programing.moneycontrol.exception.AtivoNotFoundException;
import dev.programing.moneycontrol.exception.ErroInternoApi;
import dev.programing.moneycontrol.handler.signature.FinanceHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@Component
@Slf4j
public class FinanceHandlerImpl implements FinanceHandler {

    private final FinanceClient client;

    @Override
    public Mono<AtivoFinanceResponseDTO> recuperarAtivo(String ticker) {
        return this.client.recuperarAtivoFinanceiro(ticker)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    if (NOT_FOUND.isSameCodeAs(ex.getStatusCode())) {
                        return Mono.error(new AtivoNotFoundException());
                    }
                    if (BAD_REQUEST.isSameCodeAs(ex.getStatusCode())) {
                        return Mono.error(new AtivoInvalidoException());
                    }
                    log.info("Erro na recuperação do ativo {}", ticker);
                    return Mono.error(new ErroInternoApi("Erro ao recuperar o ativo."));
                });
    }
}
