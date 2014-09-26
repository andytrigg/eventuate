package com.sloshydog.eventuate.api;

import org.joda.time.DateTime;

import java.io.Serializable;

public interface Event<E> extends Serializable {

    String getAggregateIdentifier();

    DateTime getTimeStamp();

    String getAggregateType();

    E getPayload();
}
