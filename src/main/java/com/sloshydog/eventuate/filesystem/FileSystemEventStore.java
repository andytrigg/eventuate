package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventSpecification;
import com.sloshydog.eventuate.api.EventSpecifications;
import com.sloshydog.eventuate.api.EventStore;
import com.sloshydog.eventuate.api.EventStoreException;
import com.sloshydog.eventuate.api.EventStream;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FileSystemEventStore implements EventStore {

    private static final String FILE_NAME_TEMPLATE = "%s.evt";
    private final String baseDirectory;

    public FileSystemEventStore(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    @Override
    public void store(Event applicationEvent) {
        OutputStream out = null;
        try {
            EventSpecification eventSpecification = EventSpecifications.specificationFrom(applicationEvent);
            out = new FileOutputStream(new File(getBaseDirFor(eventSpecification), getFileNameFor(eventSpecification)), true);
            DataOutputStream dataOutputStream = new DataOutputStream(out);
            dataOutputStream.writeUTF(applicationEvent.getPayload());
        } catch (IOException e) {
            throw new EventStoreException("Unable to store given entity due to an IOException", e);
        } finally {
            closeQuietly(out);
        }

    }

    private String getFileNameFor(EventSpecification eventSpecification) {
        return String.format(FILE_NAME_TEMPLATE, urlEncode(eventSpecification.getAggregateIdentifier()));
    }

    private static String urlEncode(Object id) {
        try {
            return URLEncoder.encode(id.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("System doesn't support UTF-8?", e);
        }
    }

    private File getBaseDirFor(EventSpecification eventSpecification) {
        File typeSpecificDir = new File(baseDirectory, eventSpecification.getAggregateType());
        if (!typeSpecificDir.exists() && !typeSpecificDir.mkdirs()) {
            throw new RuntimeException("Should be an IO exception");
        }
        return typeSpecificDir;
    }

    public void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ignored) {
            }
        }
    }

    @Override
    public EventStream getMatching(EventSpecification eventSpecification) {
//        DataInputStream inputStream = null;
//        try {
//            inputStream = new DataInputStream(new FileInputStream(new File(getBaseDirFor(eventSpecification), getFileNameFor(eventSpecification))));
//        } catch (FileNotFoundException e) {
//            throw new EventStoreException("bang", e);
//        }
//        return new EventStream() {
//
//            @Override
//            public Iterator<Event> iterator() {
//                return new ;
//            }
//        };
        return null;
    }
}
