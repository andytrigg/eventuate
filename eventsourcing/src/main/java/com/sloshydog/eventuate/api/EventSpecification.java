package com.sloshydog.eventuate.api;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class EventSpecification {

    private final String aggregateIdentifier;
    private final String aggregateType;

    public EventSpecification(String aggregateType, String aggregateIdentifier) {
        checkArgument(aggregateType != null, "aggregateIdentifier is null");
        checkArgument(aggregateIdentifier != null, "aggregateIdentifier is null");
        this.aggregateIdentifier = aggregateIdentifier;
        this.aggregateType = aggregateType;
    }

    public String getAggregateIdentifier() {
        return aggregateIdentifier;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public boolean matches(Event event) {
        checkNotNull(event, "event is null");
        return aggregateIdentifier.matches(event.getAggregateIdentifier()) && getAggregateType().matches(event.getAggregateType());
    }

    @Override
    public boolean equals(Object other) {
        return reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }
}
