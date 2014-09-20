package com.sloshydog.eventuate.api;

import static com.google.common.base.Preconditions.checkNotNull;

public class EventSpecification {

    private final String aggregateIdentifier;
    private final String aggregateType;

    public EventSpecification(String aggregateIdentifier, String aggregateType) {
        this.aggregateIdentifier = checkNotNull(aggregateIdentifier, "aggregateIdentifier is null");
        this.aggregateType = checkNotNull(aggregateType, "aggregateType is null");
    }

    public String getAggregateIdentifier() {
        return aggregateIdentifier;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public boolean matches(Event event) {
        checkNotNull(event, "event is null");
        return aggregateIdentifier.matches(event.getIdentifier()) && getAggregateType().matches(event.getAggregateType());
    }
}
