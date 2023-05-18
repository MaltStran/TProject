package ru.tinkoff.edu.java.bot.DTO;

public record LinkUpdate(Long id, String url, String description, Long[] tgChatIds) {}
