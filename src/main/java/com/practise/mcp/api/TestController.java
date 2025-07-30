package com.practise.mcp.api;

import com.practise.mcp.entity.BookDoc;
import com.practise.mcp.service.GenderizeClientService;
import com.practise.mcp.service.OpenLibraryClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class TestController {

    final private GenderizeClientService genderizeClientService;

    final private OpenLibraryClientService openLibraryClientService;

    /** REST endpoint to test the Spring AI MCP tool calling for gender prediction.
      * This RestController is primarily for testing the integration of the Spring AI tool */
    public TestController(GenderizeClientService genderizeClientService, OpenLibraryClientService openLibraryClientService) {
        this.genderizeClientService = genderizeClientService;
        this.openLibraryClientService = openLibraryClientService;
    }

    @GetMapping("/api/gender/{name}")
    Mono<String> testGenderApiForName(@PathVariable String name){
        return genderizeClientService.getGenderForName(name);
    }

    @GetMapping("/api/book/{topic}")
    Mono<List<BookDoc>> testBookApiForTopuc(@PathVariable String topic){
        return openLibraryClientService.searchBooks(topic);
    }
}
