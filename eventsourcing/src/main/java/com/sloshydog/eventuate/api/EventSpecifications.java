package com.sloshydog.eventuate.api;

import static com.google.common.base.Preconditions.checkArgument;

public final class EventSpecifications {

    private EventSpecifications() {
    }

    public static EventSpecification specificationFrom(Event event) {
        checkArgument(event != null, "event is null");
        return new EventSpecification(event.getAggregateType(), event.getAggregateIdentifier());
    }
}
