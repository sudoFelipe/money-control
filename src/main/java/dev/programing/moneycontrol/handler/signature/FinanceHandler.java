package dev.programing.moneycontrol.handler.signature;

import dev.programing.moneycontrol.dto.AtivoFinanceDTO;
import reactor.core.publisher.Mono;

public interface FinanceHandler {

    Mono<AtivoFinanceDTO> recuperarAtivo(String ticker);
}
