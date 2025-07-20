package dev.grenn.githubinfoapi.dto;

public record ErrorResponse(
        int status,
        String message
) {}

