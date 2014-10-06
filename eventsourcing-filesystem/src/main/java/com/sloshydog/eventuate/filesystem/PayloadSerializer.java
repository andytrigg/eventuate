package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.EventStoreException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.apache.commons.io.IOUtils.closeQuietly;


/**
 * Describes how a payload associated to an event is serialized and deserialized. This implementation uses an
 * ObjectOutputStream for serializing objects and an ObjectInputStream for deserializing objects.
 *
 * @since 0.1
 */
class PayloadSerializer {

    /**
     * Serializes a payload using an ObjectOutputStream. The result is returned as a {@link com.sloshydog.eventuate.filesystem.SerializedPayload}.
     *
     * @param payload the object to be serialized.
     * @return The serialized payload
     * @throws EventStoreException if the payload object can not be serialized
     */
    public SerializedPayload serialize(Object payload) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream outputStream = createObjectOutputStream(byteArrayOutputStream);
            try {
                outputStream.writeObject(payload);
            } finally {
                closeQuietly(outputStream);
            }
        } catch (IOException e) {
            throw new EventStoreException("An exception occurred writing serialized data: " + e.getMessage(), e);
        }
        return new SimpleSerializedPayload(byteArrayOutputStream.toByteArray());
    }

    /**
     * Deserializes a {@link com.sloshydog.eventuate.filesystem.SerializedPayload} to the original serialized payload.
     *
     * @param serializedPayload the serialized payload to be deserialized.
     * @return The deserialized payload object
     * @throws EventStoreException if the {@link com.sloshydog.eventuate.filesystem.SerializedPayload} can not be
     *                             deserialized
     */
    public Object deserialize(SerializedPayload serializedPayload) {
        ObjectInputStream inputStream = null;
        try {
            inputStream = createObjectInputStream(serializedPayload);
            return inputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new EventStoreException("An error occurred while deserializing: " + e.getMessage(), e);
        } finally {
            closeQuietly(inputStream);
        }
    }

    ObjectInputStream createObjectInputStream(SerializedPayload serializedPayload) throws IOException {
        return new ObjectInputStream(new ByteArrayInputStream(serializedPayload.getData()));
    }

    ObjectOutputStream createObjectOutputStream(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        return new ObjectOutputStream(byteArrayOutputStream);
    }
}
