package com.practise.mcp.entity;


public record GenderizeResponse(
    String name,
    String gender,
    Integer count,
    Double probability
) {}