package com.sloshydog.eventuate.api;

import java.io.Closeable;

public interface EventStream extends Iterable<Event>, Closeable {

}
