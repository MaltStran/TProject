package ru.tinkoff.edu.java.scrapper.clients;

import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.DTO.GitHubResponse;

public class GitHubClient {

    private final WebClient webClient;

    public GitHubClient(String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public GitHubClient() {
        this.webClient = WebClient.create("https://api.github.com");
    }

    public GitHubResponse fetchRepo(String owner, String repo) {
        return webClient.get()
                .uri("/repos/{owner}/{repo}", owner, repo)
                .retrieve()
                .bodyToMono(GitHubResponse.class).block();
    }
}
