package ru.tinkoff.edu.java.bot.DTO;

public record ApiErrorResponse(String description, String code,
                               String exceptionName, String exceptionMessage,
                               String[] stacktrace){}
