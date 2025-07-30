package com.practise.mcp.config;

import com.practise.mcp.service.ExtendedSearchService;
import com.practise.mcp.service.GenderizeClientService;
import com.practise.mcp.service.OpenLibraryClientService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    final private GenderizeClientService genderizeClientService;

    final private OpenLibraryClientService openLibraryClientService;

    public ApplicationConfig(GenderizeClientService genderizeClientService, OpenLibraryClientService openLibraryClientService) {
        this.genderizeClientService = genderizeClientService;
        this.openLibraryClientService = openLibraryClientService;
    }

    @Bean
    ToolCallbackProvider tools(){
        return MethodToolCallbackProvider.builder()
                .toolObjects(new ExtendedSearchService(genderizeClientService, openLibraryClientService))
                .build();
    }

}
