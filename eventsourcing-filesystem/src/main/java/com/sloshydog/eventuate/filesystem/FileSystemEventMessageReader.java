package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.Events;
import org.joda.time.DateTime;

import java.io.DataInput;
import java.io.IOException;

class FileSystemEventMessageReader {

    private final PayloadSerializer payloadSerializer;

    public FileSystemEventMessageReader(PayloadSerializer payloadSerializer) {
        this.payloadSerializer = payloadSerializer;
    }

    public Event readEventMessage(DataInput input) throws IOException {
        String aggregateIdentifier = input.readUTF();
        String timestamp = input.readUTF();
        String aggregateType = input.readUTF();
        byte[] payload = new byte[input.readInt()];
        input.readFully(payload);
        Object deserializedPayload = payloadSerializer.deserialize(new SimpleSerializedPayload(payload));
        return Events.newDomainEvent(aggregateType, aggregateIdentifier, new DateTime(timestamp), deserializedPayload);
    }

}
