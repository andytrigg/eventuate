package com.sloshydog.eventuate.api;

import static com.google.common.base.Preconditions.checkArgument;

public class EventSpecifications {

    public static EventSpecification specificationFrom(Event event) {
        checkArgument(event != null, "event is null");
        return new EventSpecification(event.getIdentifier(), event.getAggregateType());
    }
}
