package ru.tinkoff.edu.java.scrapper.clients;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.DTO.LinkUpdate;

public class BotClient {
    private final WebClient webClient;

    public BotClient(String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public BotClient() {
        this.webClient = WebClient.create("http://localhost:8081");
    }

    public void updateLink(LinkUpdate request) {
        webClient.post()
                .uri("/updates")
                .body(Mono.just(request), LinkUpdate.class)
                .retrieve();
    }
}
