package dev.programing.moneycontrol.handler.signature;

import dev.programing.moneycontrol.client.finance.dto.AtivoFinanceResponseDTO;
import reactor.core.publisher.Mono;

public interface FinanceHandler {

    Mono<AtivoFinanceResponseDTO> recuperarAtivo(String ticker);
}
