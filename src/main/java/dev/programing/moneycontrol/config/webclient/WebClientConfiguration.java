package dev.programing.moneycontrol.config.webclient;

import dev.programing.moneycontrol.client.finance.FinanceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientSsl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;

@Configuration
public class WebClientConfiguration extends WebClientManagerConfiguration {

    private static final String REGISTRATION_ID = "finance";
    private static final int TAMANHO_MAXIMO_MEMORIA_4_MB = 4 * 1024 * 1024;

    public WebClientConfiguration(ReactiveOAuth2AuthorizedClientManager authorizedClientManager, WebClientSsl webClientSsl) {
        super(authorizedClientManager, webClientSsl);
    }


    @Bean
    public FinanceClient getFinanceClient(@Value("${api-consumer.brapi-finance}") String baseUrl, @Value("${api-consumer.token}") String bearer) {
        return buildWebClient(baseUrl, FinanceClient.class, bearer);
    }
}
