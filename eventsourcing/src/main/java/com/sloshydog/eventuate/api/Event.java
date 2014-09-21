package com.sloshydog.eventuate.api;

import org.joda.time.DateTime;

public interface Event<E> {

    String getIdentifier();

    DateTime getTimeStamp();

    String getAggregateType();

    String getType();

    E getPayload();
}
