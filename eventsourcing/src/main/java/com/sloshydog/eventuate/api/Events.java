package com.sloshydog.eventuate.api;

import org.joda.time.DateTime;

import static com.sloshydog.eventuate.api.Preconditions.checkArgumentProvided;

public final class Events {

    public static <E> Event<E> newDomainEvent(String aggregateId, String aggregateType) {
        return new DomainEvent<>(checkArgumentProvided(aggregateId, "aggregateId"), checkArgumentProvided(aggregateType, "aggregateType"));
    }

    private static class DomainEvent<E> implements Event<E> {

        private final String aggregateId;
        private final String aggregateType;

        private DomainEvent(String aggregateId, String aggregateType) {
            this.aggregateId = aggregateId;
            this.aggregateType = aggregateType;
        }

        @Override
        public String getAggregateIdentifier() {
            return aggregateId;
        }

        @Override
        public DateTime getTimeStamp() {
            return new DateTime();
        }

        @Override
        public String getAggregateType() {
            return aggregateType;
        }

        @Override
        public E getPayload() {
            return null;
        }
    }
}
