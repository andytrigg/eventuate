package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.EventStoreException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PayloadSerializerTest {

    @Test
    public void shouldBeAbleToSerializeAPayloadToByteData() throws IOException {
        SerializedPayload serializedPayload = new PayloadSerializer().serialize("My payload");

        SerializedPayload expectedSerializedPayload = new SimpleSerializedPayload(new byte[]{-84, -19, 0, 5, 116, 0, 10, 77, 121, 32, 112, 97, 121, 108, 111, 97, 100});
        assertThat(serializedPayload).isEqualTo(expectedSerializedPayload);
    }

    @Test
    public void ensureTheOutputStreamIsClosed() throws IOException {
        PayloadSerializer payloadSerializer = spy(new PayloadSerializer());
        ObjectOutputStream objectOutputStream = spy(new ObjectOutputStream(new ByteArrayOutputStream()));
        doReturn(objectOutputStream).when(payloadSerializer).createObjectOutputStream(any(ByteArrayOutputStream.class));

        payloadSerializer.serialize("My payload");

        verify(objectOutputStream).close();
    }

    @Test
    public void ensureExceptionIsThrownIfSerializationFails() throws IOException {
        PayloadSerializer payloadSerializer = spy(new PayloadSerializer());
        doThrow(new IOException("Bang")).when(payloadSerializer).createObjectOutputStream(any(ByteArrayOutputStream.class));

        try {
            payloadSerializer.serialize("My payload");
            failBecauseExceptionWasNotThrown(EventStoreException.class);
        } catch (EventStoreException e) {
            assertThat(e).hasMessage("An exception occurred writing serialized data: Bang");
        }
    }

    @Test
    public void shouldBeAbleToDeserializeAPayloadToItsOriginalObject() {
        Object result = new PayloadSerializer().deserialize(new SimpleSerializedPayload(new byte[]{-84, -19, 0, 5, 116, 0, 10, 77, 121, 32, 112, 97, 121, 108, 111, 97, 100}));

        assertThat(result).isEqualTo("My payload");
    }

    @Test
    public void ensureThatTheInputStreamIsClosed() throws IOException {
        byte[] data = {-84, -19, 0, 5, 116, 0, 10, 77, 121, 32, 112, 97, 121, 108, 111, 97, 100};
        SimpleSerializedPayload serializedPayload = new SimpleSerializedPayload(data);

        ObjectInputStream inputStream = spy(new ObjectInputStream(new ByteArrayInputStream(data)));

        PayloadSerializer payloadSerializer = spy(new PayloadSerializer());
        when(payloadSerializer.createObjectInputStream(serializedPayload)).thenReturn(inputStream);

        payloadSerializer.deserialize(serializedPayload);

        verify(inputStream).close();
    }

    @Test
    public void ensureExceptionIsThrownIfDeserializationFails() throws IOException {
        SimpleSerializedPayload serializedPayload = new SimpleSerializedPayload(new byte[]{-84, -19, 0, 5, 116, 0, 10, 77, 121, 32, 112, 97, 121, 108, 111, 97, 100});

        PayloadSerializer payloadSerializer = spy(new PayloadSerializer());
        doThrow(new IOException("Bang")).when(payloadSerializer).createObjectInputStream(serializedPayload);

        try {
            payloadSerializer.deserialize(serializedPayload);
            failBecauseExceptionWasNotThrown(EventStoreException.class);
        } catch (EventStoreException e) {
            assertThat(e).hasMessage("An error occurred while deserializing: Bang");
        }
    }
}
