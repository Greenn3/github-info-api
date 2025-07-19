package dev.grenn.githubinfoapi.service;

import dev.grenn.githubinfoapi.UserNotFoundException;
import dev.grenn.githubinfoapi.dto.BranchInfo;
import dev.grenn.githubinfoapi.dto.RepositoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;


import java.util.List;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class GithubService {

    private final RestClient restClient;

    public List<RepositoryResponse> getUserRepos(String username) {
        try {
            List<Map<String, Object>> repos = restClient.get()
                    .uri("/users/{username}/repos", username)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});

            return repos.stream()
                    .filter(repo -> !(Boolean) repo.get("fork"))
                    .map(repo -> {
                        String repoName = (String) repo.get("name");
                        String ownerLogin = ((Map<String, String>) repo.get("owner")).get("login");
                        List<BranchInfo> branches = getBranches(ownerLogin, repoName);
                        return new RepositoryResponse(repoName, ownerLogin, branches);
                    }).toList();

        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException();
        }
    }

    private List<BranchInfo> getBranches(String owner, String repo) {
        List<Map<String, Object>> branches = restClient.get()
                .uri("/repos/{owner}/{repo}/branches", owner, repo)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        return branches.stream()
                .map(branch -> new BranchInfo(
                        (String) branch.get("name"),
                        ((Map<String, String>) branch.get("commit")).get("sha")
                )).toList();
    }
}

