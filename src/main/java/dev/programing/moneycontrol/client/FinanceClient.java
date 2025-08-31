package dev.programing.moneycontrol.client;

import dev.programing.moneycontrol.dto.AtivoFinanceDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface FinanceClient {

    @GetExchange(value = "/api/quote/{ticker}", accept = APPLICATION_JSON_VALUE)
    Mono<AtivoFinanceDTO> recuperarAtivoFinanceiro(@PathVariable String ticker);
}
