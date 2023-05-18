package ru.tinkoff.edu.java.bot.exceptions;

public class LinkNotFoundException extends RuntimeException{
    public LinkNotFoundException(String message) {
        super(message);
    }
}
