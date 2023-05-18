package ru.tinkoff.edu.java.scrapper.exceptions;

public class LinkExistException extends RuntimeException{
    public LinkExistException(String message) {
        super(message);
    }
}
