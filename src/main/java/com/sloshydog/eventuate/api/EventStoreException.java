package com.sloshydog.eventuate.api;

public class EventStoreException extends RuntimeException {
    public EventStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
