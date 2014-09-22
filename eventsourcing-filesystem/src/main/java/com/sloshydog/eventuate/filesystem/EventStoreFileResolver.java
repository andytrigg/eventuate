package com.sloshydog.eventuate.filesystem;

import com.google.common.base.Preconditions;
import com.sloshydog.eventuate.api.EventSpecification;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EventStoreFileResolver {

    private static final String FILE_NAME_TEMPLATE = "%s.evt";

    private final File baseDirectory;

    public EventStoreFileResolver(File baseDirectory) {
        this.baseDirectory = Preconditions.checkNotNull(baseDirectory);
    }

    public File getFileFor(EventSpecification eventSpecification) {
        return new File(getBaseDirFor(eventSpecification), getFileNameFor(eventSpecification));
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


}
