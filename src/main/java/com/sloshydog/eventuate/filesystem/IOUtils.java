package com.sloshydog.eventuate.filesystem;

import java.io.Closeable;
import java.io.IOException;

public final class IOUtils {

    private IOUtils() {
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ignored) {
            }
        }
    }

}
