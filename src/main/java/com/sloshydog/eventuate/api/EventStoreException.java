package com.sloshydog.eventuate.api;

import java.io.IOException;

public class EventStoreException extends RuntimeException {
    public EventStoreException(String message, IOException cause) {
        super(message, cause);
    }
}
