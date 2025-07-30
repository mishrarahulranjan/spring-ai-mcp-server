package com.practise.mcp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${external.api.open.library.url}")
    private String openApiBaseUrl;

    @Value("${external.api.genderize.library.url}")
    private String genderizeBaseUrl;

    @Bean
    public WebClient openLibraryWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl(openApiBaseUrl)
                .build();
    }

    @Bean
    public WebClient genderizeWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl(genderizeBaseUrl)
                .build();
    }
}