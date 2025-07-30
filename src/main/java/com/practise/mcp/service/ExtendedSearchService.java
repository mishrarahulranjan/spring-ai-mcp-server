package com.practise.mcp.service;

import com.practise.mcp.entity.BookDoc;
import org.springframework.ai.tool.annotation.Tool;
import reactor.core.publisher.Mono;

import java.util.List;

public class ExtendedSearchService {

    private GenderizeClientService genderizeClientService;

    private OpenLibraryClientService openLibraryClientService;

    public ExtendedSearchService(GenderizeClientService genderizeClientService, OpenLibraryClientService openLibraryClientService){
        this.genderizeClientService = genderizeClientService;
        this.openLibraryClientService = openLibraryClientService;
    }

    @Tool(description = "get top 2 books name for topic")
    Mono<List<BookDoc>> getTop2Books(String topic){
        return openLibraryClientService.searchBooks(topic);
    }

    @Tool(description = "get gender with highest probability for name")
    Mono<String> getGender(String name){
        return genderizeClientService.getGenderForName(name);
    }


}
