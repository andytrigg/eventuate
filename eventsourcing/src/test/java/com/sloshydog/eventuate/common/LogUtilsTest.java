package com.sloshydog.eventuate.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogUtilsTest {

    @Mock
    private Logger logger;

    @Test
    public void shouldLogDebugMessageWhenDebugLoggingIsEnabled() {
        when(logger.isDebugEnabled()).thenReturn(true);

        LogUtils.debug(logger, "format", "argument");

        verify(logger).debug("format", new String[]{"argument"});
    }

    @Test
    public void shouldNotLogDebugMessageWhenDebugLoggingIsDisabled() {
        when(logger.isDebugEnabled()).thenReturn(false);

        LogUtils.debug(logger, "format", "argument");

        verify(logger, never()).debug("format", new String[]{"argument"});
    }
}