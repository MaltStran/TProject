package ru.tinkoff.edu.java.bot.clients;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class ChatClient {

    private final WebClient webClient;

    public ChatClient(String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public ChatClient() {
        this.webClient = WebClient.create("http://localhost:8080/tg-chat");
    }

    public HttpStatus registerChat(long id) {
        return webClient.post()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(HttpStatus.class)
                .block();
    }

    public HttpStatus deleteChat(long id) {
        return webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(HttpStatus.class)
                .block();
    }
}
