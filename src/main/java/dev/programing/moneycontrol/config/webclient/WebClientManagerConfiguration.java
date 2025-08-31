package dev.programing.moneycontrol.config.webclient;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientSsl;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@RequiredArgsConstructor
public class WebClientManagerConfiguration {

    private final ReactiveOAuth2AuthorizedClientManager authorizedClientManager;
    private final WebClientSsl webClientSsl;

    protected <T> T buildWebClient(String baseUrl, Class<T> clientClass, boolean sslCheck){
        return buildWebClient(null,baseUrl, clientClass, null, sslCheck);
    }

    protected <T> T buildWebClient(String registrationId, String baseUrl, Class<T> clientClass, boolean sslCheck){
        return buildWebClient(registrationId, baseUrl, clientClass, null, sslCheck);
    }

    protected <T> T buildWebClient(String registrationId, String baseUrl, Class<T> clientClass, Integer maxInMemorySize, boolean sslCheck) {

        return HttpServiceProxyFactory.builderFor(sslCheck ? createWebClientAdapterWithSslInternal(baseUrl, registrationId, maxInMemorySize) : createWebClientAdapter(baseUrl, registrationId, maxInMemorySize))
                .build().createClient(clientClass);
    }

    private WebClient.Builder createWebClientWithOAuth(String baseUrl, String registrationId, Integer maxInMemorySize){
        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth2Client = new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
        oauth2Client.setDefaultClientRegistrationId(registrationId);

        var webClient = WebClient
                .builder()
                .filter(oauth2Client)
                .baseUrl(baseUrl);

        if (maxInMemorySize != null){
            webClient.codecs(codecs -> codecs
                    .defaultCodecs()
                    .maxInMemorySize(maxInMemorySize));
        }

        return webClient;

    }

    private WebClient.Builder createWebClientWithoutOAuth(String baseUrl, Integer maxInMemorySize){
        var webClient = WebClient
                .builder()
                .baseUrl(baseUrl);

        if (maxInMemorySize != null){
            webClient.codecs(codecs -> codecs
                    .defaultCodecs()
                    .maxInMemorySize(maxInMemorySize));
        }

        return webClient;
    }

    private WebClientAdapter createWebClientAdapterWithSslInternal(String baseUrl, String registrationId, Integer maxInMemorySize) {

        WebClient.Builder webClientBuilder;
        if (!Strings.isEmpty(registrationId)){
            webClientBuilder = createWebClientWithOAuth(baseUrl, registrationId, maxInMemorySize);
        } else {
            webClientBuilder = createWebClientWithoutOAuth(baseUrl, maxInMemorySize);
        }
        webClientBuilder.apply(webClientSsl.fromBundle("interno"));
        return WebClientAdapter.create(webClientBuilder.build());
    }

    private WebClientAdapter createWebClientAdapter(String baseUrl, String registrationId, Integer maxInMemorySize) {

        WebClient.Builder webClientBuilder;
        if (!Strings.isEmpty(registrationId)){
            webClientBuilder = createWebClientWithOAuth(baseUrl, registrationId, maxInMemorySize);
        } else {
            webClientBuilder = createWebClientWithoutOAuth(baseUrl, maxInMemorySize);
        }
        return WebClientAdapter.create(webClientBuilder.build());
    }
}
