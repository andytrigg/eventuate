package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.EventStoreException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PayloadSerializer {

    public SerializedPayload serialize(Object payload) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             try {
                 ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
                 try {
                     outputStream.writeObject(payload);
                 } finally {
                     outputStream.close();
                 }
             } catch (IOException e) {
                 throw new EventStoreException("An exception occurred writing serialized data to the output stream", e);
             }
             return new SimpleSerializedPayload(byteArrayOutputStream.toByteArray());
    }
}
