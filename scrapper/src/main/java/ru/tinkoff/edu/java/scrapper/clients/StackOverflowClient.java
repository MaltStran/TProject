package ru.tinkoff.edu.java.scrapper.clients;

import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.DTO.StackOverflowQuestion;
import ru.tinkoff.edu.java.scrapper.DTO.StackOverflowResponse;

public class StackOverflowClient {
    private final WebClient webClient;

    public StackOverflowClient() {
        this.webClient = WebClient.create("https://api.stackexchange.com/2.3/");
    }

    public StackOverflowClient(String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public StackOverflowResponse fetchQuestion(long id) {
        return webClient.get().uri("/questions/{id}?order=desc&sort=activity&site=stackoverflow", id)
                .retrieve()
                .bodyToMono(StackOverflowResponse.class)
                .block();
    }
}