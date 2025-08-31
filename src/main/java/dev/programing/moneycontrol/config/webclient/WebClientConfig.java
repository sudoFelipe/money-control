package dev.programing.moneycontrol.config.webclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig implements WebFluxConfigurer {

    @Bean
    public InMemoryReactiveOAuth2AuthorizedClientService authorizedClientService(ReactiveClientRegistrationRepository clientRegistration) {
        return new InMemoryReactiveOAuth2AuthorizedClientService(clientRegistration);
    }

    @Bean
    public ReactiveOAuth2AuthorizedClientManager authorizedClientManager(ReactiveClientRegistrationRepository clientRegistrarion, InMemoryReactiveOAuth2AuthorizedClientService authorizedClient) {
        return new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(clientRegistrarion, authorizedClient);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
