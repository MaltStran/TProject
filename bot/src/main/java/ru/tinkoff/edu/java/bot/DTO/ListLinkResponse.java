package ru.tinkoff.edu.java.bot.DTO;


import java.util.List;

public record ListLinkResponse(List<LinkResponse> links, int size) {}
