package ru.tinkoff.edu.java.scrapper.DTO;

public record ApiErrorResponse(String description, String code,
                               String exceptionName, String exceptionMessage,
                               String[] stacktrace){}
