package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.Event;

import java.io.DataOutput;
import java.io.IOException;

public class FileSystemEventMessageWriter {

    private final PayloadSerializer payloadSerializer;

    public FileSystemEventMessageWriter(PayloadSerializer payloadSerializer) {
        this.payloadSerializer = payloadSerializer;
    }

    public void writeEventMessage(DataOutput output, Event event) throws IOException {
        output.writeUTF(event.getAggregateIdentifier());
        output.writeUTF(event.getTimeStamp().toString());
        output.writeUTF(event.getAggregateType());

        SerializedPayload serializedPayload = payloadSerializer.serialize(event.getPayload());
        output.writeInt(serializedPayload.getData().length);
        output.write(serializedPayload.getData());
    }

}
