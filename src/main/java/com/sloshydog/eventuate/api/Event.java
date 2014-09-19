package com.sloshydog.eventuate.api;

import org.joda.time.DateTime;

public interface Event {

    String getIdentifier();

    DateTime getTimeStamp();

    String getAggregateType();

    String getType();

    String getPayload();
}
