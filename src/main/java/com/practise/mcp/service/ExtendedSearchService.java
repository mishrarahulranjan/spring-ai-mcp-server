package com.practise.mcp.service;

import com.practise.mcp.entity.BookDoc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class ExtendedSearchService {

    private GenderizeClientService genderizeClientService;

    private OpenLibraryClientService openLibraryClientService;

    public ExtendedSearchService(GenderizeClientService genderizeClientService, OpenLibraryClientService openLibraryClientService){
        this.genderizeClientService = genderizeClientService;
        this.openLibraryClientService = openLibraryClientService;
    }

    @Tool(description = "get top 2 books name for topic")
    Mono<List<BookDoc>> getTop2Books(@ToolParam(description = "Topic") String topic){
        Mono<List<BookDoc>> data=  openLibraryClientService.searchBooks(topic);
        log.info("inside getTop2Books data:{}",data);
        return data;
    }

    @Tool(description = "get gender for name")
    Mono<String> getGender(@ToolParam(description = "Name") String name){
        Mono<String> data= genderizeClientService.getGenderForName(name);
        log.info("inside getGender data:{}",data);
        return data;
    }


}
