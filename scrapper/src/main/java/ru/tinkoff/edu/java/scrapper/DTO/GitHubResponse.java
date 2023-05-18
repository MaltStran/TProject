package ru.tinkoff.edu.java.scrapper.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record GitHubResponse(@JsonProperty("full_name") String fullName,
        @JsonProperty("pushed_at") OffsetDateTime pushedAt,
        @JsonProperty("forks_count") Long forksCount,
        @JsonProperty("private") String isPrivate){}
