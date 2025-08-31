package dev.programing.moneycontrol.config;

import dev.programing.moneycontrol.exception.NaoAutenticadoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Mono;

import java.util.List;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Bean
    @ConditionalOnProperty(name = "security.enabled", havingValue = "true", matchIfMissing = true)
    SecurityWebFilterChain springSecurityFilterChainResourceServer(ServerHttpSecurity http, CorsConfigurationSource corsConfigurationSource, @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}") String jwkUri) {
        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(corsSpec -> corsSpec.configurationSource(corsConfigurationSource))
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/api/**").authenticated()
                        .anyExchange().permitAll()
                )
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                        .jwt(jwtSpec -> jwtSpec.jwkSetUri(jwkUri))
                        .authenticationEntryPoint((exchange, e) -> Mono.error(new NaoAutenticadoException(e.getMessage())))
                        .accessDeniedHandler((exchange, e) -> Mono.error(new NaoAutenticadoException(e.getMessage())))
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((exchange, e) -> Mono.error(new NaoAutenticadoException(e.getMessage())))
                        .accessDeniedHandler((exchange, e) -> Mono.error(new NaoAutenticadoException(e.getMessage())))
                )
                .build();
    }

    @Bean
    @ConditionalOnProperty(name = "security.enabled", havingValue = "false")
    SecurityWebFilterChain springSecurityFilterChainDisabled(ServerHttpSecurity http, CorsConfigurationSource corsConfigurationSource) {
        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(corsSpec -> corsSpec.configurationSource(corsConfigurationSource))
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .authorizeExchange(exchanges -> exchanges.anyExchange().permitAll())
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(@Value("${cors.linkorigem:*}") String linkOrigem) {
        final var configuration = new CorsConfiguration();
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedOriginPatterns(List.of(linkOrigem));
        configuration.setAllowCredentials(true);
        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
