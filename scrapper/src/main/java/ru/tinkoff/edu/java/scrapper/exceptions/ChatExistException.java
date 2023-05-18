package ru.tinkoff.edu.java.scrapper.exceptions;

public class ChatExistException extends RuntimeException{
    public ChatExistException(String message) {
        super(message);
    }
}
