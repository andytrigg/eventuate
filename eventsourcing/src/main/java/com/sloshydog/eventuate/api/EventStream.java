package com.sloshydog.eventuate.api;

import java.io.Closeable;

/**
 * The EventStream represents a stream of historical domain events. The order of events in this stream are in actual
 * chronological order in which the events happened.
 *
 * @since 1.0
 */
public interface EventStream extends Iterable<Event>, Closeable {

}
