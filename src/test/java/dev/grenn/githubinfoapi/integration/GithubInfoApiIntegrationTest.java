package dev.grenn.githubinfoapi.integration;

import dev.grenn.githubinfoapi.dto.BranchInfo;
import dev.grenn.githubinfoapi.dto.RepositoryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnNonForkRepositoriesWithBranchesAndShaForValidUser() {
        // Given
        String username = "octocat";

        // When
        var response = restTemplate.getForEntity("/github-info/" + username, RepositoryResponse[].class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        RepositoryResponse[] body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.length).isGreaterThan(0);

        for (RepositoryResponse repo : body) {
            assertThat(repo.repositoryName()).isNotBlank();
            assertThat(repo.ownerLogin()).isEqualTo("octocat");
            assertThat(repo.branches()).isNotEmpty();
            for (BranchInfo branch : repo.branches()) {
                assertThat(branch.name()).isNotBlank();
                assertThat(branch.lastCommitSha()).isNotBlank();
            }
        }
    }
}
