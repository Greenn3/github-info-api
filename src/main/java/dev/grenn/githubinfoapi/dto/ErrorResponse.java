package dev.grenn.githubinfoapi.model;

public record ErrorResponse(
        int status,
        String message
) {}

