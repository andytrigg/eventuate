package com.sloshydog.eventuate.api;

/**
 * An EventStore provides the mechanism for event storage. Events are read as {@link com.sloshydog.eventuate.api.EventStream}.
 *
 * @since 0.1
 */
public interface EventStore {

    /**
     * Store a single Event in the event store.
     * @param applicationEvent the event to be stored
     */
    void store(Event applicationEvent);

    /**
     * Retrieve an EventStream for the Events in the event store that match the event specification provided.
     * @param eventSpecification
     * @return
     */
    EventStream getMatching(EventSpecification eventSpecification);
}
