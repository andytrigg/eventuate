package com.sloshydog.eventuate.api;

import org.joda.time.DateTime;

public interface Event<E> {

    String getAggregateIdentifier();

    DateTime getTimeStamp();

    String getAggregateType();

    E getPayload();
}
