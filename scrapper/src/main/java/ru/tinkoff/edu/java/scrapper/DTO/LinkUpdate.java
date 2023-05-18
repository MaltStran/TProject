package ru.tinkoff.edu.java.scrapper.DTO;

import java.net.URI;

public record LinkUpdate(Long id, URI url, String description, Long[] tgChatIds) {}
