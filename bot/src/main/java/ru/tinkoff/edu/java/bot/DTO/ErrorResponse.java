package ru.tinkoff.edu.java.bot.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class ErrorResponse {
    public ErrorResponse(@NotNull String description,@NotNull String code,@NotNull String exceptionName,@NotNull String exceptionMessage,@NotNull String[] stacktrace) {
        this.description = description;
        this.code = code;
        this.exceptionName = exceptionName;
        this.exceptionMessage = exceptionMessage;
        this.stacktrace = stacktrace;
    }
    @NotNull
    private String description;
    @NotNull
    private String code;
    @NotNull
    private String exceptionName;
    @NotNull
    private String exceptionMessage;
    @NotNull
    private String[] stacktrace;
}
