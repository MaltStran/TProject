package ru.tinkoff.edu.java.bot.clients;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.DTO.AddLinkRequest;
import ru.tinkoff.edu.java.bot.DTO.LinkResponse;
import ru.tinkoff.edu.java.bot.DTO.ListLinkResponse;
import ru.tinkoff.edu.java.bot.DTO.RemoveLinkRequest;

public class LinkClient {

    private final WebClient webClient;

    public LinkClient(String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public LinkClient() {
        this.webClient = WebClient.create("http://localhost:8080/");}

    public ListLinkResponse getAllLinks(Long tgChatId) {
        return webClient.get()
                .uri("/links")
                .accept(MediaType.APPLICATION_JSON)
                .header("Tg-Chat-Id", tgChatId.toString())
                .retrieve()
                .bodyToMono(ListLinkResponse.class)
                .block();
    }

    public LinkResponse deleteLink(Long tgChatId, RemoveLinkRequest linkRequest) {
        return webClient.method(HttpMethod.DELETE)
                .uri("/links")
                .header("Tg-Chat-Id", tgChatId.toString())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(linkRequest), RemoveLinkRequest.class)
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .block();
    }

    public LinkResponse addLink(Long tgChatId, AddLinkRequest linkRequest) {
        return webClient.post()
                .header("Tg-Chat-Id", tgChatId.toString())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(linkRequest), AddLinkRequest.class)
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .block();
    }
}
