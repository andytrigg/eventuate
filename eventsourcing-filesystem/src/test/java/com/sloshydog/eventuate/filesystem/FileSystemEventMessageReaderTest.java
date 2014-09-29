package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.Event;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.DataInput;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileSystemEventMessageReaderTest {

    @Mock
    private PayloadSerializer payloadSerializer;
    @Mock
    private DataInput input;
    private final Object expectedPayload = new Object();

    @Before
    public void setUp() throws IOException {
        when(input.readUTF()).thenReturn("aggregateIdentifier")
                .thenReturn(new DateTime(2014, 9, 24, 19, 10).toString())
                .thenReturn("aggregateType");
        when(input.readInt()).thenReturn(10);
        when(payloadSerializer.deserialize(any(SimpleSerializedPayload.class))).thenReturn(expectedPayload);

    }

    @Test
    public void shouldBeAbleToGetTheAggregateIdentifierForAnEventFromADataInput() throws IOException {
        FileSystemEventMessageReader eventMessageReader = new FileSystemEventMessageReader(payloadSerializer);
        Event event = eventMessageReader.readEventMessage(input);

        assertThat(event.getAggregateIdentifier()).isEqualTo("aggregateIdentifier");
    }

    @Test
    public void shouldBeAbleToGetTheAggregateTypeForAnEventFromADataInput() throws IOException {
        FileSystemEventMessageReader eventMessageReader = new FileSystemEventMessageReader(payloadSerializer);
        Event event = eventMessageReader.readEventMessage(input);

        assertThat(event.getAggregateType()).isEqualTo("aggregateType");
    }

    @Test
    public void shouldBeAbleToGetTheTimeStampForAnEventFromADataInput() throws IOException {
        FileSystemEventMessageReader eventMessageReader = new FileSystemEventMessageReader(payloadSerializer);
        Event event = eventMessageReader.readEventMessage(input);

        assertThat(event.getTimeStamp()).isEqualTo(new DateTime(2014, 9, 24, 19, 10));
    }

    @Test
    public void shouldBeAbleToGetThePayloadForAnEventFromADataInput() throws IOException {
        FileSystemEventMessageReader eventMessageReader = new FileSystemEventMessageReader(payloadSerializer);
        Event event = eventMessageReader.readEventMessage(input);

        assertThat(event.getPayload()).isEqualTo(expectedPayload);
    }
}