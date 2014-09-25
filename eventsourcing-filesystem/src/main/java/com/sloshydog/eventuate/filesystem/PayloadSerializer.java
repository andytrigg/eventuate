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

      public Object deserialize(SerializedPayload serializedPayload) {
          ObjectInputStream ois = null;
          try {
              ois = new ObjectInputStream(new ByteArrayInputStream(serializedPayload.getData()));
              return ois.readObject();
          } catch (ClassNotFoundException e) {
              throw new EventStoreException("An error occurred while deserializing: " + e.getMessage(), e);
          } catch (IOException e) {
              throw new EventStoreException("An error occurred while deserializing: " + e.getMessage(), e);
          } finally {
              IOUtils.closeQuietly(ois);
          }
      }
}
