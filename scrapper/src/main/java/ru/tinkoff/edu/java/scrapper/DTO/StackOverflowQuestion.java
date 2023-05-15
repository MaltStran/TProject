package ru.tinkoff.edu.java.scrapper.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record StackOverflowQuestion(@JsonProperty("title") String title,
                                    @JsonProperty("answer_count") Integer answerCount,
                                    @JsonProperty("last_edit_date") OffsetDateTime lastActivityDate,
                                    @JsonProperty("last_activity_date") OffsetDateTime lastEditDate) {}
