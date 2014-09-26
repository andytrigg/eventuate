package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.EventStoreException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PayloadSerializer {

    public SerializedPayload serialize(Object payload) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream outputStream = createObjectOutputStream(byteArrayOutputStream);
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

    public Object deserialize(SerializedPayload serializedPayload) {
        ObjectInputStream inputStream = null;
        try {
            inputStream = createObjectInputStream(serializedPayload);
            return inputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new EventStoreException("An error occurred while deserializing: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new EventStoreException("An error occurred while deserializing: " + e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    ObjectInputStream createObjectInputStream(SerializedPayload serializedPayload) throws IOException {
        return new ObjectInputStream(new ByteArrayInputStream(serializedPayload.getData()));
    }

    ObjectOutputStream createObjectOutputStream(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        return new ObjectOutputStream(byteArrayOutputStream);
    }
}
