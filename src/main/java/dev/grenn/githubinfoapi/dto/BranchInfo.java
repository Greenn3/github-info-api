package dev.grenn.githubinfoapi.dto;

public record BranchInfo(
        String name,
        String lastCommitSha
) {}
