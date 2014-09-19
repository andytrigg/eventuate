package com.sloshydog.eventuate.api;

public interface Event {

    String getGuid();

    String getAggregateType();

    String getType();

    String getPayload();
}
