package com.sloshydog.eventuate.api;

import org.joda.time.DateTime;

import static com.sloshydog.eventuate.common.Preconditions.checkArgumentProvided;

public final class Events {

    public static <E> Event<E> newDomainEvent(String aggregateType, String aggregateId, E payload) {
        return new DomainEvent<>(aggregateId, aggregateType, payload);
    }

    public static <E> Event<E> newDomainEvent(String aggregateType, String aggregateId, DateTime timeStamp, E payload) {
        return new DomainEvent<E>(aggregateId, aggregateType, timeStamp, payload);
    }

    private static class DomainEvent<E> implements Event<E> {

        private final String aggregateId;
        private final String aggregateType;
        private final E payload;
        private final DateTime timeStamp;

        private DomainEvent(String aggregateId, String aggregateType, E payload) {
            this(aggregateId, aggregateType, new DateTime(), payload);
        }

        private DomainEvent(String aggregateId, String aggregateType, DateTime timeStamp, E payload) {
            this.aggregateId = checkArgumentProvided(aggregateId, "aggregateId");
            this.aggregateType = checkArgumentProvided(aggregateType, "aggregateType");
            this.payload = checkArgumentProvided(payload, "payload");
            this.timeStamp = checkArgumentProvided(timeStamp, "timeStamp");
        }

        @Override
        public String getAggregateIdentifier() {
            return aggregateId;
        }

        @Override
        public DateTime getTimeStamp() {
            return timeStamp;
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
