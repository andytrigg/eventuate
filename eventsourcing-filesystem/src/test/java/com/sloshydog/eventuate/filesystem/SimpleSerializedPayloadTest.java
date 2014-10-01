package com.sloshydog.eventuate.filesystem;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleSerializedPayloadTest {

    @Test
    public void shouldBeASerializedPayload() {
        assertThat(SerializedPayload.class).isAssignableFrom(SimpleSerializedPayload.class);
    }

    @Test
    public void shouldBeAbleToGetData() {
        byte[] data = new byte[10];
        SimpleSerializedPayload serializedPayload = new SimpleSerializedPayload(data);

        assertThat(serializedPayload.getData()).isSameAs(data);
    }

}
