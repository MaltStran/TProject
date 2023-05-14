package ru.tinkoff.edu.java.scrapper.DTO;

import java.util.List;

public record StackOverflowResponse(List<StackOverflowQuestion> questionList) {}
