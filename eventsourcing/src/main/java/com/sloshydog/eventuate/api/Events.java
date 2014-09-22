package com.sloshydog.eventuate.api;

import org.joda.time.DateTime;

import static com.sloshydog.eventuate.common.Preconditions.checkArgumentProvided;

public final class Events {

    public static <E> Event<E> newDomainEvent(String aggregateType, String aggregateId, E payload) {
        return new DomainEvent<>(
                checkArgumentProvided(aggregateId, "aggregateId"),
                checkArgumentProvided(aggregateType, "aggregateType"),
                checkArgumentProvided(payload, "payload"));
    }

    private static class DomainEvent<E> implements Event<E> {

        private final String aggregateId;
        private final String aggregateType;
        private final E payload;

        private DomainEvent(String aggregateId, String aggregateType, E payload) {
            this.aggregateId = aggregateId;
            this.aggregateType = aggregateType;
            this.payload = payload;
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
            return payload;
        }
    }
}
