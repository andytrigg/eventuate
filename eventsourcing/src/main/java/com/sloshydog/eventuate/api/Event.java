package com.sloshydog.eventuate.api;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Represents and event that has occurred. The Payload contains any pertinent information that relates to the event that
 * has occurred. The event should contain any relevant information that other components may require in order to react
 * to this event.
 *
 * @param <T> The type of the payload contained in the Event
 * @since 1.0
 */
public interface Event<T> extends Serializable {

    /**
     * Returns the identifier of the Aggregate that generated this Event.
     *
     * @return the identifier of the Aggregate that generated this Event
     */
    String getAggregateIdentifier();

    /**
     * Returns the type of the Aggregate that generated this Event.
     *
     * @return the type of the Aggregate that generated this Event
     */
    String getAggregateType();

    /**
     * Returns the time stamp of the occurrence of this Event. This is the time that this Event was created.
     *
     * @return the time stamp for this Event
     */
    DateTime getTimeStamp();

    /**
     * Returns the payload of this Event. The payload is the application-specific information.
     *
     * @return the payload of this Event
     */
    T getPayload();
}
