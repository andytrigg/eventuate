package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.Event;

import java.io.DataOutput;
import java.io.IOException;

public class FileSystemEventMessageWriter {
    public void writeEventMessage(DataOutput output, Event event) throws IOException {
        output.writeUTF(event.getPayload().toString());
    }

}
