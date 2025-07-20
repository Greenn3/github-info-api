package dev.grenn.githubinfoapi.dto;

import java.util.List;


    public record RepositoryResponse(
            String repositoryName,
            String ownerLogin,
            List<BranchInfo> branches
    ) {}


