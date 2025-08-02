package com.practise.mcp.service;

import com.practise.mcp.entity.BookDoc;
import com.practise.mcp.entity.OpenLibraryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class OpenLibraryClientService {

    private final WebClient openLibraryWebClient;

    @Value("${external.api.open.library.record.limit}")
    private int recordLimit;

    @Autowired
    public OpenLibraryClientService(WebClient openLibraryWebClient) {
        this.openLibraryWebClient = openLibraryWebClient;
    }

    public Mono<List<BookDoc>> searchBooks(String query) {
        log.info("inside searchBooks for query:{}",query);
        return openLibraryWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/search.json")
                        .queryParam("q", query)
                        .queryParam("size", recordLimit)
                        .build())
                .retrieve()
                .bodyToMono(OpenLibraryResponse.class)
                .map(OpenLibraryResponse::docs);
    }
}