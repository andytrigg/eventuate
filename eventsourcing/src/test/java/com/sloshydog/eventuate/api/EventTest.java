package com.sloshydog.eventuate.api;

import org.junit.Test;

import java.io.Serializable;

import static org.assertj.core.api.Assertions.assertThat;

public class EventTest {

    @Test
    public void shouldBeSerializable() {
        assertThat(Serializable.class).isAssignableFrom(Event.class);
    }
}
