package com.sloshydog.eventuate.inmemory;

import com.google.common.collect.Lists;
import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventSpecification;
import com.sloshydog.eventuate.api.EventStore;
import com.sloshydog.eventuate.api.EventStream;
import com.sloshydog.eventuate.common.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * An in memory implementation of an {@link com.sloshydog.eventuate.api.EventStore}. This is not intended for production
 * implementations. It is intended to be useful for prototyping and testing.
 *
 * @since 0.1
 */
public class InMemoryEventStore implements EventStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryEventStore.class);

    private final Map<EventSpecification, List<Event>> eventsByType = newHashMap();

    /**
     * Store a single Event in the event store.
     * @param applicationEvent the event to be stored
     */
    @Override
    public void store(Event applicationEvent) {
        LogUtils.debug(LOGGER, "store the event:%s", applicationEvent);

        EventSpecification eventSpecification = new EventSpecification(applicationEvent.getAggregateType(), applicationEvent.getAggregateIdentifier());
        getEventsForAggregate(eventSpecification).add(applicationEvent);
    }

    /**
     * Retrieve an EventStream for the Events in the store that match the event specification provided.
     * @param eventSpecification The event specification that defines the criteria of the events to return in the event
     *                           stream
     * @return The stream of events that match the event specification
     */
    @Override
    public EventStream getMatching(final EventSpecification eventSpecification) {
        return new EventStream() {
            @Override
            public Iterator<Event> iterator() {
                return getEventsForAggregate(eventSpecification).iterator();
            }

            @Override
            public void close() throws IOException {

            }
        };
    }


    private List<Event> getEventsForAggregate(EventSpecification eventSpecification) {
        if (!eventsByType.containsKey(eventSpecification)) {
            eventsByType.put(eventSpecification, Lists.<Event>newArrayList());
        }
        return eventsByType.get(eventSpecification);
    }
}
