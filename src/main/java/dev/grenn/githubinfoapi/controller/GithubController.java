package dev.grenn.githubinfoapi.controller;

import dev.grenn.githubinfoapi.exception.UserNotFoundException;
import dev.grenn.githubinfoapi.dto.ErrorResponse;
import dev.grenn.githubinfoapi.dto.RepositoryResponse;
import dev.grenn.githubinfoapi.client.GithubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/github-info")
@RequiredArgsConstructor
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getRepositories(@PathVariable String username) {
        try {
            List<RepositoryResponse> result = githubService.getUserRepos(username);
            return ResponseEntity.ok(result);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404, "User not found"));
        }
    }
}


