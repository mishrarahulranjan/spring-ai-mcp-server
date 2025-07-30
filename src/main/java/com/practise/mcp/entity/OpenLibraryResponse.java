package com.practise.mcp.entity;

import java.util.List;

public record OpenLibraryResponse(
        int numFound,
        int start,
        boolean numFoundExact,
        List<BookDoc> docs,
        String q,
        int offset
) {

}