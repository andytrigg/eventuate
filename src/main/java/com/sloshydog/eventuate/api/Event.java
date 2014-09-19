package com.sloshydog.eventuate.api;

public interface Event {

    String getIdentifier();

    String getAggregateType();

    String getType();

    String getPayload();
}
