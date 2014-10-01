package com.sloshydog.eventuate.filesystem;

import com.google.common.base.Preconditions;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class EventStoreFileResolver {

    private static final String FILE_NAME_TEMPLATE = "%s.evt";

    private final File baseDirectory;

    public EventStoreFileResolver(File baseDirectory) {
        this.baseDirectory = Preconditions.checkNotNull(baseDirectory);
    }

    public File getFileFor(String aggregateType, String aggregateIdentifier) {
        return new File(getBaseDirFor(aggregateType), getFileNameFor(aggregateIdentifier));
    }

    private String getFileNameFor(String aggregateIdentifier) {
        return String.format(FILE_NAME_TEMPLATE, urlEncode(aggregateIdentifier));
    }

    private static String urlEncode(Object id) {
        try {
            return URLEncoder.encode(id.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("System doesn't support UTF-8?", e);
        }
    }

    private File getBaseDirFor(String aggregateType) {
        File typeSpecificDir = new File(baseDirectory, aggregateType);
        if (!typeSpecificDir.exists() && !typeSpecificDir.mkdirs()) {
            throw new RuntimeException("Should be an IO exception");
        }
        return typeSpecificDir;
    }


}
