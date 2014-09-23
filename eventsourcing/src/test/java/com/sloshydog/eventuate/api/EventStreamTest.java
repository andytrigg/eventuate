package com.sloshydog.eventuate.api;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.Closeable;
import java.util.Iterator;

public class EventStreamTest {
    @Test
    public void shouldBeCloseable() {
        Assertions.assertThat(Closeable.class).isAssignableFrom(EventStream.class);
    }

    @Test
    public void shouldBeAnIterator() {
        Assertions.assertThat(Iterable.class).isAssignableFrom(EventStream.class);
    }


}