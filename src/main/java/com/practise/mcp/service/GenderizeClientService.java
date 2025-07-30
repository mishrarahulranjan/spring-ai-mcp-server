package com.practise.mcp.service;

import com.practise.mcp.entity.GenderizeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GenderizeClientService {

    private final WebClient genderizeWebClient;

    @Autowired
    public GenderizeClientService(WebClient genderizeWebClient) {
        this.genderizeWebClient = genderizeWebClient;
    }

    public Mono<String> getGenderForName(String name) {
        return genderizeWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/")
                        .queryParam("name", name)
                        .build())
                .retrieve()
                .bodyToMono(GenderizeResponse.class)
                .map(GenderizeResponse::gender)
                .doOnError(e -> System.err.println("Error calling Genderize API: " + e.getMessage()));
    }
}