package com.sloshydog.eventuate.common;

import org.slf4j.Logger;

public final class LogUtils {

    private LogUtils() {
    }

    public static void debug(Logger logger, String format, Object... arguments) {
        if (logger.isDebugEnabled()) {
            logger.debug(format, arguments);
        }
    }
}
