package com.sloshydog.eventuate.impl;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventSpecification;

public class AggregateTypeSpecification implements EventSpecification {

    private final String aggregateType;

    public AggregateTypeSpecification(String aggregateType) {
        this.aggregateType = aggregateType;
    }

    public boolean matches(Event applicationEvent) {
        return applicationEvent.getAggregateType().equals(aggregateType);
    }
}
