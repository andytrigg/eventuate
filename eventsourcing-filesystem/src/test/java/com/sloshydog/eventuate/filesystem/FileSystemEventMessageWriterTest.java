package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.Event;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.DataOutput;
import java.io.IOException;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileSystemEventMessageWriterTest {

    @Mock
    private PayloadSerializer payloadSerializer;
    @Mock
    private DataOutput output;
    @Mock
    private Event event;
    @Mock
    private SerializedPayload serializedPayload;

    @Test
    public void shouldBeAbleToWriteAnEventToADataOutput() throws IOException {
        when(event.getAggregateIdentifier()).thenReturn("aggregateIdentifier");
        when(event.getTimeStamp()).thenReturn(new DateTime(2014, 9, 24, 19, 10));
        when(event.getAggregateType()).thenReturn("aggregateType");
        Object payload = new Object();
        when(event.getPayload()).thenReturn(payload);

        when(payloadSerializer.serialize(payload)).thenReturn(serializedPayload);

        byte[] data = new byte[10];
        when(serializedPayload.getData()).thenReturn(data);

        FileSystemEventMessageWriter eventMessageWriter = new FileSystemEventMessageWriter(payloadSerializer);
        eventMessageWriter.writeEventMessage(output, event);

        InOrder inOrderVerifier = Mockito.inOrder(output);
        inOrderVerifier.verify(output).writeUTF("aggregateIdentifier");
        inOrderVerifier.verify(output).writeUTF(new DateTime(2014, 9, 24, 19, 10).toString());
        inOrderVerifier.verify(output).writeUTF("aggregateType");
        inOrderVerifier.verify(output).writeInt(10);
        inOrderVerifier.verify(output).write(data);
        inOrderVerifier.verifyNoMoreInteractions();
    }

}
