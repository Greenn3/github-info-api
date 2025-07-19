package dev.grenn.githubinfoapi.model;

public record BranchInfo(
        String name,
        String lastCommitSha
) {}
